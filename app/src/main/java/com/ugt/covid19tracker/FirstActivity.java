package com.ugt.covid19tracker;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;



public class FirstActivity extends AppCompatActivity  {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);

        getSupportActionBar().hide();

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        YouTubePlayerView youTubePlayerView1 = findViewById(R.id.youtube_player_view1);
        getLifecycle().addObserver(youTubePlayerView1);


        mAdView = findViewById(R.id.adView);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.InterstitialVideo));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
                startActivity(intent);
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
    }


    public void Stats(View view) {

            Intent intent = new Intent(FirstActivity.this, MainActivity.class);
            startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).show();
    }

    public void onDailBtn(View view) {


        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(FirstActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FirstActivity.this, "Permission not given", Toast.LENGTH_SHORT).show();
            }
        };

        //ask for permission
        TedPermission.with(FirstActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:1075"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(intent);
    }

    public void bellPermission(View view) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(FirstActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FirstActivity.this, "Permission not given", Toast.LENGTH_SHORT).show();
            }
        };

        //ask for permission
        TedPermission.with(FirstActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    public void testbanner(View view) {
        Intent downloadIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/testing.html"));
        startActivity(downloadIntent);
    }

    public void helpline(View view) {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(FirstActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FirstActivity.this, "Permission not given", Toast.LENGTH_SHORT).show();
            }
        };

        //ask for permission
        TedPermission.with(FirstActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91-11-23978046"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(intent);
    }

    public void menuClick(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(FirstActivity.this, "Covid-19 is up-to-date", Toast.LENGTH_SHORT).show();
        }

    }

    public void visitSite(View view) {


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent i = new  Intent(FirstActivity.this, Site.class);
            startActivity(i);
        }


    }


    public void about(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent i = new  Intent(FirstActivity.this, About.class);
            startActivity(i);
        }
    }

    public void govSite(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent i = new  Intent(FirstActivity.this, GovSite.class);
            startActivity(i);
        }
    }

    public void arogyaSetu(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent downloadIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu&hl=en_IN"));
            startActivity(downloadIntent);
        }

    }


    public void shareIt(View view) {
        Intent a = new Intent(Intent.ACTION_SEND);

        //this is to get the app link in the playstore without launching your app.
        final String appPackageName = getApplicationContext().getPackageName();
        String strAppLink = "";

        try
        {
            strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        }
        catch (android.content.ActivityNotFoundException anfe)
        {
            strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        }
        // this is the sharing part
        a.setType("text/link");
        String shareBody = "Hey! Download by app for free and win amazing cash prizes." +
                "\n"+""+strAppLink;
        String shareSub = "APP NAME/TITLE";
        a.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        a.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(a, "Share Using"));
    }

}