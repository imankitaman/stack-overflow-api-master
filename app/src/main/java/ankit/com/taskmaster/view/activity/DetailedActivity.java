package ankit.com.taskmaster.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Answer;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.network.NetworkManager;
import ankit.com.taskmaster.uiutils.Constants;
import ankit.com.taskmaster.uiutils.MyProgressDialog;
import ankit.com.taskmaster.uiutils.SpaceItemDecoration;
import ankit.com.taskmaster.view.adapters.AnswerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ankit on 14/02/17.
 */
public class DetailedActivity extends AppCompatActivity implements Callback<Items<Answer>> {

    private static final String TAG = DetailedActivity.class.getSimpleName();
    @Bind(R.id.tvQuestion)
    TextView tvQuestion;
    @Bind(R.id.ivPhoto)
    ImageView ivPhoto;
    @Bind(R.id.tvOwnerName)
    TextView tvOwnerName;
    @Bind(R.id.relativeLayComments)
    RecyclerView relativeLayComments;
    @Bind(R.id.txtError)
    TextView txtError;
    private Call<Items<Answer>> call;

    private Answer answer;
    private MyProgressDialog progressDialog;
    private AnswerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);
        ButterKnife.bind(this);
        progressDialog = new MyProgressDialog(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }
        initRecycleView();
        initToolBar();
        answer = (Answer) getIntent().getSerializableExtra(Constants.BUNDLE_DATA_NAME);
        if (answer != null) {
            updateView(answer);
        }

        NetworkManager networkmanager = new NetworkManager();
        call = networkmanager.loadCommentFromQuestion(Integer.parseInt(answer.getQuestion_id()));
        call.enqueue(this);
        progressDialog.showProgressDialog(TAG, false);
    }


    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailedActivity.this);
        relativeLayComments.setLayoutManager(layoutManager);
        relativeLayComments.addItemDecoration(new SpaceItemDecoration(8));
        adapter = new AnswerAdapter();
        relativeLayComments.setAdapter(adapter);
    }

    private void initToolBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");
    }

    private void updateView(Answer answer) {
        Glide.with(DetailedActivity.this).load(answer.getOwner().getProfile_image()).into(ivPhoto);
        tvQuestion.setText(answer.getTitle());
        tvOwnerName.setText(getResources().getString(R.string.author_name, answer.getOwner().getDisplay_name()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            ActivityCompat.finishAfterTransition(DetailedActivity.this);
        else
            finish();
    }


    @Override
    public void onResponse(Call<Items<Answer>> call, Response<Items<Answer>> response) {
        adapter.setAnswerItems(response.body().getItems());
        checkForResult(response.body().getItems().size());
        adapter.notifyDataSetChanged();
        progressDialog.hideProgressDialog(TAG);
    }

    @Override
    public void onFailure(Call<Items<Answer>> call, Throwable t) {

    }

    public void checkForResult(int size) {
        relativeLayComments.setVisibility(size == 0 ? View.GONE : View.VISIBLE);
        txtError.setVisibility(size == 0 ? View.VISIBLE : View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (call != null)
            call.cancel();
    }
}
