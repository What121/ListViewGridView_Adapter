package com.chener.testlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chener.testlistview.activity.OneApklistActivity;
import com.chener.testlistview.activity.TwoApklistActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView onetext,twotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onetext=findViewById(R.id.onetest);
        onetext.setOnClickListener(this);
        twotext=findViewById(R.id.twotest);
        twotext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.onetest:
                Intent one=new Intent(this, OneApklistActivity.class);
                startActivity(one);
                break;
            case R.id.twotest:
                Intent two=new Intent(this, TwoApklistActivity.class);
                startActivity(two);
                break;
            default:
                break;
        }
    }
}
