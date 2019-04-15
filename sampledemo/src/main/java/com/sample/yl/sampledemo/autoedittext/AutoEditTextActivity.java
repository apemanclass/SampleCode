package com.sample.yl.sampledemo.autoedittext;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.sample.yl.sampledemo.R;

/**
 * 自定义View之带正则校验的EditText
 */
public class AutoEditTextActivity extends AppCompatActivity {

    private AutoCheckEditText mobileET;
    private AutoCheckEditText telET;
    private AutoCheckEditText emailET;
    private AutoCheckEditText urlET;
    private AutoCheckEditText chzET;
    private AutoCheckEditText usernameET;
    private AutoCheckEditText inputRegexET;
    private EditText inputET;
    private Drawable successDrawble;
    private Drawable unsuccessDrawble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit_text);

        initControls();

        mobileET.createCheck(AutoCheckEditText.TYPE_OF_MOBILE, successDrawble, unsuccessDrawble);
        telET.createCheck(AutoCheckEditText.TYPE_OF_TEL, successDrawble, unsuccessDrawble);
        emailET.createCheck(AutoCheckEditText.TYPE_OF_EMAIL, successDrawble, unsuccessDrawble);
        urlET.createCheck(AutoCheckEditText.TYPE_OF_URL, successDrawble, unsuccessDrawble);
        chzET.createCheck(AutoCheckEditText.TYPE_OF_CHZ, successDrawble, unsuccessDrawble);
        usernameET.createCheck(AutoCheckEditText.TYPE_OF_USERNAME, successDrawble, unsuccessDrawble);
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        successDrawble = ResourcesCompat.getDrawable(getResources(), R.drawable.success, getApplicationContext().getTheme());
        unsuccessDrawble = ResourcesCompat.getDrawable(getResources(), R.drawable.unsuccess, getApplicationContext().getTheme());

        mobileET = findViewById(R.id.et_ac_mobile);
        telET = findViewById(R.id.et_ac_tel);
        emailET = findViewById(R.id.et_ac_email);
        urlET = findViewById(R.id.et_ac_url);
        chzET = findViewById(R.id.et_ac_chz);
        usernameET = findViewById(R.id.et_ac_username);
        inputRegexET = findViewById(R.id.et_ac_input_regex);
    }
}
