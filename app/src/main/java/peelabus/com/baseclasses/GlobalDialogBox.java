package peelabus.com.baseclasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class GlobalDialogBox {

//    private String msg,title;
//    private AlertDialog.Builder globalAlertDialog;
//    private Context mContext;
//    AlertDialog alertDialog;
//
//    GlobalDialogBox(String title, String msg, Context context) {
//        mContext = context;
//        this.msg = msg;
//        this.title = title;
//        globalAlertDialog = new AlertDialog.Builder(mContext);
//        globalAlertDialog.setMessage(msg);
//        globalAlertDialog.setTitle(title);
//        globalAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
//                mContext.startActivity(intent);
//            }
//        });
//        globalAlertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        alertDialog = globalAlertDialog.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//    }
//

    private String msg;
    private ProgressBar progressBar;

    GlobalDialogBox(String title, String msg, Context context){
        progressBar = new ProgressBar(context);
    }

}

