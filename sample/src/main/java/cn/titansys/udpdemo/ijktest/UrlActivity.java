package cn.titansys.udpdemo.ijktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UrlActivity extends AppCompatActivity {
    private EditText edt_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        edt_url=findViewById(R.id.edt_url);
        edt_url.setText("udp://239.0.0.3:8218");
        //edt_url.setText("https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d");
        Button btn_player = (Button) findViewById(R.id.btn_player);

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UrlActivity.this,MainActivity.class);
                intent.putExtra("player_url", edt_url.getText().toString().trim());
                startActivity(intent);
            }
        });
    }



}