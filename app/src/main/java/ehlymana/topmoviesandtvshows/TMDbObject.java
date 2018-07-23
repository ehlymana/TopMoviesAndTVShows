package ehlymana.topmoviesandtvshows;

import android.os.Parcel;
import android.os.Parcelable;

public class TMDbObject implements Parcelable {
    String name, description, image;
    public TMDbObject () { }
    public TMDbObject (String n, String d, String i) {
        name = n;
        description = d;
        image = i;
    }
    public void setName (String n) {
        name = n;
    }
    public String getName () {
        return name;
    }
    public void setDescription (String d) {
        description = d;
    }
    public String getDescription () {
        return description;
    }
    public void setImage (String i) {
        image = i;
    }
    public String getImage () {
        return image;
    }
    protected TMDbObject(Parcel in) {
        name=in.readString();
        description=in.readString();
        image=in.readString();
    }
    public static final Creator<TMDbObject> CREATOR=new Creator<TMDbObject> () {
        @Override
        public TMDbObject createFromParcel (Parcel in) {
            return new TMDbObject(in);
        }
        @Override
        public TMDbObject[] newArray (int size) {
            return new TMDbObject[size];
        }
    };
    @Override
    public int describeContents () {
        return 0;
    }
    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
    }
}