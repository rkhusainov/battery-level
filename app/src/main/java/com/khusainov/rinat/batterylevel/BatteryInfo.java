package com.khusainov.rinat.batterylevel;

public class BatteryInfo {
    private final float mBatteryLevel;
    private final boolean mIsCharging;

    public BatteryInfo(float batteryLevel, boolean isCharging) {
        mBatteryLevel = batteryLevel;
        mIsCharging = isCharging;
    }


    public float getBatteryLevel() {
        return mBatteryLevel;
    }

    public boolean isCharging() {
        return mIsCharging;
    }

    @Override
    public String toString() {
        return "BatteryInfo{" +
                "mBatteryLevel=" + mBatteryLevel +
                ", mIsCharging=" + mIsCharging +
                '}';
    }
}
