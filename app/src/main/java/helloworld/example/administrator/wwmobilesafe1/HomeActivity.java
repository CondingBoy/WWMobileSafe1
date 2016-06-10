package helloworld.example.administrator.wwmobilesafe1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import helloworld.example.administrator.wwmobilesafe1.activity.SettingActivity;
import helloworld.example.administrator.wwmobilesafe1.utils.ConstantValue;
import helloworld.example.administrator.wwmobilesafe1.utils.Sputil;
import helloworld.example.administrator.wwmobilesafe1.utils.ToastUtil;

public class HomeActivity extends AppCompatActivity {

    private GridView gv_home;
    private String[] mTitleStrs;
    private int[] mDrawableIds;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        Log.e(TAG, "onCreate: " );
    }

    private void initData() {
        //准备数据(文字(9组),图片(9张))
        mTitleStrs = new String[]{
                "手机防盗","通信卫士","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"
        };

        mDrawableIds = new int[]{
                R.mipmap.home_safe,R.mipmap.home_callmsgsafe,
                R.mipmap.home_apps,R.mipmap.home_taskmanager,
                R.mipmap.home_netmanager,R.mipmap.home_trojan,
                R.mipmap.home_sysoptimize,R.mipmap.home_tools,R.mipmap.home_settings
        };

        gv_home.setAdapter(new MyGridViewAdapter());
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showDialog();
                        break;
                    case 8:
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                        break;
                }
            }
        });
    }

    private void showDialog() {
        String psd = Sputil.getString(this, ConstantValue.MOBIEL_SAFE_PSD,"");
        if(psd.isEmpty()){
            //第一次使用，设置密码
            showSetDialog();
        }else{
            //不是第一次使用，确认密码
            showConfirmDialog();
        }
    }

    /**
     * 确认密码对话框
     */
    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        final View view = View.inflate(this,R.layout.confirmpsd_dialog,null);
        final Button confirm = (Button) view.findViewById(R.id.bt_confirm);
        Button cancle = (Button) view.findViewById(R.id.bt_cancle);
        //确认
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置密码
                EditText etPsd = (EditText) view.findViewById(R.id.et_psd);
                String confirmPsd = etPsd.getText().toString().trim();
                String savePsd = Sputil.getString(HomeActivity.this,ConstantValue.MOBIEL_SAFE_PSD,"");
                if(!TextUtils.isEmpty(confirmPsd)){
                    if(confirmPsd.equals(savePsd)){
                        //密码输入正确，页面跳转
                         ToastUtil.show(getApplicationContext(),"页面跳转");
                        alertDialog.dismiss();
                    }else {
                        ToastUtil.show(getApplicationContext(),"密码错误");
                    }
                }else {
                    ToastUtil.show(getApplicationContext(),"密码不能为空");
                }

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //设置自定义布局
        alertDialog.setView(view);
        alertDialog.show();
    }

    private void showSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        final View view = View.inflate(this,R.layout.setpsd_dialog,null);
        Button confirm = (Button) view.findViewById(R.id.bt_confirm);
        Button cancle = (Button) view.findViewById(R.id.bt_cancle);
        //确认
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置密码
                EditText etPsd = (EditText) view.findViewById(R.id.et_psd);
                EditText etPsdConfirm = (EditText) view.findViewById(R.id.et_psd_confirm);
                String psd = etPsd.getText().toString();
                String psdConfirm = etPsdConfirm.getText().toString();
                if(psd!=null&&psdConfirm!=null){
                    if(psd.equals(psdConfirm)){
                        //保存密码
                        Sputil.saveString(HomeActivity.this,ConstantValue.MOBIEL_SAFE_PSD,psd);
                        //跳转页面
                        ToastUtil.show(getApplicationContext(),"页面跳转");
                        alertDialog.dismiss();

                    }else {
                        ToastUtil.show(getApplicationContext(),"输入的密码不一致");
                    }
                }else {
                    ToastUtil.show(getApplicationContext(),"密码不能为空");
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //设置自定义布局
        alertDialog.setView(view);
        alertDialog.show();
    }

    private void initView() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }
    class MyGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTitleStrs.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(),R.layout.gridview_item,null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            ImageView iv_icon = (ImageView)view.findViewById(R.id.iv_icon);
            tv_title.setText(mTitleStrs[position]);
            iv_icon.setImageResource(mDrawableIds[position]);
            return view;
        }
    }
}
