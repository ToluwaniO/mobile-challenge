package toluog.a500px;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    String URL = "https://api.500px.com/v1/photos?feature=popular&image_size=4&consumer_key=csZEl3Qn21mahInULkFSU7WhLaewdblx3HxXBsDF";
    String TAG = MainActivity.class.getSimpleName();
    ArrayList<Image> imageList;
    ImageAdapter adapter;
    RecyclerView recyclerView;
    ImageAdapter.OnItemClickListener listener;
    LinearLayout loaderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = (ImageAdapter.OnItemClickListener) this;
        imageList = new ArrayList<>();
        adapter = new ImageAdapter(this, imageList, listener);
        loaderLayout = (LinearLayout)findViewById(R.id.loader_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        new getData().execute();
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("image", (Parcelable) image);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    class getData extends AsyncTask<Void, Void, ArrayList<Image>>{

        @Override
        protected ArrayList<Image> doInBackground(Void... voids) {
            String dataStr = Utils.getPictureData(URL);
            if(dataStr != null) Log.d(TAG, dataStr);
            return Utils.getList(dataStr);
        }

        @Override
        protected void onPostExecute(ArrayList<Image> images) {
            super.onPostExecute(images);
            for (Image i : images)
            {
                imageList.add(i);
            }
            loaderLayout.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }
}
