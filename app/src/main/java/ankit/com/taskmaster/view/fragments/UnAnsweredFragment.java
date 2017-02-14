package ankit.com.taskmaster.view.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Answer;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.network.NetworkManager;
import ankit.com.taskmaster.uiutils.Constants;
import ankit.com.taskmaster.uiutils.DividerItemDecoration;
import ankit.com.taskmaster.uiutils.MyProgressDialog;
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
public class UnAnsweredFragment extends Fragment implements Callback<Items<Answer>>, ActivityEventListener {

    private static final String TAG = UnAnsweredFragment.class.getSimpleName();
    @Bind(R.id.recycleViewUNAnswered)
    RecyclerView recycleViewUNAnswered;
    Call<Items<Answer>> call;
    private AnswerAdapter adapter;
    String tagName;
    private MyProgressDialog progressDialog;

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
        progressDialog = new MyProgressDialog(getActivity());
        NetworkManager networkmanager = new NetworkManager();
        call=networkmanager.loadUnAnsweredQuestions(tagName);
        call.enqueue(this);
        progressDialog.showProgressDialog(TAG,false);


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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(call != null)
            call.cancel();
    }

    @Override
    public void onResponse(Call<Items<Answer>> call, Response<Items<Answer>> response) {
        adapter.setAnswerItems(response.body().getItems());
        adapter.notifyDataSetChanged();
        progressDialog.cancel();
    }

    @Override
    public void onFailure(Call<Items<Answer>> call, Throwable t) {

    }

    @Override
    public void onBackPress() {
        getActivity().finish();
    }
}
