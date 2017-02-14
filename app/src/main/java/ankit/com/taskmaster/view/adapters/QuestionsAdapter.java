package ankit.com.taskmaster.view.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.uiutils.ModuleMaster;
import ankit.com.taskmaster.view.viewHolders.AnswerViewHolder;

/**
 * Created by ankit on 14/02/17.
 */
public class QuestionsAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

    private static final String TAG = QuestionsAdapter.class.getSimpleName();
    private List<Question> questionItems;
    private Context context;

    public QuestionsAdapter() {
        questionItems = new ArrayList<>();
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_question, parent, false);
        context = parent.getContext();
        return new AnswerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        final Question question = questionItems.get(position);
        final Pair[] viewStringPair = new Pair[]{Pair.create(holder.userPhoto, holder.itemView.getResources().getString(R.string.transition_image)),
                Pair.create((View) holder.tvQuestion, holder.itemView.getResources().getString(R.string.transition_title)),
                Pair.create((View) holder.tvAuthor, holder.itemView.getResources().getString(R.string.transition_authorName))};
        if (!TextUtils.isEmpty(question.getOwner().getProfile_image()))
        Glide.with(context).load(question.getOwner().getProfile_image()).into(holder.userPhoto);
        holder.tvAuthor.setText(context.getResources().getString(R.string.author, question.getOwner().getDisplay_name()));
        if (!TextUtils.isEmpty(question.getTitle())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvQuestion.setText(Html.fromHtml(question.getTitle(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                holder.tvQuestion.setText(Html.fromHtml(question.getTitle()));
            }
        }
        holder.tvVotes.setText(context.getResources().getString(R.string.vote, question.getScore()));
        holder.tvAnswerCount.setText(context.getResources().getString(R.string.ans_count, question.getAnswer_count()));
        holder.tvViews.setText(context.getResources().getString(R.string.views, question.getView_count()));
        holder.itemView.setOnClickListener(view -> ModuleMaster.navigateToAnswersDetails(context, question,viewStringPair));
    }

    @Override
    public int getItemCount() {
        return questionItems == null ? 0 : questionItems.size();
    }

    public void setQuestionItems(List<Question> questionItems) {
        this.questionItems = questionItems;
    }


}
