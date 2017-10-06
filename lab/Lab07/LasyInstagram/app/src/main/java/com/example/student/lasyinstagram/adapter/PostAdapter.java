package com.example.student.lasyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.student.lasyinstagram.MainActivity;
import com.example.student.lasyinstagram.R;

class Holder extends RecyclerView.ViewHolder {

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}

public class PostAdapter
extends RecyclerView.Adapter<Holder>{


    private Context context;

    String data[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png"
    };

    public PostAdapter(MainActivity mainActivity) {
        this.context = mainActivity;
    }

    @Override
    public com.example.student.lasyinstagram.adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.post_item, null, false);

        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(com.example.student.lasyinstagram.adapter.Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
