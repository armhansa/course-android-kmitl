package kmitl.armhansa.test.app.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.armhansa.test.app.lazyinstagram.R;
import kmitl.armhansa.test.app.lazyinstagram.model.Post;

class Holder extends RecyclerView.ViewHolder {

    public ImageView post;
    public TextView like;
    public TextView comment;

    public Holder(View itemView) {
        super(itemView);
        post = itemView.findViewById(R.id.image);
        like = itemView.findViewById(R.id.like);
        comment = itemView.findViewById(R.id.comment);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private boolean isGrid;

    public PostAdapter(Context context, boolean isGrid) {
        this.context = context;
        this.isGrid = isGrid;
    }

    private Post data[];

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if(isGrid) itemView = inflater.inflate(R.layout.post_item_grid, null, false);
        else itemView = inflater.inflate(R.layout.post_item_list, null, false);
        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView post = holder.post;
        TextView like = holder.like;
        TextView comment = holder.comment;

        Glide.with(context).load(data[position].getUrl()).into(post);
        like.setText(String.valueOf(data[position].getLike()));
        comment.setText(String.valueOf(data[position].getComment()));

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setData(Post data[]) {
        this.data = data;
    }
}
