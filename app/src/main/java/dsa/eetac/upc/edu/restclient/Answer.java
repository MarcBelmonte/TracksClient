package dsa.eetac.upc.edu.restclient;

import com.google.gson.annotations.SerializedName;

public class Answer { //NO ENTIENDO ESTO
    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    public int score;

    @Override
    public String toString() {
        return answerId + " - Score: " + score + " - Accepted: " + (accepted ? "Yes" : "No");
    }
}
