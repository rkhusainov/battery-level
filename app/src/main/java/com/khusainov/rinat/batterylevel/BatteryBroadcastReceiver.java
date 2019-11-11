package com.khusainov.rinat.batterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import java.lang.ref.WeakReference;

public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private WeakReference<BatteryChangeListener> mBatteryChangeListener;

    public BatteryBroadcastReceiver(BatteryChangeListener batteryChangeListener) {
        mBatteryChangeListener = new WeakReference<>(batteryChangeListener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BatteryChangeListener listener = mBatteryChangeListener.get();
        if (listener != null) {
            BatteryInfo batteryInfo = createBatteryInfo(intent);
            listener.onChange(batteryInfo);
        }
    }

    private float getBatteryLevel(Intent intent) {

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return level / (float) scale;
    }

    private boolean isCharging(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    private BatteryInfo createBatteryInfo(Intent intent) {
        return new BatteryInfo(getBatteryLevel(intent), isCharging(intent));
    }
}
