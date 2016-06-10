package helloworld.example.administrator.wwmobilesafe1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自动获取焦点的TextView
 * Created by Administrator on 2016/6/10.
 */
public class FocusTextView extends TextView {
    public FocusTextView(Context context) {
        super(context);
    }

    public FocusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
