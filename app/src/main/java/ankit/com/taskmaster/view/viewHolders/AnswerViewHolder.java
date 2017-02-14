package ankit.com.taskmaster.view.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ankit.com.taskmaster.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class AnswerViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.userPhoto)
    public ImageView userPhoto;
    @Bind(R.id.tvQuestion)
    public TextView tvQuestion;
    @Bind(R.id.separator)
    public View separator;
    @Bind(R.id.tvAuthor)
    public TextView tvAuthor;
    @Bind(R.id.tvVotes)
    public TextView tvVotes;
    @Bind(R.id.tvAnswerCount)
    public TextView tvAnswerCount;
    @Bind(R.id.tvViews)
    public TextView tvViews;
    @Bind(R.id.llAnswer)
    public LinearLayout llAnswer;
    @Bind(R.id.cvItemQuestion)
    public CardView cvItemQuestion;

    public AnswerViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
