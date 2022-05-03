package com.example.quotes.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quotes.Home_Screen.Category_Homepage;
import com.example.quotes.Modal.Modal_Data_Quotes;
import com.example.quotes.R;

import java.util.ArrayList;
import java.util.List;

public class Rv_Adapter extends RecyclerView.Adapter<Rv_Adapter.ViewDataHolder> {
    Activity activity;
    List<Modal_Data_Quotes> l2 = new ArrayList<>();


    public Rv_Adapter(Category_Homepage category_homepage, List<Modal_Data_Quotes> l2) {
        activity = category_homepage;
        this.l2 = l2;
    }

    @NonNull
    @Override
    public ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_2, parent, false);

        return new ViewDataHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewDataHolder holder, int position) {

        holder.slogan_txt.setText(l2.get(position).getS());

    }

    @Override
    public int getItemCount() {
        return l2.size();
    }

    class ViewDataHolder extends RecyclerView.ViewHolder {
        TextView slogan_txt;


        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            slogan_txt = itemView.findViewById(R.id.slogan_txt);

        }
    }
}