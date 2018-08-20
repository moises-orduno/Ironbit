package com.moises.ironbit.lobby;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.DetectedActivity;
import com.moises.ironbit.R;
import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.model.FoursquareResponse;
import com.moises.ironbit.common.model.Venue;
import com.moises.ironbit.common.viewmodel.LocalResponse;
import com.moises.ironbit.common.viewmodel.Response;
import com.moises.ironbit.lobby.viewmodel.LocalVenueViewModel;
import com.moises.ironbit.lobby.viewmodel.LocalVenueViewModelFactory;
import com.moises.ironbit.lobby.viewmodel.VenueViewModel;
import com.moises.ironbit.lobby.viewmodel.VenueViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationBasedOnActivityProvider;
import timber.log.Timber;


public class VenuesFragment extends Fragment implements VenuesRecyclerViewAdapter.OnListFragmentInteractionListener {

    public static String TAG = VenuesFragment.class.getSimpleName();

    @Inject
    VenueViewModelFactory mViewModelFactory;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.list)
    RecyclerView mRecyclerViewList;
    private VenueViewModel viewModel;

    @Inject
    LocalVenueViewModelFactory mLocalViewModelFactory;
    private LocalVenueViewModel mLocalViewModel;
    private List<LocalVenue> mLocalVenues=new ArrayList<>();

    public VenuesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VenuesFragment newInstance() {
        VenuesFragment fragment = new VenuesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(VenueViewModel.class);

        viewModel.response().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                VenuesFragment.this.processResponse(response);
            }
        });

        mLocalViewModel = ViewModelProviders.of(getActivity(), mLocalViewModelFactory).get(LocalVenueViewModel.class);

        mLocalViewModel.response().observe(this, new Observer<LocalResponse>() {
            @Override
            public void onChanged(@Nullable LocalResponse response) {
                VenuesFragment.this.processLocalResponse(response);
            }
        });

    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }


    private void processLocalResponse(LocalResponse response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS_LOAD:
                renderLocalDataState(response.data);
                break;

            case SUCCESS_INSERT:
                renderLocalInsert();
                break;

            case SUCCESS_DELETE:
                renderLocalDelete();
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLocalDataState(List<LocalVenue> localVenues) {

        SmartLocation.with(getContext()).location(new LocationBasedOnActivityProvider(new LocationBasedOnActivityProvider.LocationBasedOnActivityListener() {
            @Override
            public LocationParams locationParamsForActivity(DetectedActivity detectedActivity) {
                return null;
            }
        }))
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        Log.d(TAG, "onLocationUpdated: "+location.getLatitude()+" "+location.getLongitude());

                        viewModel.loadVenues(location.getLatitude(),location.getLongitude());
                    }
                });
        mProgressBar.setVisibility(View.GONE);

        mLocalVenues=localVenues;

    }

    private void renderLocalInsert() {

        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Add Success", Toast.LENGTH_SHORT).show();


    }

    private void renderLocalDelete() {

        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Delete Success", Toast.LENGTH_SHORT).show();


    }


    private void renderLoadingState() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void renderDataState(FoursquareResponse greeting) {

        mProgressBar.setVisibility(View.GONE);
        mRecyclerViewList.setAdapter(new VenuesRecyclerViewAdapter(greeting.getResponse().getGroups().get(0).getItems(),mLocalVenues, this));

    }

    private void renderErrorState(Throwable throwable) {
        Timber.e(throwable);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);


        ButterKnife.bind(this, view);
        mRecyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));

        mLocalViewModel.loadLocalVenues();

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onListFragmentInteraction(Venue item) {

        if(!((VenuesRecyclerViewAdapter)mRecyclerViewList.getAdapter()).validateLocal(item.getId())) {
            mLocalViewModel.insertLocalVenue(item);
        }else {
            mLocalViewModel.deleteLocalVenue(((VenuesRecyclerViewAdapter)mRecyclerViewList.getAdapter()).getLocalVenue(item.getId()));
        }
    }
}
