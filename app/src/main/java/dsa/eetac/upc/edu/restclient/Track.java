package dsa.eetac.upc.edu.restclient;
import com.google.gson.annotations.SerializedName;

public class Track {

  public int id;
  public String singer;
  public String title;

    public Track(int id, String singer, String title){
        this.id = id;
        this.singer = singer;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Track [title=" + title + ", singer=" + singer + "]";
    }
}
