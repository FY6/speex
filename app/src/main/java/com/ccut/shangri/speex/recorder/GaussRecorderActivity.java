package com.ccut.shangri.speex.recorder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GaussRecorderActivity extends Activity implements OnClickListener {

	public static final int STOPPED = 0;
	public static final int RECORDING = 1;

	// PcmRecorder recorderInstance = null;
	SpeexRecorder recorderInstance = null;

	Button startButton = null;
	Button stopButton = null;
	Button playButton = null;
	Button exitButon = null;
	TextView textView = null;
	int status = STOPPED;

	String fileName = null;
	SpeexPlayer splayer = null;
	public void onClick(View v) {
		if (v == startButton) {
			this.setTitle("startButton");
			fileName =  "/mnt/sdcard/gauss.spx";
		//	fileName =  "/mnt/sdcard/1324966898504.spx";
			
			if (recorderInstance == null) {
				// recorderInstance = new PcmRecorder();

				recorderInstance = new SpeexRecorder(fileName);

				Thread th = new Thread(recorderInstance);
				th.start();
			}
			recorderInstance.setRecording(true);
		} else if (v == stopButton) {
			this.setTitle("stopButton");
			recorderInstance.setRecording(false);
		} else if (v == playButton) {
			// play here........
			this.setTitle("playButton");
			fileName =  "/mnt/sdcard/gauss.spx";
			System.out.println("filename===="+fileName);
			splayer = new SpeexPlayer(fileName);
			splayer.startPlay();
			
		} else if (v == exitButon) {
			recorderInstance.setRecording(false);
			System.exit(0);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		startButton = new Button(this);
		stopButton = new Button(this);
		exitButon = new Button(this);
		playButton = new Button(this);
		textView = new TextView(this);

		startButton.setText("Start");
		stopButton.setText("Stop");
		playButton.setText("Play");
		exitButon.setText("Exit");
		textView.setText("android ¼����\n(1)��ȡPCM���." + "\n(2)ʹ��speex����");

		startButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
		exitButon.setOnClickListener(this);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(startButton);
		layout.addView(stopButton);
		layout.addView(playButton);
		layout.addView(exitButon);
		this.setContentView(layout);
	}
}