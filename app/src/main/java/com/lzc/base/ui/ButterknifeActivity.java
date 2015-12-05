package com.lzc.base.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.lzc.base.R;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterknifeActivity extends AppCompatActivity {

    @Bind(R.id.textView2) EditText username;
    @Bind(R.id.textView3) EditText password;

//    @BindString(R.string.login_error)
//    String loginErrorMessage;

    @OnClick(R.id.submit) void submit() {
        // TODO call server...
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);

        ButterKnife.bind(this);

    }
}
