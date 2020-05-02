package com.example.challenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.challenge1.api.model.NumberResponse;

public class ValidActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    TextView txtValid, txtCountryCode, txtCountryName, txtCarrier, txtLine, txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);

        NumberResponse response = (NumberResponse) getIntent().getSerializableExtra("response");
        txtNumber = findViewById(R.id.txtNumber);
        txtValid = findViewById(R.id.txtValid);
        txtCountryCode = findViewById(R.id.txtCountryCode);
        txtCountryName = findViewById(R.id.txtCountryName);
        txtCarrier = findViewById(R.id.txtCarrier);
        txtLine = findViewById(R.id.txtLine);
        txtValid.setText(response.getValid().toString());
        txtCountryCode.setText(response.getCountryCode());
        txtCountryName.setText(response.getCountryName());
        txtCarrier.setText(response.getCarrier());
        txtLine.setText(response.getLineType());
        txtNumber.setText(response.getNumber());

    }
}
