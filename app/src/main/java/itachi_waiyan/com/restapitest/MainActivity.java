package itachi_waiyan.com.restapitest;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import itachi_waiyan.com.restapitest.fragment.RecentFragment;
import itachi_waiyan.com.restapitest.fragment.HomeFragment;
import itachi_waiyan.com.restapitest.fragment.MenuFragment;
import itachi_waiyan.com.restapitest.fragment.SpoilerFragment;
import itachi_waiyan.com.restapitest.utils.BottomLayoutHelper;
import itachi_waiyan.com.restapitest.utils.BusProvider;

public class MainActivity extends AppCompatActivity {


    BottomLayoutHelper bottomLayoutHelper;
    BottomLayoutHelper.BottomTabLayoutHelperSelectListener bottomTabLayoutHelperSelectListener;
    Fragment homeFragment,spoilerFragment,menuFragment,recentFragment;
    int currentPage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        homeFragment = new HomeFragment();
        spoilerFragment = new SpoilerFragment();
        menuFragment = new MenuFragment();
        recentFragment = new RecentFragment();

        showFragment(homeFragment,HomeFragment.class.getName());

        BusProvider.getInstance().register(this);
        bottomTabLayoutHelperSelectListener = new BottomLayoutHelper.BottomTabLayoutHelperSelectListener() {
            @Override
            public void selected(int i) {
                switch (i){
                    case 0 :
                        showFragment(homeFragment,HomeFragment.class.getName());
                        currentPage = 0;
                        break;
                    case 1 :
                        showFragment(spoilerFragment,SpoilerFragment.class.getName());
                        currentPage = 1;
                        break;
                    case 2 :
                        showFragment(recentFragment,RecentFragment.class.getName());
                        currentPage = 2;
                        break;
                    case 3 :
                        showFragment(menuFragment,MenuFragment.class.getName());
                        currentPage = 3;
                        break;
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
                case 2: {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(recentFragment)
                            .commit();
                    recentFragment = null;
                    break;
                }
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

    @Override
    public void onBackPressed() {
        if (currentPage == 0) {
                finish();
                super.onBackPressed();
        } else {
            showFragment(homeFragment,HomeFragment.class.getName());
            currentPage = 0;
            BottomLayoutHelper.reset(0);
        }
    }
}
