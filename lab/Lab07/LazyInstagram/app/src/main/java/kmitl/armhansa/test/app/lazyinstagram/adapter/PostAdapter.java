package kmitl.armhansa.test.app.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.armhansa.test.app.lazyinstagram.R;
import kmitl.armhansa.test.app.lazyinstagram.model.Image;

class Holder extends RecyclerView.ViewHolder {

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    private Image data[];

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position].getUrl()).into(image);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setData(Image data[]) {
        this.data = data;
    }
}
