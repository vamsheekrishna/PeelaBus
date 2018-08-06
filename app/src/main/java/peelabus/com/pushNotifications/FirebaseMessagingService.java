package peelabus.com.pushNotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import peelabus.com.R;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    static final String TAG = "Firebase_Msg_Service";

    private static final String NOTIFICATION_ID_EXTRA = "notificationId";
    private static final String IMAGE_URL_EXTRA = "imageUrl";
    private static final String ADMIN_CHANNEL_ID ="admin_channel";
    private NotificationManager notificationManager;

    @Override
    public void onNewToken(String s) {
        Log.d(TAG, "Refreshed token: " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());



        Intent notificationIntent = new Intent(this, "maps module" );//TODO: set the Maps_module class
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);

        //You should use an actual ID instead
        int notificationId = new Random().nextInt(60000);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                        .setLargeIcon("image") // TODO: set notification image
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(remoteMessage.getData().get("title"))
                        .setContentText(remoteMessage.getData().get("message"))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .addAction(R.drawable.ic_favorite_true,
                                getString(R.string.notification_add_to_cart_button),notificationIntent)
                        .setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, notificationBuilder.build());

    }


}
