package com.sundy.music.receiver;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.security.auth.login.LoginException;

public class WakeReceiver extends BroadcastReceiver {
    private final static String TAG = WakeReceiver.class.getSimpleName();
    private final static int WAKE_SERVICE_ID = -1111;

    /**
     * 灰色保活手段唤醒广播的action
     */
    public final static String GRAY_WAKE_ACTION = "com.sundy.wake.gray";
    @Override
    public void onReceive(Context context, Intent intent) {
       String action=intent.getAction();
       if (GRAY_WAKE_ACTION.equals(action)){
           Log.i(TAG,"唤醒中 ！！");
           Intent wakeIntent=new Intent(context,WakeNotifyService.class);
           context.startService(wakeIntent);
       }
    }

    public static class WakeNotifyService extends Service{

        @Override
        public void onCreate() {
            super.onCreate();
            Log.i(TAG, "WakeNotifyService->onCreate");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(TAG, "WakeNotifyService->onStartCommand");
            if (Build.VERSION.SDK_INT<18){
                startForeground(WAKE_SERVICE_ID,new Notification());
            }else{
                Intent innerIntent=new Intent(this,WakeGrayInnerService.class);
                startService(innerIntent);
                startForeground(WAKE_SERVICE_ID,new Notification());
            }
            return START_STICKY;
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i(TAG, "WakeNotifyService->onDestroy");
        }
    }
    /**
     * 给 API >= 18 的平台上用的灰色保活手段
     */
    public static class WakeGrayInnerService extends Service {

        @Override
        public void onCreate() {
            Log.i(TAG, "InnerService -> onCreate");
            super.onCreate();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(TAG, "InnerService -> onStartCommand");
            startForeground(WAKE_SERVICE_ID, new Notification());
            //stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "InnerService -> onDestroy");
            super.onDestroy();
        }
    }

}
