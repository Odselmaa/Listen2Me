package com.example.listenme;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

public class MainActivity extends Activity {
	FrameLayout fr;
	LinearLayout llText, llSongInfo;
	ImageView ivCover;
	CircleProgressView mCircleView;

	int percent = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FontsOverride.setDefaultFont(this.getApplicationContext(), "MONOSPACE",
				"fonts/Oswald-Light.ttf");
		setContentView(R.layout.activity_main);

		fr = (FrameLayout) findViewById(R.id.fr);
		llText = (LinearLayout) findViewById(R.id.llText);
		llSongInfo = (LinearLayout) findViewById(R.id.llSongInfo);

		mCircleView = (CircleProgressView) findViewById(R.id.circleView);

		// value setting
		mCircleView.setStartAngle(180);
		mCircleView.setMaxValue(100);
		mCircleView.setValue(0);
		mCircleView.setValueAnimated(24);
		// show unit
		mCircleView.setUnit("%");
		mCircleView.setShowUnit(true);
		mCircleView.setRimColor(Color.CYAN);
		// text sizes
		mCircleView.setTextSize(50); // text size set, auto text size off
		mCircleView.setUnitSize(40); // if i set the text size i also have to
										// set the unit size
		mCircleView.setAutoTextSize(true); // enable auto text size, previous
											// values are overwritten
		// if you want the calculated text sizes to be bigger/smaller you can do
		// so via
		mCircleView.setUnitScale(0.9f);
		mCircleView.setTextScale(0.9f);

		// color
		// you can use a gradient
		mCircleView.setBarColor(getResources().getColor(R.color.primary_color),
				getResources().getColor(R.color.primary_color));

		// colors of text and unit can be set via
		mCircleView.setTextColor(Color.RED);
		mCircleView.setTextColor(Color.BLUE);
		// or to use the same color as in the gradient
		mCircleView.setAutoTextColor(true); // previous set values are ignored

		// text mode
		mCircleView.setText("Text"); // shows the given text in the circle view
		mCircleView.setTextMode(TextMode.TEXT); // Set text mode to text to show
												// text

		// in the following text modes, the text is ignored
		mCircleView.setTextMode(TextMode.VALUE); // Shows the current value
		mCircleView.setTextMode(TextMode.PERCENT); // Shows current percent of
													// the current value from
													// the max value

		// spinning
		mCircleView.spin(); // start spinning
		mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter
									// until it disappears.
		mCircleView.setValueAnimated(24); // stops spinning. Spinner spins until
											// on top. Then fills to set value.
		mCircleView.setShowTextWhileSpinning(true); // Show/hide text in
													// spinning mode
		// animation callbacks

		// this example shows how to show a loading text if it is in spinning
		// mode, and the current percent value otherwise.
		mCircleView.setText("Loading...");
		mCircleView.setTextMode(TextMode.PERCENT);
		Timer t = new Timer();

		t.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (percent >= 50)
					mCircleView.setValue(percent--);
			}
		}, 0, 1000);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// the height will be set at this point
		// Toast.makeText(this,
		// " "+llSongInfo.getMeasuredHeight()+" "+llText.getMeasuredHeight(),
		// Toast.LENGTH_LONG).show();
		int h1 = llSongInfo.getMeasuredHeight();
		int h2 = llText.getMeasuredHeight();

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		double h = size.y;

		LayoutParams lp1 = (LayoutParams) fr.getLayoutParams();
		lp1.height = (int) (h / 2);
		fr.setLayoutParams(lp1);

	}

	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		mCircleView.setValue(0);
		mCircleView.setValueAnimated(42);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
