package ankit.com.taskmaster.view.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ankit.com.taskmaster.R;


public class TagViewHolder extends RecyclerView.ViewHolder {
    public TextView txtVwTagName;

    public TagViewHolder(View v) {
        super(v);
        txtVwTagName = (TextView) v.findViewById(R.id.txtVwTagName);
    }
}
