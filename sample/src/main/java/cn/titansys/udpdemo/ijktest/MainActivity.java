package cn.titansys.udpdemo.ijktest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity {
    IjkVideoView videoView;
    private TextView tv_url_hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_url_hint = findViewById(R.id.tv_url_hint);

        Intent getIntent = getIntent();
        String player_url = getIntent.getStringExtra("player_url");
        tv_url_hint.setText(player_url);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) findViewById(R.id.ijkPlayer);
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
        if (BaseVideoService.getMediaPlayer() != null) {
            videoView.setMediaPlayer((IjkMediaPlayer) BaseVideoService.getMediaPlayer());
        } else {
            //String url = "https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d";
            // String url = "http://o6wf52jln.bkt.clouddn.com/演员.mp3";
            //String url = edt_url.getText().toString().trim();
            // 方法一
            videoView.setVideoURI(Uri.parse(player_url));
        }
        videoView.start();




    }

    private void setOrientation(int orientation) {
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  videoView.pause();
    }

    @Override
    protected void onDestroy() {
        BaseVideoService.setMediaPlayer(videoView.getMediaPlayer());
        BaseVideoService.intentToStart(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }
}
