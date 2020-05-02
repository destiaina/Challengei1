package com.example.challenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.challenge1.api.helper.ServiceGenerator;
import com.example.challenge1.api.model.NumberResponse;
import com.example.challenge1.api.service.ApiInterface;
import com.google.android.gms.common.api.Response;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public static final String SHARED_PREFS = "sharedPrefs";
    private EditText editNumber,editCountry;
    private Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "203904306", true);
        setContentView(R.layout.activity_main);
        editNumber = findViewById(R.id.edtPhone);
        editCountry = findViewById(R.id.edtCountry);
        btnValidate = findViewById(R.id.btnValidation);

        //StartApp Ads
        StartAppSDK.setUserConsent (this,
                "pas",
                System.currentTimeMillis(),
                true);

        //Initialize Admobs
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        // Reload AdListener
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
        // Reload Ads
                  showInterstitial();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
               }
           }
        });
        // Ad Events
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(MainActivity.this,
                        "onAdFailedToLoad() with error code: " + errorCode,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            //menutup iklan
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            //    changePage();
            }
        });
    }
    // showing ads Intersitial
    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    public void validate() {
        String number = editNumber.getText().toString();
        String country_code = editCountry.getText().toString();
        String access_key = "85d67d03e2a053a3274c0fcad19dc7be";
        String format = ("1");

        if (number.length() == 0) {
            editNumber.setError("Number not valid");
        } else if (country_code.length() == 0) {
            editCountry.setError("Country not valid !");
        } else {
            ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
            Call<NumberResponse> call = service.doValidate(access_key, number, country_code, format);
            call.enqueue(new Callback<NumberResponse>() {
                @Override
                public void onResponse(Call<NumberResponse> call, Response<NumberResponse> response) {
                    if (response.isSuccessful()) {
                        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                                "sedang memvalidasi");
                        dialog.show();
                        NumberResponse numberResponse = response.body();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Intent i = new Intent(MainActivity.this, ValidActivity.class);
                                i.putExtra("response", numberResponse);
                                startActivity(i);
                            }
                        }, 2000);

                    } else {
                        Toast.makeText(MainActivity.this, "SEK SALAH BOS !", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<NumberResponse> call, Throwable t) {

                }
            });
        }
    }
    public void handleValid(View view) {
        Intent intent = new Intent(this, ValidActivity.class);
        startActivity(intent);
    }
}
