package ankit.com.taskmaster.uiutils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Question;
import ankit.com.taskmaster.view.activity.BaseTabsActivity;
import ankit.com.taskmaster.view.activity.DetailedActivity;

/**
 * Created by ankit on 14/02/17.
 */
public class ModuleMaster {

    private static final String TAG = ModuleMaster.class.getSimpleName();

    public static void navigateToAnswers(Context context, String tagName) {
        Intent intent = new Intent(context, BaseTabsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CONSTANT_TAG_NAME, tagName);
        ArrayList<Bundle> args = new ArrayList<Bundle>();
        args.add(bundle);
        args.add(bundle);
        intent.putExtra(BaseTabsActivity.ARGS_ACTIVITY_TITLE, R.string.tabTitle);
        intent.putExtra(BaseTabsActivity.ARGS_FRAGMENT_ARRAY, R.array.fragments_name);
        intent.putExtra(BaseTabsActivity.ARGS_FRAGMENTS_TITLES, R.array.fragments_titles);
        intent.putExtra(BaseTabsActivity.ARGS_FRAGMENTS_ICONS, R.array.fragment_icons);
        intent.putExtra(BaseTabsActivity.ARGS_SELECTED_INDEX, 0);
        intent.putExtra(BaseTabsActivity.TABS_FRAGMENTS_ARGUMENTS, args);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static void navigateToAnswersDetails(Context context, Question question, Pair<View, String>[] pairs ) {
        Intent intent = new Intent(context, DetailedActivity.class);
        intent.putExtra(Constants.BUNDLE_DATA_NAME, question);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
            ActivityCompat.startActivity(context, intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }


}
