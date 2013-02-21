package com.behappy.graphofpurchases.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.behappy.graphofpurchases.R;

@SuppressLint("ValidFragment")
public class NewSmsFragment extends DialogFragment {
	private String body = null;
	private String number = null;

	public NewSmsFragment(String body, String number) {
		super();

		this.body = body;
		this.number = number;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.new_sms);

		View v = inflater.inflate(R.layout.dialog_fragment_new_sms, null);
		TextView tvSms = (TextView) v.findViewById(R.id.tvSms);

		tvSms.setText(body + "\n\n" + number);
		return v;
	}
}
