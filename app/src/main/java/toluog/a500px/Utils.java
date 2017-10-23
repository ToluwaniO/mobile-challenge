package toluog.a500px;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by oguns on 10/21/2017.
 */

public class Utils {

    static String ID = "id";
    static String NAME = "name";
    static String DESCRIPTION = "description";
    static String CREATED_AT = "created_at";
    static String IMAGE_URL = "image_url";
    static String USERNAME = "username";
    static String USERPIC = "userpic_url";
    static String BODY = "body";
    static String VOTES_COUNT = "votes_count";
    static String TAG = Utils.class.getSimpleName();

    public static String getPictureData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try{
            Response response = client.newCall(request).execute();
            //Log.d(TAG, response.body().string());
            return response.body().string();
        }catch (IOException e){
            Log.d(TAG, e.getMessage() + " IO");
        }catch (IllegalStateException e){
            Log.d(TAG, e.getMessage() + " Illegal");
        }
        return null;
    }

    public static ArrayList<Image> getList(String data){
        ArrayList<Image> images = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(data);
            JSONArray item = root.getJSONArray("photos");
            Log.d(TAG, "Length: " + item.length());

            for (int i = 0; i < item.length(); i++){
                JSONObject j = item.getJSONObject(i);
                int id = j.getInt(ID);
                int votesCount = j.getInt(VOTES_COUNT);
                String name = j.getString(NAME);
                String desc = j.getString(DESCRIPTION);
                String createdAt = j.getString(CREATED_AT);
                String imageUrl = j.getString(IMAGE_URL);
                String userName = j.getJSONObject("user").getString(USERNAME);
                String userPic = j.getJSONObject("user").getString(USERPIC);
                Image image = new Image(id, name, desc, createdAt, imageUrl, userName, userPic, votesCount);
                images.add(image);
            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
        return images;
    }

    public static String getCommentData(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e){
            Log.d(TAG, e.getMessage() + " IO");
        }catch (IllegalStateException e){
            Log.d(TAG, e.getMessage() + " Illegal");
        }
        return null;
    }

    public static ArrayList<Comment> getCommentList(String data){
        ArrayList<Comment> commentList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            JSONArray comments = root.getJSONArray("comments");

            for (int i = 0; i < comments.length(); i++){
                JSONObject comment = comments.getJSONObject(i);
                int id = comment.getInt(ID);
                String body = comment.getString(BODY);
                String createdAt = comment.getString(CREATED_AT);
                String userName = comment.getJSONObject("user").getString(USERNAME);
                String userPicUrl = comment.getJSONObject("user").getString(USERPIC);
                Comment c = new Comment(id, userName, createdAt, body, userPicUrl);
                commentList.add(c);
                Log.d(TAG, c.toString());
            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
        return commentList;
    }
}
