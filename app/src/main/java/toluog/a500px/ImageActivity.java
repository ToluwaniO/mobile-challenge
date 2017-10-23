package toluog.a500px;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class ImageActivity extends AppCompatActivity {

    String TAG = ImageActivity.class.getSimpleName();
    ArrayList<Comment> commentsList;
    TextView votesCount;
    Button buttonComments;
    String URL = "https://api.500px.com/v1/photos/";
    Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Bundle b = getIntent().getExtras();
        image = b.getParcelable("image");
        URL += image.getId() + "/comments";
        commentsList = new ArrayList<>();

        ImageView imageItem = (ImageView) findViewById(R.id.image);
        ImageView profileView = (ImageView) findViewById(R.id.profile_image);
        TextView profileName = (TextView) findViewById(R.id.profile_name);
        votesCount = (TextView) findViewById(R.id.votes_count);
        buttonComments = (Button) findViewById(R.id.view_more);

        Glide.with(this).load(image.getImageUrl()).into(imageItem);
        Glide.with(this).load(image.getUserPic()).into(profileView);
        profileName.setText(image.getUserName());
        votesCount.setText(image.getVotesCount() + "");

        buttonComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("comments", commentsList);
                Intent intent = new Intent(ImageActivity.this, CommentsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        new getData().execute();
    }

    public void addComment(Comment comment){
        ImageView profilePic = findViewById(R.id.comment_profile_image);
        TextView profileName = (TextView)findViewById(R.id.user_name);
        TextView commentBody = (TextView)findViewById(R.id.comment_body);

        Glide.with(getApplicationContext()).load(comment.getUserPicUrl()).into(profilePic);
        profileName.setText(comment.getUserName());
        commentBody.setText(comment.getBody());
    }

    class getData extends AsyncTask<Void, Void, ArrayList<Comment>> {

        @Override
        protected ArrayList<Comment> doInBackground(Void... voids) {
            String dataStr = Utils.getCommentData(URL);
            Log.d(TAG, dataStr);
            return Utils.getCommentList(dataStr);
        }

        @Override
        protected void onPostExecute(ArrayList<Comment> comments) {
            super.onPostExecute(comments);
            for (Comment i : comments)
            {
                commentsList.add(i);
            }
            //commentAdapter.notifyDataSetChanged();

            if(commentsList.size() == 0){
//                recyclerView.setVisibility(View.GONE);
//                noComments.setVisibility(View.VISIBLE);
            }
            else if(commentsList.size() > 0){
                addComment(comments.get(0));
            }
        }
    }
}
