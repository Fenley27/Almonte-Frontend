package com.example.hellojbhbjbjj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.servidorViewHolder> {

    Context context;
    List<Loans> localList;

    public LocalAdapter(Context context, List<Loans> localList) {
        this.context = context;
        this.localList = localList;
    }

    @NonNull
    @Override
    public servidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new LocalAdapter.servidorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull servidorViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return localList.size();
    }

    public class servidorViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvUsername, tvEmail, tvPhone, tvWebsite;

        public servidorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}