package com.example.tip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private double billAmount = 0.0;
    private double customPercent = 0.18;

    private TextView tip15TextView;
    private TextView total15TextView;
    private TextView tipCustomTextView;
    private TextView totalCustomTextView;
    private EditText amountEditText;
    private TextView percentCustomTextView;
    private SeekBar customTipSeekBar;
    TextWatcherHandler editTextHandler = new TextWatcherHandler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tip15TextView = (TextView)findViewById(R.id.tip15TextView);
        total15TextView = (TextView)findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView)findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView)findViewById(R.id.totalCustomTextView);
        customTipSeekBar = (SeekBar)findViewById(R.id.customTipSeekBar);
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        percentCustomTextView = (TextView)findViewById(R.id.percentCustomTextView);
        OnSeekBarChangeHandler seekBarHandler = new OnSeekBarChangeHandler();
        customTipSeekBar.setOnSeekBarChangeListener(seekBarHandler);
        amountEditText.addTextChangedListener(editTextHandler);


        Update15();
        UpdateCustom();

    }

    private void Update15(){
        double tip = billAmount * 0.15;
        double total = billAmount + tip;

        tip15TextView.setText(String.format("%.2f",tip));
        total15TextView.setText(String.format("%.2f",total));
    }

    private void UpdateCustom(){
        double tip = billAmount * customPercent;
        double total = billAmount + tip;

        percentCustomTextView.setText(String.format("%.0f",customPercent * 100) + "%");
        tipCustomTextView.setText(String.format("%.2f",tip));
        totalCustomTextView.setText(String.format("%.2f",total));
    }

    public class OnSeekBarChangeHandler implements
            SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            customPercent = progress/100.0;
            UpdateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    public class TextWatcherHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                billAmount = Double.parseDouble(s.toString());
            }catch(NumberFormatException e){
                billAmount = 0.0;
            }
            Update15();
            UpdateCustom();
        }

        public void afterTextChanged(Editable s) {}
    }
}