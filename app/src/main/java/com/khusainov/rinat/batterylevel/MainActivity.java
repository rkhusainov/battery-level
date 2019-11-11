package com.khusainov.rinat.batterylevel;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BatteryChangeListener {

    private static final int IMG_CLIP = 10_000;

    private BatteryBroadcastReceiver mBatteryBroadcastReceiver;
    private TextView mTxtIsCharging;
    private ImageView mImageBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtIsCharging = findViewById(R.id.txt_is_charging);
        mImageBattery = findViewById(R.id.img_battery);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        destroyReceiver();
    }

    private void setupReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        mBatteryBroadcastReceiver = new BatteryBroadcastReceiver(this);
        registerReceiver(mBatteryBroadcastReceiver, intentFilter);
    }

    private void destroyReceiver() {
        unregisterReceiver(mBatteryBroadcastReceiver);
        mBatteryBroadcastReceiver = null;
    }

    @Override
    public void onChange(BatteryInfo batteryInfo) {
        mImageBattery.setImageLevel((int) (batteryInfo.getBatteryLevel() * IMG_CLIP));
        mTxtIsCharging.setText(String.valueOf(batteryInfo.isCharging()));
    }
}
