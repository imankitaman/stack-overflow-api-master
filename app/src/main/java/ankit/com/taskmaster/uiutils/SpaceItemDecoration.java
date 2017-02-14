package ankit.com.taskmaster.uiutils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ankit on 27/11/16.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

//        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildAdapterPosition(view) == 0)
//            outRect.top = space;
    }
}
