package ankit.com.taskmaster.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.network.NetworkManager;
import ankit.com.taskmaster.uiutils.Constants;
import ankit.com.taskmaster.uiutils.SpaceItemDecoration;
import ankit.com.taskmaster.view.activity.ActivityEventListener;
import ankit.com.taskmaster.view.adapters.AnswerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ankit on 14/02/17.
 */
public class AnsweredFragment extends Fragment implements Callback<Items<Question>> ,ActivityEventListener{

    private static final String TAG = AnsweredFragment.class.getSimpleName();
    @Bind(R.id.recycleViewUNAnswered)
    RecyclerView recycleViewUNAnswered;

    Call<Items<Question>> call;
    private AnswerAdapter adapter;
    String tagName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unanswered,
                container, false);
        ButterKnife.bind(this, view);
        tagName = getArguments().getString(Constants.CONSTANT_TAG_NAME);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        NetworkManager networkmanager = new NetworkManager();
        call=networkmanager.loadAnsweredQuestions(tagName);
        call.enqueue(this);
    }

    private void initRecycleView() {
        recycleViewUNAnswered.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewUNAnswered.setLayoutManager(layoutManager);
        recycleViewUNAnswered.addItemDecoration(new SpaceItemDecoration(2));
        adapter = new AnswerAdapter();
        recycleViewUNAnswered.setAdapter(adapter);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(call != null)
            call.cancel();
    }

    @Override
    public void onResponse(Call<Items<Question>> call, Response<Items<Question>> response) {
        adapter.setQuestionItems(response.body().getItems());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<Items<Question>> call, Throwable t) {

    }

    @Override
    public void onBackPress() {
        getActivity().finish();
    }
}
