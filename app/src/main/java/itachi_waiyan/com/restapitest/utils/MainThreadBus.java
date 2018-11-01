package itachi_waiyan.com.restapitest.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;


public class MainThreadBus extends Bus {
    private static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if(Looper.myLooper()==Looper.getMainLooper()){
            super.post(event);
        }else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(event!=null){
                        MainThreadBus.super.post(event);
                    }
                }
            });
        }
    }
}
