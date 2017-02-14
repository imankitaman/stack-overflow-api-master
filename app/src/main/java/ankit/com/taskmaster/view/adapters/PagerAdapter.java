package ankit.com.taskmaster.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import ankit.com.taskmaster.uiutils.Utility;


public class PagerAdapter extends FragmentStatePagerAdapter {

    String [] fragments;
    ArrayList<Bundle> args;
    Context context;
    Fragment[] fragmentList;

    public PagerAdapter(Context context, FragmentManager fm, String [] fragments, ArrayList<Bundle> args) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.args = args;
        fragmentList = new Fragment[fragments.length];
    }

    @Override
    public Fragment getItem(int position) {
        if(fragmentList[position] !=null){
            return fragmentList[position];
        }else {
            Fragment fragment = Utility.getFragmentInstance(context, fragments[position]);
            if (null != args.get(position))
                fragment.setArguments(args.get(position));
            fragmentList[position] = fragment;
            return fragment;
        }
    }


    @Override
    public int getCount() {
        return fragments.length;
    }
}