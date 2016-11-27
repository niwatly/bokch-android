package tv.bokch.android;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import timber.log.Timber;
import tv.bokch.R;

public abstract class BaseDialog extends DialogFragment {
	protected Activity mParentActivity;
	public static final int SIZE_DEFAULT = -999;
	
	@Override
	public void onAttach(Activity activity) {
		mParentActivity = activity;
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Dialog dialog = getDialog();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		int width, height;

		int orientation = getResources().getConfiguration().orientation;
		width = getWidth(orientation);
		if (width == SIZE_DEFAULT) {
			int resId = orientation == Configuration.ORIENTATION_PORTRAIT ? R.dimen.dialog_width_portrait : R.dimen.dialog_width_landscape;
			params.width = getResources().getDimensionPixelSize(resId); 
		} else {
			params.width = width;
		}
		
		height = getHeight(orientation);
		if (height == SIZE_DEFAULT) {
			int resId = orientation == Configuration.ORIENTATION_PORTRAIT ? R.dimen.dialog_height_portrait : R.dimen.dialog_height_landscape;
			params.height = getResources().getDimensionPixelSize(resId);
		} else {
			params.height = height;
		}
		dialog.getWindow().setAttributes(params);
	}

	protected abstract int getWidth(int orientation);
	protected abstract int getHeight(int orientation);

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Activity activity = getActivity();
		if (activity == null) {
			dismiss();
			return null;
		}
		Dialog dialog = new Dialog(activity);
		//オリジナルのデザインを適用するために標準のデザインを消す
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //nullじゃだめだった
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		return dialog;
	}


	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}

	protected void sendActivityResultCallback(@Nullable Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		PendingIntent pending = mParentActivity.createPendingResult(getTargetRequestCode(), intent, PendingIntent.FLAG_ONE_SHOT);
		try {
			pending.send(Activity.RESULT_OK);
		} catch (PendingIntent.CanceledException e) {
			Timber.w(e, null);
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		//キャンセルされると呼び出し元のactivityResultが呼ばれないため、
		//キャンセル動作はdismissに変更する
		dismiss();
	}
}
