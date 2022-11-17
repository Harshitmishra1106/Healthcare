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
    private final HistModel[] histData;



    // RecyclerView recyclerView;

    public HistAdapter(HistModel[] histData) {
        this.histData=histData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.history_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        HistModel histModel = histData[position];
        holder.textView.setText(histModel.getDate());
        holder.textView1.setText(histModel.getTime());
        holder.textView2.setText(histModel.getTemperature());
        holder.textView3.setText(histModel.getPulse());
        holder.textView4.setText(histModel.getOxygen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "records ", Toast.LENGTH_SHORT).show();
            }
        });
        


    }


    @Override
    public int getItemCount() {
        return histData.length;
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
            this.textView =  itemView.findViewById(R.id.textView);
            this.textView1 =  itemView.findViewById(R.id.textView1);
            this.textView2 =  itemView.findViewById(R.id.textView2);
            this.textView3 =  itemView.findViewById(R.id.textView3);
            this.textView4 =  itemView.findViewById(R.id.textView4);
            linearLayout =  itemView.findViewById(R.id.linearLayout);
        }


    }

}
