package su.leff.smartcounter.colorer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import su.leff.smartcounter.colorer.R;
import su.leff.smartcounter.colorer.ResourceManager;

public class CustomButton extends ConstraintLayout implements View.OnClickListener {

    OnClickListener listener;

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_custom, this, true);

        getRootView().setBackgroundResource(ResourceManager.INSTANCE.getButtonBackgroundResource());
    }

    public void setDrawableResource(int res){
        ((ImageView) findViewById(R.id.imgvIcon)).setImageResource(res);
    }

    public void setTitleText(String text){
        ((TextView) findViewById(R.id.txvTitle)).setText(text);
    }

    public CustomButton(Context context) {
        this(context, null);
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    public void setOnClickListener(OnClickListener listener) {
        if (!isClickable()) {
            setClickable(true);
        }
        this.listener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(listener != null) listener.onClick(this);
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            if(listener != null) listener.onClick(this);
        }
        return super.dispatchKeyEvent(event);
    }
}
