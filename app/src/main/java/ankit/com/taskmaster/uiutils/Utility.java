package ankit.com.taskmaster.uiutils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

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

}
