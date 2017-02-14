package ankit.com.taskmaster.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.network.NetworkManager;
import ankit.com.taskmaster.uiutils.Constants;
import ankit.com.taskmaster.uiutils.MyProgressDialog;
import ankit.com.taskmaster.uiutils.SpaceItemDecoration;
import ankit.com.taskmaster.uiutils.Utility;
import ankit.com.taskmaster.view.adapters.AnswerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ankit on 14/02/17.
 */
public class DetailedActivity extends AppCompatActivity {

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
    @Bind(R.id.tvBody)
    TextView tvBody;
    @Bind(R.id.cordLayDetails)
    CoordinatorLayout cordLayDetails;
    private CompositeSubscription compositeSubscription;
    private Question question;
    private MyProgressDialog progressDialog;
    private AnswerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);
        ButterKnife.bind(this);
        compositeSubscription = new CompositeSubscription();
        progressDialog = new MyProgressDialog(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }
        initRecycleView();
        initToolBar();
        question = (Question) getIntent().getSerializableExtra(Constants.BUNDLE_DATA_NAME);
        if (question != null) {
            updateView(question);
        }

        if (Utility.isNetworkConnected()) {
            NetworkManager networkmanager = new NetworkManager();
            progressDialog.showProgressDialog(TAG, false);
            compositeSubscription.add(networkmanager.loadCommentFromQuestion(Integer.parseInt(question.getQuestion_id())).subscribe(new Observer<Items<Question>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    progressDialog.hideProgressDialog(TAG);
                }

                @Override
                public void onNext(Items<Question> questionItems) {
                    adapter.setQuestionItems(questionItems.getItems());
                    checkForResult(questionItems.getItems().size());
                    adapter.notifyDataSetChanged();
                    progressDialog.hideProgressDialog(TAG);
                }
            }));


        } else {
            Utility.showSnackBar(cordLayDetails, this, "No Internet Connection");
        }
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

    private void updateView(Question question) {
        if (!TextUtils.isEmpty(question.getOwner().getProfile_image()))
            Glide.with(DetailedActivity.this).load(question.getOwner().getProfile_image()).into(ivPhoto);
        tvQuestion.setText(Html.fromHtml(question.getTitle()));
        tvOwnerName.setText(getResources().getString(R.string.author_name, question.getOwner().getDisplay_name()));
        if (!TextUtils.isEmpty(question.getBody()))
            tvBody.setText(Html.fromHtml(question.getBody()));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    public void checkForResult(int size) {
        relativeLayComments.setVisibility(size == 0 ? View.GONE : View.VISIBLE);
        txtError.setVisibility(size == 0 ? View.VISIBLE : View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if(compositeSubscription!=null && !compositeSubscription.isUnsubscribed()){
            compositeSubscription.unsubscribe();
        }
    }
}
