package com.example.hellojbhbjbjj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.serverViewHolder> {

    Context context;
    List<Users> serverList;

    public ServerAdapter(Context context, List<Users> serverList) {
        this.context = context;
        this.serverList = serverList;
    }

    @NonNull
    @Override
    public serverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, null, false);
        return new ServerAdapter.serverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull serverViewHolder holder, int position) {
        holder.tvName.setText(serverList.get(position).getName());
        holder.tvUsername.setText(serverList.get(position).getUsername());
        holder.tvEmail.setText(serverList.get(position).getEmail());
        holder.tvPhone.setText(serverList.get(position).getPhone());
        holder.tvWebsite.setText(serverList.get(position).getWebsite());
    }

    @Override
    public int getItemCount() {
        return serverList.size();
    }

    public class serverViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvUsername, tvEmail, tvPhone, tvWebsite;
        public serverViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
        }
    }
}
