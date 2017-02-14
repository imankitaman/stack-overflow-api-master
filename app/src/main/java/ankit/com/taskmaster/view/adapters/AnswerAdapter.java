package ankit.com.taskmaster.view.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Answer;
import ankit.com.taskmaster.uiutils.ModuleMaster;
import ankit.com.taskmaster.view.viewHolders.AnswerViewHolder;

/**
 * Created by ankit on 14/02/17.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

    private static final String TAG = AnswerAdapter.class.getSimpleName();
    private List<Answer> answerItems;
    private Context context;

    public AnswerAdapter() {
        answerItems = new ArrayList<>();
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_answer, parent, false);
        context = parent.getContext();
        return new AnswerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        final Answer answer = answerItems.get(position);

        final Pair[] viewStringPair = new Pair[]{Pair.create(holder.userPhoto, holder.itemView.getResources().getString(R.string.transition_image)),
                Pair.create((View) holder.tvQuestion, holder.itemView.getResources().getString(R.string.transition_title))};
        Glide.with(context).load(answer.getOwner().getProfile_image()).into(holder.userPhoto);
        holder.tvAuthor.setText(context.getResources().getString(R.string.author, answer.getOwner().getDisplay_name()));
        holder.tvQuestion.setText(answer.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModuleMaster.navigateToAnswersDetails(context, answer,viewStringPair);

            }
        });
    }

    @Override
    public int getItemCount() {
        return answerItems == null ? 0 : answerItems.size();
    }

    public void setAnswerItems(List<Answer> answerItems) {
        this.answerItems = answerItems;
    }


}
