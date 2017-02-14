package ankit.com.taskmaster.uiutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.StackOverFlowApplication;

/**
 * Created by ankit on 14/02/17.
 */
public class Utility {

    private static final String TAG = Utility.class.getSimpleName();

    /**
     * Gets the fragment instance.
     *
     * @param context      the context
     * @param fragmentPath the path of the Fragment in String format
     * @return the fragment instance
     */
    public static Fragment getFragmentInstance(Context context, String fragmentPath) {
        @SuppressWarnings("rawtypes")
        Class clazz = null;
        Fragment fragment = null;
        String module = fragmentPath;
        try {
            clazz = Class.forName(module);
            try {
                fragment = (Fragment) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                Log.e("getFragmentInstance", "Please make sure the fragment(" + module + ") has a constructor.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.e("getFragmentInstance", "Couldn't Access the fragment - " + module);
            }
        } catch (ClassNotFoundException e) {
            Log.e("getFragmentInstance", "Couldn't locate the fragment - " + module);
            e.printStackTrace();
        }
        return fragment;
    }

    public static boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) StackOverFlowApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void showSnackBar(View view, Context context, String msg){
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("RETRY",null);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.txt_white));
        textView.setTextColor(ContextCompat.getColor(context, R.color.txt_red));
        snackbar.show();
    }

}
