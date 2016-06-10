package helloworld.example.administrator.wwmobilesafe1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/6/10.
 */
public class Sputil {

    private static SharedPreferences sharedPreferences=null;

    //写
    public static void saveConfig(Context ctx ,String key ,boolean value){
        if(sharedPreferences==null){
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key,value).commit();
    }
    //读
    public static boolean getConfig(Context ctx,String key,boolean defValue){
        if(sharedPreferences==null){
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key,defValue);
    }

    //写
    public static void saveString(Context ctx ,String key ,String value){
        if(sharedPreferences==null){
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key,value).commit();
    }
    //读
    public static String getString(Context ctx,String key,String defValue){
        if(sharedPreferences==null){
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key,defValue);
    }
}
