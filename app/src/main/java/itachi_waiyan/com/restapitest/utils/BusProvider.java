package itachi_waiyan.com.restapitest.utils;


import com.squareup.otto.Bus;

public class BusProvider {
    private static final MainThreadBus MAIN_THREAD_BUS = new MainThreadBus();

    public static Bus getInstance(){
        return MAIN_THREAD_BUS;
    }

    private BusProvider(){

    }
}
