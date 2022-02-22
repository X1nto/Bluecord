package mods.anti;

import android.app.IntentService;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import mods.preference.Prefs;

public class AntiProximitySensor implements SensorEventListener {
    private static final String TAG = "AntiProximitySensor";

    public static AntiProximitySensor start(IntentService intentService) {
        if (!Prefs.getBoolean("blue.disable.proximity", false)) {
            return null;
        }
        try {
            AntiProximitySensor antiProximitySensor = new AntiProximitySensor();
            SensorManager sensorManager = (SensorManager) intentService.getApplication().getSystemService(Context.SENSOR_SERVICE);
            sensorManager.registerListener(antiProximitySensor, sensorManager.getDefaultSensor(8), 3);
            Log.e(TAG, "started successfully");
            return antiProximitySensor;
        } catch (Throwable th) {
            Log.e(TAG, "failed to start proximity sensor override", th);
            return null;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {}

    public void stop(IntentService intentService) {
        try {
            ((SensorManager) intentService.getApplication().getSystemService(Context.SENSOR_SERVICE)).unregisterListener(this);
        } catch (Throwable th) {
            Log.e(TAG, "failed to stop proximity sensor override", th);
        }
    }
}
