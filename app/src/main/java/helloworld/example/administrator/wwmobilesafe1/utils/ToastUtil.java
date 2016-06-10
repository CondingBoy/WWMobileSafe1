package helloworld.example.administrator.wwmobilesafe1.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/10.
 */
public class ToastUtil {
    public static void show(Context ctx ,String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }
}
