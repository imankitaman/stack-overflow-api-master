package ankit.com.taskmaster.view.activity;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;


import java.util.ArrayList;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.view.adapters.PagerAdapter;


public class BaseTabsActivity extends AppCompatActivity {

    private static final String TAG = BaseTabsActivity.class.getName();

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the resource ID of the String-array
     * from modules.xml file that consists of the titles for each fragment in the tabs.
     */
    public static final String ARGS_FRAGMENTS_TITLES = "titles";

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the resource ID of the String-array
     * from modules.xml file that consists of the path for each fragment in the tabs.
     */
    public static final String ARGS_FRAGMENT_ARRAY = "fragments";

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the resource ID of the integer-array
     * from modules.xml file that consists of the icon for each fragment in the tabs.
     */
    public static final String ARGS_FRAGMENTS_ICONS = "icons";

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the ArrayList<Bundle> that consists of the
     * bundle arguments for each fragment in the tabs.
     */
    public static final String TABS_FRAGMENTS_ARGUMENTS = "arguments";

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the index of the tab that needs to be shown by default.
     * The indices of the tabs start from 0.
     */
    public static final String ARGS_SELECTED_INDEX = "selected_index";

    /**
     * This needs to be passed in the intent as a key.
     * This key should have the title that needs to be set to the Activity
     * that is holding all the tabs and the fragments.
     */
    public static final String ARGS_ACTIVITY_TITLE = "activity_title";
    private PagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        initToolbar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }

        // SET THE ACTIVITY TITLE
        try {
            String title = getIntent().getStringExtra(ARGS_ACTIVITY_TITLE);
            title = (!TextUtils.isEmpty(title)) ? title : getString(R.string.app_name);
            setTitle(title);
        } catch (Exception e) {
            String title = getIntent().getStringExtra(ARGS_ACTIVITY_TITLE);
            title = ((null != title) && !TextUtils.isEmpty(title)) ? title : getString(R.string.app_name);
            setTitle(title);
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    public void onBackPressed() {
        Fragment fragment = null;
        try {
            fragment = adapter.getItem(viewPager.getCurrentItem());
            ((ActivityEventListener) fragment).onBackPress();
        } catch (Exception e) {
            if (null != fragment)
                Log.w(TAG, fragment.getClass().getName() + " must implement ActivityEventlistener.");
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            try {
                String[] fragmentTitles = getResources().getStringArray(getIntent().getIntExtra(ARGS_FRAGMENTS_TITLES, 0));
                TypedArray fragmentIcons = getResources().obtainTypedArray(getIntent().getIntExtra(ARGS_FRAGMENTS_ICONS, 0));
                String[] fragments = getResources().getStringArray(getIntent().getIntExtra(ARGS_FRAGMENT_ARRAY, 0));
                ArrayList<Bundle> args = (ArrayList<Bundle>) getIntent().getSerializableExtra(TABS_FRAGMENTS_ARGUMENTS);
                int selectedIndex = getIntent().getIntExtra(ARGS_SELECTED_INDEX, 0);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

                for (int i = 0; i < fragmentTitles.length; i++) {
                    int resourceId = fragmentIcons.getResourceId(i, -1);
                    tabLayout.addTab(tabLayout.newTab().setText(fragmentTitles[i]).setIcon(resourceId));
                }
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#00A1DF"));
                if (tabLayout.getTabCount() >= 3)
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

                viewPager = (ViewPager) findViewById(R.id.pager);
                adapter = new PagerAdapter(this, getSupportFragmentManager(), fragments, args);
                viewPager.setAdapter(adapter);

                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                //SELECTING THE DEFAULT TAB
                viewPager.setCurrentItem(selectedIndex);

            } catch (Exception e) {
            }
        }

    }

    public void swipPager(int index){
        viewPager.setCurrentItem(index);
    }

}
