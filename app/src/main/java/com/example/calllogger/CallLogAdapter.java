package com.example.calllogger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.ViewHolder> {

    private final List<CallLog> mCallLogList;

    public CallLogAdapter() {
        mCallLogList = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_log_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        assert mCallLogList != null;
        CallLog item = mCallLogList.get(position);
        holder.numberTextView.setText(item.getNumber());
        holder.dateTextView.setText(item.getDate());
        holder.typeTextView.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        assert mCallLogList != null;
        return mCallLogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView numberTextView;
        public final TextView dateTextView;
        public final TextView typeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }
    }
}

