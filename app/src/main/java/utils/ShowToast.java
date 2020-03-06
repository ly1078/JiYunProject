package utils;

import android.widget.Toast;

import apps.MyApplication;

public class ShowToast {

    public static void show(String msg){
        Toast.makeText(MyApplication.myApplication,msg,Toast.LENGTH_LONG).show();
    }
}
