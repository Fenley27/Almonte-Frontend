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
    List<Clients> clientList;

    public ServerAdapter(Context context, List<Clients> serverList) {
        this.context = context;
        this.clientList = serverList;
    }

    @NonNull
    @Override
    public serverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ServerAdapter.serverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull serverViewHolder holder, int position) {
        holder.tvName.setText(clientList.get(position).getName());
        holder.tvUsername.setText(clientList.get(position).getApellido());
        holder.tvEmail.setText(clientList.get(position).getCiudad());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class serverViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvUsername, tvEmail;
        public serverViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
