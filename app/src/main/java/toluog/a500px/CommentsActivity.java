package toluog.a500px;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    ArrayList<Comment> commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        commentsList = getIntent().getParcelableArrayListExtra("comments");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        commentAdapter = new CommentAdapter(this, commentsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(commentAdapter);
    }
}
