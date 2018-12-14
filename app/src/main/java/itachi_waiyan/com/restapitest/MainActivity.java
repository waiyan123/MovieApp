package itachi_waiyan.com.restapitest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import itachi_waiyan.com.restapitest.fragment.HomeFragment;
import itachi_waiyan.com.restapitest.fragment.MenuFragment;
import itachi_waiyan.com.restapitest.fragment.SpoilerFragment;
import itachi_waiyan.com.restapitest.utils.BottomLayoutHelper;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class MainActivity extends AppCompatActivity {


    BottomLayoutHelper bottomLayoutHelper;
    BottomLayoutHelper.BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener;
    Fragment homeFragment,spoilerFragment,menuFragment;
    int currentPage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        spoilerFragment = new SpoilerFragment();
        menuFragment = new MenuFragment();

        showFragment(homeFragment,HomeFragment.class.getName());

        BusProvider.getInstance().register(this);
        bottomTabLayoutHelperSelectListener = new BottomLayoutHelper.BottomTabLayoutHelperSelectListener() {
            @Override
            public void selected(int i) {
                switch (i){
                    case 0 : showFragment(homeFragment,HomeFragment.class.getName());
                        break;
                    case 1 : showFragment(spoilerFragment,SpoilerFragment.class.getName());
                        break;
                    case 3 : showFragment(menuFragment,MenuFragment.class.getName());
                }
            }
        };
        bottomLayoutHelper = BottomLayoutHelper.withThis(this,bottomTabLayoutHelperSelectListener).setUp((LinearLayout) findViewById(R.id.bottomTab));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
    private void removeFragment(int i) {
        try {
            switch (i) {
                case 0: {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(homeFragment)
                            .commit();
                    homeFragment = null;
                    break;
                }
                case 1: {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(spoilerFragment)
                            .commit();
                    spoilerFragment = null;
                    break;
                }
//                case 2: {
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .remove(timerFragement)
//                            .commit();
//                    timerFragement = null;
//                    break;
//                }
                case 3: {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(menuFragment)
                            .commit();
                    menuFragment = null;
                    break;
                }
            }
        }
        catch (IllegalStateException ex) {
            Log.e("IllegalStateException", ex.getLocalizedMessage());
        }
    }
    public void showFragment(Fragment fragment,String name){
        if(getSupportFragmentManager().findFragmentById(R.id.mainFragment) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.mainFragment, fragment, name)
                    .commit();
        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragment, fragment, name)
                    .commit();
        }
    }
}
