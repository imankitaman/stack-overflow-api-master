package ankit.com.taskmaster.network;

import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Tag;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ankit on 14/02/17.
 */

public interface ApiProvider {

    // Popular Tags List
    @GET("tags?order=desc&sort=popular&site=stackoverflow")
    Observable<Response<Items<Tag>>> loadTags();

    //UnAnswered Questions
    @GET("questions/unanswered?&filter=withbody&site=stackoverflow")
    Observable<Response<Items<Question>>> loadUnAnsweredQuestions(@Query("tagged") String tagName);

    //AnsweredQuestions
    @GET("questions/no-answers?&filter=withbody&site=stackoverflow")
    Observable<Response<Items<Question>>> loadNoAnsweredQuestions(@Query("tagged") String tagName);

    //comments
    @GET("questions/{id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    Observable<Response<Items<Question>>> loadCommentsFromQuestion(@Path("id") int id);

}
