package ankit.com.taskmaster.models;

/**
 * Created by ankit on 14/02/17.
 */
public class Answer {
    Owner owner;
    String answer_id;
    String question_id;
    String body;
    String link;
    String title;

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
}
