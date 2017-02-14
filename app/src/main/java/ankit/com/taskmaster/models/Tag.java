package ankit.com.taskmaster.models;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ankit on 14/02/17.
 */
public class Tag {

    boolean has_synonyms;
    boolean is_moderator_only;
    boolean is_required;
    int count;
    @SerializedName("name")
    String tagName;

    public boolean isHas_synonyms() {
        return has_synonyms;
    }

    public boolean is_moderator_only() {
        return is_moderator_only;
    }

    public boolean is_required() {
        return is_required;
    }

    public int getCount() {
        return count;
    }
    public String getTagName() {
        return tagName;
    }
}
