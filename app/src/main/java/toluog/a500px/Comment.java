package toluog.a500px;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by oguns on 10/22/2017.
 */

public class Comment implements Parcelable {
    int id;
    String userName, createdAt, body, userPicUrl;

    public Comment() {
    }

    public Comment(int id, String userName, String createdAt, String body, String userPicUrl) {
        this.id = id;
        this.userName = userName;
        this.createdAt = createdAt;
        this.body = body;
        this.userPicUrl = userPicUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    @Override
    public String toString() {
        return "[userName: " + getUserName() + " body: " + getBody() + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.createdAt);
        dest.writeString(this.body);
        dest.writeString(this.userPicUrl);
    }

    protected Comment(Parcel in) {
        this.id = in.readInt();
        this.userName = in.readString();
        this.createdAt = in.readString();
        this.body = in.readString();
        this.userPicUrl = in.readString();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
