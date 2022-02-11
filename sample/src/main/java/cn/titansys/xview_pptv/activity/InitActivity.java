package cn.titansys.xview_pptv.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.titansys.xview_pptv.R;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class InitActivity extends AppCompatActivity {

    IjkVideoView videoView;
    private TextView tv_url_hint;
    private LinearLayout ll_video;
    LinearLayout.LayoutParams params;

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        tv_url_hint = findViewById(R.id.tv_url_hint);
        ll_video = findViewById(R.id.ll_video);
        params = (LinearLayout.LayoutParams) ll_video.getLayoutParams();
        params.height = 400;
        params.width = 711;
        params.setMargins(70,300,0,0);
        ll_video.setLayoutParams(params);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        Display display = InitActivity.this.getWindowManager().getDefaultDisplay();
        // 方法一(推荐使用)使用Point来保存屏幕宽、高两个数据
        Point outSize = new Point();
        // 通过Display对象获取屏幕宽、高数据并保存到Point对象中
        display.getSize(outSize);
        // 从Point对象中获取宽、高
        int x = outSize.x;
        int y = outSize.y;
        Log.e("log",x+":"+y);

        //edt_url.setText("https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d");
        Button btn_player = (Button) findViewById(R.id.btn_player);
        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.height = 600;
                params.width = 1020;
                params.setMargins(100,200,0,0);
                ll_video.setLayoutParams(params);
               // tv_url_hint.setText("udp://238.1.238.1:5001");
                tv_url_hint.setText("udp://239.45.3.230:5140");
                player(tv_url_hint.getText().toString());
            }
        });

        ButtonStyle btn_player2 = (ButtonStyle) findViewById(R.id.btn_player2);
        btn_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params.height = 600;
                params.width = 1020;
                params.setMargins(100,200,0,0);
                ll_video.setLayoutParams(params);
                //tv_url_hint.setText("http://183.207.249.14/PLTV/3/224/3221225567/index.m3u8");
                tv_url_hint.setText("udp://238.1.238.1:5001");
                player(tv_url_hint.getText().toString());
            }
        });
        ButtonStyle btn_player3 = (ButtonStyle) findViewById(R.id.btn_player3);
        btn_player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.height = 600;
                params.width = 1020;
                params.setMargins(100,200,0,0);
                ll_video.setLayoutParams(params);
                tv_url_hint.setText("http://112.74.200.9:88/tv000000/m3u8.php?/migu/625525181");
                player(tv_url_hint.getText().toString());
            }
        });

    }



    private void player(String url){
        try {
            countDownTimer.cancel();
        }catch(Exception e){
            System.out.println("倒计时:::"+e.toString());//出现异常的处理
        }

        countDownTimer.start();

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) findViewById(R.id.ijkPlayer);
        //控制器，可有可无
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();

    }



    /**
     * CountDownTimer 实现倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer(30 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            Log.e("隐藏",value);
        }

        @Override
        public void onFinish() {
            tv_url_hint.setVisibility(View.GONE);
        }
    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            videoView.pause();
            countDownTimer.cancel();
        }catch(Exception e){
            System.out.println("倒计时:::"+e.toString());//出现异常的处理
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            videoView.pause();
            countDownTimer.cancel();
        }catch(Exception e){
            System.out.println("倒计时:::"+e.toString());//出现异常的处理
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}