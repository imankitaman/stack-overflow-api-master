package ankit.com.taskmaster.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ankit.com.taskmaster.BuildConfig;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Tag;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankit on 14/02/17.
 */
public class NetworkManager {

    ApiProvider apiProvider;

    public NetworkManager(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
//                TODO  add base url
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiProvider = retrofit.create(ApiProvider.class);

    }


    public Call<Items<Tag>> loadPopularTags(){
        Call<Items<Tag>> callTags = apiProvider.loadTags();
        return callTags;
    }


    public Call<Items<Question>> loadUnAnsweredQuestions(String tagName ){
        Call<Items<Question>> unAnsweredQuestionscall = apiProvider.loadUnAnsweredQuestions(tagName);
        return unAnsweredQuestionscall;
    }

    public Call<Items<Question>> loadAnsweredQuestions(String tagName ){
        Call<Items<Question>> call = apiProvider.loadNoAnsweredQuestions(tagName);
        return call;
    }

    public Call<Items<Question>> loadCommentFromQuestion(int id){
        Call<Items<Question>> call = apiProvider.loadCommentsFromQuestion(id);
        return call;
    }





}
