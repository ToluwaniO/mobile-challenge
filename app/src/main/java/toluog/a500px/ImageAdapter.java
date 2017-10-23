package toluog.a500px;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by oguns on 10/21/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Image image);
    }

    private ArrayList<Image> images;
    private Context context;
    private final OnItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder   {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRoot);
        }

        public void bind(final Image image, final OnItemClickListener onItemClickListener){
            Glide.with(imageView.getContext()).load(image.getImageUrl()).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(image);
                }
            });
        }
    }

    public ImageAdapter(Context context, ArrayList<Image> images, OnItemClickListener listener) {
        this.images = images;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(images.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }



}
