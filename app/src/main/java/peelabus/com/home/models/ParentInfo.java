package peelabus.com.home.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import peelabus.com.models.ChildInfoObj;
import peelabus.com.peelabus.Config;

public class ParentInfo {
    public static ParentInfo mParentInfo = null;
    public List<ChildInfoObj> mParentDetails = null;
    private ParentInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String resp = sharedPreferences.getString(Config.RESPONSE, "Not");
        Log.i("respoo", "ressss: " + resp);
        mParentDetails = Arrays.asList(new GsonBuilder().create().fromJson(resp, ChildInfoObj[].class));

    }
    public static ParentInfo getObject(Context context) {
        if(null == mParentInfo) {
            mParentInfo = new ParentInfo(context);
        }
        return mParentInfo;
    }

    public static void deleteInstance() {
        mParentInfo = null;
    }
}
