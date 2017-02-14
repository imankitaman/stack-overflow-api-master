package ankit.com.taskmaster.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ankit.com.taskmaster.BuildConfig;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Tag;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ankit on 14/02/17.
 */
public class NetworkManager extends ApiController {

    ApiProvider apiProvider;

    public NetworkManager(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiProvider = retrofit.create(ApiProvider.class);

    }


    public Observable<Items<Tag>> loadPopularTags(){
        Observable<Response<Items<Tag>>> callTags = apiProvider.loadTags();
        return handleApiObservable(callTags);
    }


    public Observable<Items<Question>> loadUnAnsweredQuestions(String tagName ){
        Observable<Response<Items<Question>>> unAnsweredQuestionscall = apiProvider.loadUnAnsweredQuestions(tagName);
        return handleApiObservable(unAnsweredQuestionscall);
    }

    public Observable<Items<Question>>  loadAnsweredQuestions(String tagName ){
        Observable<Response<Items<Question>>>  call = apiProvider.loadNoAnsweredQuestions(tagName);
        return handleApiObservable(call);
    }

    public Observable<Items<Question>> loadCommentFromQuestion(int id){
        Observable<Response<Items<Question>>> call = apiProvider.loadCommentsFromQuestion(id);
        return handleApiObservable(call);
    }





}
