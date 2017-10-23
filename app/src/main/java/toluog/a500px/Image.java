package toluog.a500px;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by oguns on 10/21/2017.
 */

public class Image implements Parcelable {
    private int id, votesCount;
    private String name, description, createdAt, imageUrl, userName, userPic;

    public Image() {
    }

    public Image(int id, String name, String description, String createdAt, String imageUrl, String userName, String userPic, int votesCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.userPic = userPic;
        this.votesCount = votesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    @Override
    public String toString() {
        return "[" + userName + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeString(this.imageUrl);
        dest.writeString(this.userName);
        dest.writeString(this.userPic);
        dest.writeInt(this.votesCount);
    }

    protected Image(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.imageUrl = in.readString();
        this.userName = in.readString();
        this.userPic = in.readString();
        this.votesCount = in.readInt();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
