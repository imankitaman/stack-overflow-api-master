package ankit.com.taskmaster.models;

import java.io.Serializable;

/**
 * Created by ankit on 14/02/17.
 */
public class Owner implements Serializable {
    String reputation;
    int user_id;
    String user_type;
    String profile_image;
    String display_name;
    String link;

    public String getReputation() {
        return reputation;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getLink() {
        return link;
    }
}
