package ankit.com.taskmaster.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Tag;
import ankit.com.taskmaster.uiutils.ModuleMaster;
import ankit.com.taskmaster.view.viewHolders.TagViewHolder;

public class TagsAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private List<Tag> tagsData;
    private Context mContext;

    public TagsAdapter() {
        tagsData = new ArrayList<>();
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tags, parent, false);
        mContext = parent.getContext();
        return new TagViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        final Tag tag = tagsData.get(position);
        holder.txtVwTagName.setText(tag.getTagName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModuleMaster.navigateToAnswers(mContext, tag.getTagName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagsData == null ? 0 : tagsData.size();
    }

    public void clearTagItems() {
        if (tagsData != null)
            tagsData.clear();
    }

    public void setTagItems(List<Tag> tagItems) {
        this.tagsData = tagItems;
    }
}
