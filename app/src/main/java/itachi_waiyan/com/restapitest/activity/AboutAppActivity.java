package itachi_waiyan.com.restapitest.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import itachi_waiyan.com.restapitest.R;

public class AboutAppActivity extends AppCompatActivity implements View.OnClickListener {

    View rlSayarFacebook,rlMyanmarLinks,rlWaiyanPh,rlWaiyanFb,rlWaiyanLinkedIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        rlSayarFacebook = findViewById(R.id.rl_sayar);
        rlMyanmarLinks = findViewById(R.id.rl_myanmar_link);
        rlWaiyanPh = findViewById(R.id.rl_waiyan_phone);
        rlWaiyanFb = findViewById(R.id.rl_waiyan_facebook);
        rlWaiyanLinkedIn = findViewById(R.id.rl_waiyan_linkedIn);

        rlSayarFacebook.setOnClickListener(this);
        rlMyanmarLinks.setOnClickListener(this);
        rlWaiyanPh.setOnClickListener(this);
        rlWaiyanFb.setOnClickListener(this);
        rlWaiyanLinkedIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.rl_sayar :
                intent = getOpenFacebookIntent(this,"soethihanaung");
                startActivity(intent);
                break;
            case R.id.rl_myanmar_link :
                intent = getOpenFacebookIntent(this,"myanmarlinks.net/");
                startActivity(intent);
                break;
            case R.id.rl_waiyan_phone :
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "09761239945")));
                break;
            case R.id.rl_waiyan_facebook :
                intent = getOpenFacebookIntent(this,"waiyan.aung.7");
                startActivity(intent);
                break;
            case R.id.rl_waiyan_linkedIn :
                openLinkedInPage("waiyan-aung-017108131/");
                break;
        }
    }

    public static Intent getOpenFacebookIntent(Context context,String address) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+address));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+address));
        }
    }

    public void openLinkedInPage(String linkedId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/" + linkedId));
        startActivity(intent);
    }
}
