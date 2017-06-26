package iordache.cristian.bakeyourrecipe.RecipeList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class RecipeStepsClass implements Parcelable{
    public RecipeStepsClass(int stepID, String sDescription, String description, String videoURL, String thumbnail) {
        this.stepID = stepID;
        this.sDescription = sDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnail = thumbnail;
    }

    private int stepID;
    private String sDescription;
    private String description;
    private String videoURL;
    private String thumbnail;

    public RecipeStepsClass() {
    }

    protected RecipeStepsClass(Parcel in) {
        stepID = in.readInt();
        sDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<RecipeStepsClass> CREATOR = new Creator<RecipeStepsClass>() {
        @Override
        public RecipeStepsClass createFromParcel(Parcel in) {
            return new RecipeStepsClass(in);
        }

        @Override
        public RecipeStepsClass[] newArray(int size) {
            return new RecipeStepsClass[size];
        }
    };

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(stepID);
        parcel.writeString(sDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnail);
    }
}
