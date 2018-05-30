package com.example.darkhorse.rvadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_sample_1, btn_sample_2, btn_sample_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        setListener();
    }

    private void findViewById() {
        btn_sample_1 = (Button) findViewById(R.id.btn_sample_1);
        btn_sample_2 = (Button) findViewById(R.id.btn_sample_2);
        btn_sample_3 = (Button) findViewById(R.id.btn_sample_3);
    }

    private void setListener() {
        btn_sample_1.setOnClickListener(this);
        btn_sample_2.setOnClickListener(this);
        btn_sample_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sample_1:
                startActivity(new Intent(this, Sample1.class));
                break;
            case R.id.btn_sample_2:
                startActivity(new Intent(this, Sample2.class));
                break;

            case R.id.btn_sample_3:
                startActivity(new Intent(this, Sample3.class));
                break;
            default:
        }
    }
}
