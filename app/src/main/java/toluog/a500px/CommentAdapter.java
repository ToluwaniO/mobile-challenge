package toluog.a500px;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by oguns on 10/22/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    ArrayList<Comment> comments;

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePic;
        TextView userName, body;
        public ViewHolder(View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.user_name);
            body = itemView.findViewById(R.id.comment_body);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(comments.get(position).getUserPicUrl()).into(holder.profilePic);
        holder.userName.setText(comments.get(position).getUserName());
        holder.body.setText(comments.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
