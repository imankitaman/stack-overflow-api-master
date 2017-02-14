package ankit.com.taskmaster.models;
import java.io.Serializable;

/**
 * Created by ankit on 14/02/17.
 */
public class Question implements Serializable {
    Owner owner;
    String answer_id;
    String question_id;
    String body;
    String link;
    String title;
    int view_count;
    int answer_count;
    int score;

    public Owner getOwner() {
        return owner;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getBody() {
        return body;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public int getView_count() {
        return view_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getScore() {
        return score;
    }
}
