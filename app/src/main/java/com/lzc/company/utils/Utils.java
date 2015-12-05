package com.lzc.company.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {

	public static void hideKeyboard(View view) {
		if (view == null) {
			return;
		}
		InputMethodManager imm = (InputMethodManager) view.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (!imm.isActive()) {
			return;
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void showKeyboard(View view) {
		if (view == null) {
			return;
		}
		InputMethodManager inputManager = (InputMethodManager) view
				.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);

		((InputMethodManager) view.getContext().getSystemService(
				Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
	}

	public static boolean isKeyboardShowed(View view) {
		if (view == null) {
			return false;
		}
		InputMethodManager inputManager = (InputMethodManager) view
				.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		return inputManager.isActive(view);
	}

	public static ProgressDialog progressDialog;

	public static void ShowProgressDialog(final Activity activity,
			final String message) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!activity.isFinishing()) {
					progressDialog = new ProgressDialog(activity);
					if (message != null) {
						progressDialog.setMessage(message);
					}
					progressDialog.setCanceledOnTouchOutside(false);
					progressDialog.setCancelable(false);
					progressDialog.show();
				}
			}
		});
	}

	public static void HideProgressDialog(Activity activity) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
		});
	}

}
