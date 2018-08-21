package com.moises.ironbit.lobby;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.moises.ironbit.R;
import com.moises.ironbit.common.dao.LocalVenue;

import java.util.List;


public class LocalVenuesRecyclerViewAdapter extends RecyclerView.Adapter<LocalVenuesRecyclerViewAdapter.ViewHolder> {

    private final List<LocalVenue> mValues;
    private final OnListFragmentInteractionListener mListener;

    public LocalVenuesRecyclerViewAdapter(List<LocalVenue> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(holder.mItem.getName());
        holder.mAddressView.setText(holder.mItem.getAddress());

        holder.mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteractionSaveValue(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mNameView;
        private final TextView mAddressView;
        private final CheckBox mAddView;
        private LocalVenue mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.name);
            mAddressView =  view.findViewById(R.id.address);
            mAddView =  view.findViewById(R.id.tbAdd);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }

    public interface OnListFragmentInteractionListener{

        void onListFragmentInteraction(LocalVenue item);
    }
}
