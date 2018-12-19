package itachi_waiyan.com.restapitest.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import itachi_waiyan.com.restapitest.R;

public class BottomLayoutHelper {

    static int tabCount = 4;
    static LinearLayout[]tabs = new LinearLayout[tabCount];
    static View [] indicators = new View[tabCount];
    static ImageView [] icons = new ImageView[tabCount];

    private static Context context_;

    private static BottomLayoutHelper bottomLayoutHelper;
    private static BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener_;

    public static BottomLayoutHelper withThis(Context context ,BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener ){
        bottomLayoutHelper = new BottomLayoutHelper();
        context_ = context;
        setBottomTabLayoutHelperSelectListener(bottomTabLayoutHelperSelectListener);
        return bottomLayoutHelper;
    }

    public static BottomLayoutHelper setUp(LinearLayout bottomLayout){
        for(int i =0 ;i<tabCount;i++){
            tabs[i] = (LinearLayout) bottomLayout.getChildAt(i);
            indicators[i] = tabs[i].getChildAt(0);
            icons[i] = (ImageView) tabs[i].getChildAt(1);
            final int j = i;
            tabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reset(j);
                    bottomTabLayoutHelperSelectListener_.selected(j);
                }
            });
        }
        Log.d("indicator", String.valueOf(indicators.length));
        return bottomLayoutHelper;
    }

    public static void reset(int untouchIndex){
        for(int i=0;i<tabCount;i++){
            if(i==untouchIndex){
                select(i,View.VISIBLE,R.color.tabSelected);
            }
            else select(i,View.INVISIBLE,R.color.themeGrey);
        }
    }

    private static void select(int i,int visible,int color){
        indicators[i].setVisibility(visible);
        tabs[i].setBackgroundColor(context_.getResources().getColor(color));
//        icons[i].setColorFilter(ContextCompat.getColor(context_, color));
    }


    private static void setBottomTabLayoutHelperSelectListener(BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener){
        bottomTabLayoutHelperSelectListener_ = bottomTabLayoutHelperSelectListener;
    }

        public interface BottomTabLayoutHelperSelectListener {
            void selected(int i);
        }
}
