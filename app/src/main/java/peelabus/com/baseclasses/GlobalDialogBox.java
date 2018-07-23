package peelabus.com.baseclasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

public class GlobalDialogBox {

    private String msg,title;
    private DialogInterface.OnClickListener onOKClickListener,onCancelClickListener;
    private AlertDialog.Builder globalAlertDialog;
    private Context mContext;
    AlertDialog alertDialog;

    GlobalDialogBox(String title, String msg, Context context,
                    DialogInterface.OnClickListener onCancelClickListener,DialogInterface.OnClickListener onOKClickListener) {
        mContext = context;
        this.msg = msg;
        this.title = title;
        this.onOKClickListener = onOKClickListener;
        this.onCancelClickListener = onCancelClickListener;

        globalAlertDialog = new AlertDialog.Builder(mContext);
        globalAlertDialog.setMessage(msg);
        globalAlertDialog.setTitle("Alert!!");
        globalAlertDialog.setPositiveButton("OK", onOKClickListener);
        globalAlertDialog.setNegativeButton("Cancel",onCancelClickListener);
        alertDialog = globalAlertDialog.create();
        globalAlertDialog.setCancelable(false);

    }

    public void showDialogBox(){

    }

}

