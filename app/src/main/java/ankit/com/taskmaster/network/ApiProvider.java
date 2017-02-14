package ankit.com.taskmaster.network;

import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Tag;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ankit on 14/02/17.
 */

public interface ApiProvider {

    // Popular Tags List
    @GET("tags?order=desc&sort=popular&site=stackoverflow")
    Call<Items<Tag>> loadTags();

    //UnAnsweredFragment Questions
    @GET("questions/unanswered?&filter=withbody")
    Call<Items<Question>> loadUnAnsweredQuestions(@Query("tagged") String tagName , @Query("site")String stackoverflow);

    //AnsweredQuestions
    @GET("questions/no-answers?&filter=withbody")
    Call<Items<Question>> loadNoAnsweredQuestions(@Query("tagged") String tagName, @Query("site")String stackoverflow);

    @GET("questions/{id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    Call<Items<Question>> loadCommentsFromQuestion(@Path("id") int id);

}
