package com.moises.ironbit.lobby;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.moises.ironbit.R;
import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.model.venues.Item;
import com.moises.ironbit.common.model.venues.Venue;

import java.util.List;


public class VenuesRecyclerViewAdapter extends RecyclerView.Adapter<VenuesRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final List<LocalVenue> mLocalValues;
    private final OnListFragmentInteractionListener mListener;

    public VenuesRecyclerViewAdapter(List<Item> items, List<LocalVenue> localVenues, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mLocalValues = localVenues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position).getVenue();
        holder.mNameView.setText(holder.mItem.getName());
        holder.mAddressView.setText(holder.mItem.getLocation().getAddress());
//        holder.mAddView.setChecked(validateLocal(holder.mItem.getId()));
        holder.mAddView.setChecked(validateLocal(holder.mItem.getId()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)mListener.onListFragmentInteractionGoToVenue(holder.mItem);
            }
        });

        holder.mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null)mListener.onListFragmentInteractionSaveValue(holder.mItem);
            }
        });

    }

    public boolean validateLocal(String id) {
        for (LocalVenue l :
                mLocalValues) {
            if (id.equals(l.getFoursquareId())) return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public LocalVenue getLocalVenue(String id) {
        for (LocalVenue l :
                mLocalValues) {
            if (id.equals(l.getFoursquareId())) return l;
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mNameView;
        private final TextView mAddressView;
        private final CheckBox mAddView;
        private final View mView;
        private Venue mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.name);
            mAddressView = view.findViewById(R.id.address);
            mAddView = view.findViewById(R.id.tbAdd);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }

    public interface OnListFragmentInteractionListener {

        void onListFragmentInteractionSaveValue(Venue item);

        void onListFragmentInteractionGoToVenue(Venue item);
    }
}
