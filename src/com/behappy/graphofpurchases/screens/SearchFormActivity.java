package com.behappy.graphofpurchases.screens;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.behappy.graphofpurchases.R;
import com.behappy.graphofpurchases.model.SearchMask;
import com.behappy.graphofpurchases.services.SmsReceiver;

public class SearchFormActivity extends FragmentActivity {

	public static final String ACTION_NEW_SMS = "com.behappy.graphofpurchases.ACTION_NEW_SMS";

	public static final String KEY_SMS_PHONE = "smsPhone";
	public static final String KEY_SMS_TEXT = "smsText";

	private EditText etSearchForm = null;

	private SmsReceiver receiver = new SmsReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String text = intent.getStringExtra(KEY_SMS_TEXT);
			String phone = intent.getStringExtra(KEY_SMS_PHONE);

			NewSmsFragment moFragment = new NewSmsFragment(text, phone);
			moFragment.show(getSupportFragmentManager(), null);
		}
	};

	private IntentFilter filter = new IntentFilter(ACTION_NEW_SMS);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_form);
		etSearchForm = (EditText) findViewById(R.id.etSearchForm);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	public void onBtnDoneClick(View v) {
		/* SearchMask mask = */new SearchMask(etSearchForm.getText().toString());
	}

	public void onNumberMarkerClick(View v) {
		insertMarker(getString(R.string.number_marker));
	}

	public void onStringMarkerClick(View v) {
		insertMarker(getString(R.string.string_marker));
	}

	public void onSkipMarkerClick(View v) {
		insertMarker(getString(R.string.skip_marker));
	}

	private void insertMarker(String numberMarker) {
		int start = etSearchForm.getSelectionStart();
		int end = etSearchForm.getSelectionEnd();

		if (start == end) {
			etSearchForm.getText().insert(start, numberMarker);
			return;
		}

		etSearchForm.getText().replace(Math.min(start, end),
				Math.max(start, end), numberMarker, 0, numberMarker.length());
	}
}
