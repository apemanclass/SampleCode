package com.sample.yl.sampledemo.tts;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.hjq.toast.ToastUtils;
import com.sample.yl.sampledemo.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ${jz} on 2018/11/12。
 * 文字转语音的示例
 */
public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private EditText etMsgString;
    private TextToSpeech tts;
    private String strText = "所谓人生，无非就是活着的时候大闹一场，然后飘然而去";

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        //region 文字转语音的使用
        initTTs();
        findViewById(R.id.bt_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playTTS();
            }
        });
        findViewById(R.id.bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.stop();
            }
        });
        //endregion


        //region 来电铃声
        onSystemBell();
        findViewById(R.id.bt_ringtone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        //endregion
    }

    private void initTTs() {
        etMsgString = findViewById(R.id.et_msg);
        //创建tts对象
        tts = new TextToSpeech(this, this);
    }

    public void playTTS() {
        strText = etMsgString.getText().toString().trim();
        if (!TextUtils.isEmpty(strText)) {
            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            //tts.setPitch(1.0f);
            // 设置语速
            //tts.setSpeechRate(1.0f);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    strText = "所谓人生，无非就是活着的时候大闹一场，然后飘然而去";
                }
            }, 20000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    tts.speak(strText, TextToSpeech.QUEUE_ADD, null, null);
                }
            }, 0, 5000);
        }

    }

    @Override
    public void onInit(int status) {
        //判断tts回调是否成功
        if (status == TextToSpeech.SUCCESS) {
            ToastUtils.show("TTS引擎初始化成功");
            tts.speak(strText, TextToSpeech.QUEUE_ADD, null, null);
        } else {
            ToastUtils.show("TTS引擎初始化失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


    /**
     * 获取系统自带铃声并响铃
     */
    private void onSystemBell() {
        mediaPlayer = MediaPlayer.create(this, getDefaultRingtoneUri());
        mediaPlayer.setLooping(true);//设置循环
    }

    /**
     * 获取的是铃声的Uri
     * <p>
     * RingtoneManager.TYPE_NOTIFICATION;  通知声音
     * RingtoneManager.TYPE_ALARM;  警告
     * RingtoneManager.TYPE_RINGTONE; 铃声
     */
    private Uri getDefaultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE);
    }

}
