package com.example.healthcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.ViewHolder> {
    private HistModel[] histModel;



    // RecyclerView recyclerView;

    public HistAdapter(HistModel[] histModel) {
        this.histModel=histModel;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.history_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HistModel myhistModel = histModel[position];
        holder.textView.setText(histModel[position].getDate());
        holder.textView1.setText(histModel[position].getTime());
        holder.textView2.setText(histModel[position].getTemperature());
        holder.textView3.setText(histModel[position].getPulse());
        holder.textView4.setText(histModel[position].getOxygen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item of date "+myhistModel.getDate(),Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return histModel.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.textView1 = (TextView) itemView.findViewById(R.id.textView1);
            this.textView2 = (TextView) itemView.findViewById(R.id.textView2);
            this.textView3 = (TextView) itemView.findViewById(R.id.textView3);
            this.textView4 = (TextView) itemView.findViewById(R.id.textView4);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }


    }

}
