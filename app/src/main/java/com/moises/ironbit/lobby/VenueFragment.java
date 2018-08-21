package com.moises.ironbit.lobby;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moises.ironbit.R;
import com.moises.ironbit.common.model.venue.FoursquareVenueRequest;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venue.Venue;
import com.moises.ironbit.common.viewmodel.Response;
import com.moises.ironbit.lobby.viewmodel.VenueViewModel;
import com.moises.ironbit.lobby.viewmodel.VenueViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;


public class VenueFragment extends Fragment {

    public static String TAG = VenueFragment.class.getSimpleName();

    @Inject
    VenueViewModelFactory mViewModelFactory;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private VenueViewModel mViewModel;

    @BindView(R.id.name)
    TextView mNameView;

    @BindView(R.id.address)
    TextView mAddressView;

    @BindView(R.id.city)
    TextView mCityView;

    @BindView(R.id.category)
    TextView mCategoryView;

    @BindView(R.id.price)
    TextView mPriceView;

    private static final String ARG_ID = "id";
    private String mId;

    public VenueFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static VenueFragment newInstance(String id) {
        VenueFragment fragment = new VenueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(VenueViewModel.class);

        mViewModel.response().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                VenueFragment.this.processResponse(response);
            }
        });
        if (getArguments() != null) {
            mId = getArguments().getString(ARG_ID);
        }
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(response.dataVenue);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void renderErrorState(Throwable throwable) {
        Timber.e(throwable);
        mProgressBar.setVisibility(View.GONE);
    }

    private void renderDataState(FoursquareVenueResponse foursquareVenueResponse) {

        Venue venue = foursquareVenueResponse.getResponse().getVenue();
        mNameView.setText(venue.getName());

        mAddressView.setText(venue.getLocation().getAddress());

        mCityView.setText(venue.getLocation().getCity());

        mCategoryView.setText(getString(R.string.title_category,venue.getCategories().get(0).getName()));

        mPriceView.setText(getString(R.string.title_price, venue.getPrice().getMessage()));
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_venue, container, false);

        ButterKnife.bind(this, view);

        mViewModel.loadVenueById(mId);
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

}
