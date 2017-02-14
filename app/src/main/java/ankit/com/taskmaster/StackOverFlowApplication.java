package ankit.com.taskmaster;

import android.app.Application;

/**
 * Created by ankit on 14/02/17.
 */
public class StackOverFlowApplication extends Application {

    private static Application instance;
    public static Application getInstance(){return instance;}

    private static final String TAG = StackOverFlowApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
