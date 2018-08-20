package com.moises.ironbit.lobby;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.moises.ironbit.R;
import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.viewmodel.LocalResponse;
import com.moises.ironbit.lobby.viewmodel.LocalVenueViewModel;
import com.moises.ironbit.lobby.viewmodel.LocalVenueViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;


public class LocalVenuesFragment extends Fragment implements LocalVenuesRecyclerViewAdapter.OnListFragmentInteractionListener {

    public static String TAG = LocalVenuesFragment.class.getSimpleName();

    @Inject
    LocalVenueViewModelFactory viewModelFactory;
    private LocalVenueViewModel mViewModel;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.list)
    RecyclerView mRecyclerViewList;

    public LocalVenuesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LocalVenuesFragment newInstance() {
        LocalVenuesFragment fragment = new LocalVenuesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(LocalVenueViewModel.class);

        mViewModel.response().observe(this, new Observer<LocalResponse>() {
            @Override
            public void onChanged(@Nullable LocalResponse response) {
                LocalVenuesFragment.this.processResponse(response);
            }
        });

    }

    private void processResponse(LocalResponse response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS_LOAD:
                renderDataState(response.data);
                break;

            case SUCCESS_DELETE:
                renderLocalDelete();
                break;
            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLocalDelete() {

        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Delete Success", Toast.LENGTH_SHORT).show();


    }

    private void renderLoadingState() {
//        greetingTextView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void renderDataState(List<LocalVenue> localVenues) {

        mProgressBar.setVisibility(View.GONE);
        mRecyclerViewList.setAdapter(new LocalVenuesRecyclerViewAdapter(localVenues, this));

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


        mViewModel.loadLocalVenues();

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
    public void onListFragmentInteraction(LocalVenue item) {


            mViewModel.deleteLocalVenue(item);

    }
}
