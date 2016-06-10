package helloworld.example.administrator.wwmobilesafe1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import helloworld.example.administrator.wwmobilesafe1.R;

/**
 * Created by Administrator on 2016/6/10.
 */
public class SettingItemView extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_des;
    private CheckBox cb_box;
    private static String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private String destitle;
    private String deson;
    private String desoff;

    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //最后的this，表示将这个iew挂载到这个自定义控件中，相当于this.addview(view)
        View.inflate(context, R.layout.setting_item_view,this);
        //因为布局已经挂载到当前自定义view中了，所以可以直接findviewbyid
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        cb_box = (CheckBox) findViewById(R.id.cb_box);
        //获取属性值
        initValue(attrs);
        tv_title.setText(destitle);
    }

    private void initValue(AttributeSet attrs) {
        destitle = attrs.getAttributeValue(NAMESPACE, "destitle");
        deson = attrs.getAttributeValue(NAMESPACE, "deson");
        desoff = attrs.getAttributeValue(NAMESPACE, "desoff");
    }

    /**
     * 返回是否选中的状态
     * @return
     */
    public boolean isCheck(){
        return cb_box.isChecked();
    }

    /**
     * 设置选中状态
     * @param isCheck
     */
    public void setCheck(boolean isCheck){
        cb_box.setChecked(isCheck);
        if(isCheck){
            tv_des.setText(deson);
        }else{
            tv_des.setText(desoff);

        }
    }
}
