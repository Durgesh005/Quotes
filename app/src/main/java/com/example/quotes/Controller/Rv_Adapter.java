package com.example.quotes.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quotes.Home_Screen.Category_Homepage;
import com.example.quotes.Modal.Modal_Data_Quotes;
import com.example.quotes.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rv_Adapter extends RecyclerView.Adapter<Rv_Adapter.ViewDataHolder> {
    Activity activity;
    List<Modal_Data_Quotes> l2 = new ArrayList<>();

    public int i = 0;

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
        String text = l2.get(position).getS();
        int imagePath = l2.get(position).getImages();
        holder.slogan_txt.setText(l2.get(position).getS());


        holder.copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Copy("", text);
                Toast.makeText(activity, "Copied", Toast.LENGTH_SHORT).show();

            }
        });
        holder.like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < 12) {
                    holder.like_btn.setImageResource(R.drawable.ic_baseline_favorite_24);
                    i++;
                    if (i >= 12) {
                        i = 0;

                    }

                }
            }
        });
        holder.change_images_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i < 12) {
                    holder.change_images_btn.setImageResource(l2.get(i).getImages());

                    i++;

                    if (i >= 12) {
                        i = 0;
                    }

                }

            }
        });
        holder.download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Download("", text);
            }
        });
        holder.share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Hello USER,\nPlease Rate Quotes App On Play Store\n⭐⭐⭐⭐⭐\n\nYOUR QUOTE\n \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n\n" + text;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, text);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });

    }

    @Override
    public int getItemCount() {
        return l2.size();
    }

    class ViewDataHolder extends RecyclerView.ViewHolder {
        TextView slogan_txt;
        LinearLayout copy_btn, download_btn, share_btn;
        ImageView change_images_btn, like_btn;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            slogan_txt = itemView.findViewById(R.id.slogan_txt);
            copy_btn = itemView.findViewById(R.id.copy_btn);
            change_images_btn = itemView.findViewById(R.id.change_images_btn);
            like_btn = itemView.findViewById(R.id.like_btn);
            download_btn = itemView.findViewById(R.id.download_btn);
            share_btn = itemView.findViewById(R.id.share_btn);
        }

    }


    void Copy(String s, String text) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(activity.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(s, text);
        clipboard.setPrimaryClip(clip);
    }

    void Download(String s, String text) {
        DownloadManager manager = (DownloadManager) activity.getSystemService(activity.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(text);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        long reference = manager.enqueue(request);
    }


}
