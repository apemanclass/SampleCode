package com.sample.yl.sampledemo.audiomanager;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.sample.yl.sampledemo.R;

public class AudioManagerActivity extends AppCompatActivity {

    private Button buPlay, bu1, bu2;
    private SoundPool soundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        initControls();

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);//听筒播放
        audioManager.setMode(AudioManager.MODE_NORMAL);//喇叭播放
        audioManager.setSpeakerphoneOn(true);
        int result = audioManager.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {

                LogUtils.eTag("当前音频的焦点----->", focusChange);

            }
        }, AudioManager.MODE_IN_COMMUNICATION, AudioManager.AUDIOFOCUS_GAIN);

        LogUtils.eTag("申请焦点结果:----->", result);

        soundPool = new SoundPool(100, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(AudioManagerActivity.this, R.raw.ring, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                int streamId = 0;

                //streamID：播放时返回的值
                streamId = soundPool.play(1, 1, 1, 0, -1, 1);


                LogUtils.eTag("播放时返回的值----->", streamId);
            }
        });

        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                audioManager.setSpeakerphoneOn(false);
            }
        });

        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setMode(AudioManager.MODE_NORMAL);
                audioManager.setSpeakerphoneOn(true);
            }
        });
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        buPlay = (Button) findViewById(R.id.bu_play);
        bu1 = (Button) findViewById(R.id.bu_1);
        bu2 = (Button) findViewById(R.id.bu_2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (soundPool != null) {
            soundPool.release();
        }
    }

}
