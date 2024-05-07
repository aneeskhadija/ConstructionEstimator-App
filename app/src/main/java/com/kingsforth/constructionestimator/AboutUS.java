package com.kingsforth.constructionestimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AboutUS extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TextView textView=(TextView)findViewById(R.id.aboutus);
        //banner add
        AdView mAdView = (AdView)findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(request);
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        textView.setText("Midrib technology was founded recently in 2024. Midrib technology is a trusted company providing and continuously developing cross platform applications in Android and IOS for its users from around the globe. The aim of the company is to grow its development process rapidly to make its position stable in the technology market. The company has also been developed over hundreds of mobile applications for both Android and IOS on userâ€™s demand. The company has also offered many applications for different firms and multiple organizations for their digital marketing. Midribâ€™s mission and vision is to stay updated with newly discovered technologies, and to develop and publish many applications in less time in different categories.  \n" +
                "For more information or if you have any queries please feel free to mail us at: \t\t\t     \n Email: aneesirshad3932@gmail.com\n");

    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
