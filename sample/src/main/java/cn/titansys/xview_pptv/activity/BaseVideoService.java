package cn.titansys.xview_pptv.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by  Leif Zhang on 2017/12/22.
 * Email leifzhanggithub@gmail.com
 */

public class BaseVideoService extends Service {
    private static IMediaPlayer sMediaPlayer;

    public static Intent newIntent(Context context) {
        return new Intent(context, BaseVideoService.class);
    }

    public static void intentToStart(Context context) {
        context.startService(newIntent(context));
    }

    public static void intentToStop(Context context) {
        context.stopService(newIntent(context));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void setMediaPlayer(IMediaPlayer mp) {
        if (sMediaPlayer != null && sMediaPlayer != mp) {
            if (sMediaPlayer.isPlaying())
                sMediaPlayer.stop();
            sMediaPlayer.release();
            sMediaPlayer = null;
        }
        sMediaPlayer = mp;
    }

    public static IMediaPlayer getMediaPlayer() {
        return sMediaPlayer;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prepareWidget();
        setUpVideoView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    private void setUpVideoView() {

    }

    private void prepareWidget() {


    }
}
