package helloworld.example.administrator.wwmobilesafe1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import helloworld.example.administrator.wwmobilesafe1.R;
import helloworld.example.administrator.wwmobilesafe1.utils.ConstantValue;
import helloworld.example.administrator.wwmobilesafe1.utils.Sputil;
import helloworld.example.administrator.wwmobilesafe1.view.SettingItemView;

/**
 * Created by Administrator on 2016/6/10.
 */
public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        initUpdate();
    }

    private void initUpdate() {
        final SettingItemView settingItemView = (SettingItemView) findViewById(R.id.si_update);
        boolean config_update = Sputil.getConfig(this, ConstantValue.OPNE_UPDATE, false);
        settingItemView.setCheck(config_update);
        //设置点击事件
        settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = settingItemView.isCheck();
                //判断原来的选中状态
                settingItemView.setCheck(!check);
                Sputil.saveConfig(SettingActivity.this,ConstantValue.OPNE_UPDATE,!check);
            }
        });
    }
}
