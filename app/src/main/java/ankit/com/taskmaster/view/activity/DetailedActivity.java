package ankit.com.taskmaster.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Answer;
import ankit.com.taskmaster.uiutils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

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

    private Answer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }
        initToolBar();
        answer = (Answer) getIntent().getSerializableExtra(Constants.BUNDLE_DATA_NAME);
        if (answer != null) {
            updateView(answer);
        }
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


}
