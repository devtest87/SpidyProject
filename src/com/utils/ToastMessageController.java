package com.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastMessageController {
	private static boolean isShowMessage = true;
	public static void showMessahe(Context context, String message){
		if(isShowMessage){
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}
	}
}
