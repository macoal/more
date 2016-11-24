package com.one.util.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.one.util.R;


/**
 * Created by mcz on 2016/10/11
 */
public class MyToast {

	private static Toast toast;
	private static Toast toast1;

	public static void showToast(String tip, Context context){
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setBackgroundResource(R.drawable.bg_toast);

		TextView tv=new TextView(context);
		tv.setTextColor(0xffffffff);
		tv.setGravity(Gravity.CENTER);

		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		int p1=context.getResources().getDimensionPixelSize(R.dimen.space4);
		int p2=context.getResources().getDimensionPixelSize(R.dimen.space12);
		tv.setPadding(p1, p2, p1, p2);

		tv.setText(tip);

		LinearLayout.LayoutParams tvLP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvLP.setMargins(p1, p2, p1, p2);

		ll.addView(tv,tvLP);
		toast1 = new Toast(context);
		toast1.setDuration(Toast.LENGTH_SHORT);
		toast1.setView(ll);
		toast1.show();
	}
	public static void showToast(int res,Context context){
		String tip=context.getString(res);
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setBackgroundResource(R.drawable.bg_toast);

		TextView tv=new TextView(context);
		tv.setTextColor(0xffffffff);
		tv.setGravity(Gravity.CENTER);

		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		int p1=context.getResources().getDimensionPixelSize(R.dimen.space4);
		int p2=context.getResources().getDimensionPixelSize(R.dimen.space12);
		tv.setPadding(p1, p2, p1, p2);

		tv.setText(tip);

		LinearLayout.LayoutParams tvLP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvLP.setMargins(p1, p2, p1, p2);

		ll.addView(tv,tvLP);
		toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(ll);
		toast.show();
	}
	public static void cancelToast(){
		if(toast1!=null){
			toast1.cancel();
		}
	}


	public static void showIconToast(int iconRes, String tip, Context context){

		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setGravity(Gravity.CENTER);
//		ll.setBackgroundResource(R.drawable.bg_toast_rectangle);
		ll.setBackgroundResource(R.drawable.bg_toast);

		int p0=context.getResources().getDimensionPixelSize(R.dimen.space5);
		int p1=context.getResources().getDimensionPixelSize(R.dimen.space4);
		int p2=context.getResources().getDimensionPixelSize(R.dimen.space12);

		ImageView icon=new ImageView(context);
		icon.setImageResource(iconRes);
		LinearLayout.LayoutParams icLP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		icLP.setMargins(p1,p1,p1,p1);


		TextView tv=new TextView(context);
		tv.setTextColor(0xffffffff);
		tv.setGravity(Gravity.CENTER);

		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

		tv.setPadding(p1, p0, p1, p2);
		tv.setText(tip);

		LinearLayout.LayoutParams tvLP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvLP.setMargins(p1, p2, p1, p2);

		ll.addView(tv,tvLP);
		ll.addView(icon,icLP);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(ll);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
