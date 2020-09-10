package com.news.reporter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public ArrayList<NewsContent> mItemList;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_image;
        public TextView item_title;
        public TextView item_source;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            item_source = itemView.findViewById(R.id.item_source);
            item_title = itemView.findViewById(R.id.item_title);
        }
    }

    public ItemAdapter(ArrayList<NewsContent> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(v);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        NewsContent currentItem = mItemList.get(position);

        holder.item_title.setText(currentItem.getTitle());
        holder.item_source.setText(currentItem.getSource());
        //holder.item_image.setImageResource(R.drawable.ic_android);

        Picasso.get().load(currentItem.getImgUrl()).into(holder.item_image);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < mItemList.size(); i++)   {
                    NewsContent NC = mItemList.get(i);
                    String imgURL = NC.getImgUrl();
                    putImageOn(imgURL, holder);
                }
            }
        }).start();*/

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class Download12 extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap mBitmap = BitmapFactory.decodeStream(in);
                return mBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void putImageOn(String url, @NonNull final ItemViewHolder HOLDER) {
        try {
            Download12 download = new Download12();
            final Bitmap bit = download.execute(url).get();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    HOLDER.item_image.setImageBitmap(bit);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
