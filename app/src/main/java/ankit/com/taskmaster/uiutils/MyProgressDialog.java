package ankit.com.taskmaster.uiutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;


import ankit.com.taskmaster.R;
import ankit.com.taskmaster.StackOverFlowApplication;

/**
 * Created by ankit
 */
public class MyProgressDialog extends ProgressDialog {

    private boolean setCancelable;
    private static final String TAG = MyProgressDialog.class.getCanonicalName();

    public MyProgressDialog(Context context) {
        super(context,R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProgressBar progressBar = new ProgressBar(getContext());
        setContentView(progressBar);

        progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(StackOverFlowApplication.getInstance(), R.drawable.bg_progress_dialog));
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);
    }

    /**
     * Make sure you have called resetDialog() before calling this
     *
     * @param setCancelable
     */
    public void showProgressDialog(String screenFrom, boolean setCancelable) {
        try {
            this.setCancelable = setCancelable;
            show();
            setCanceledOnTouchOutside(setCancelable);
            setCancelable(setCancelable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hideProgressDialog(String screenFrom) {
        dismiss();
    }
}
