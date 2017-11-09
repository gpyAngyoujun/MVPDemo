package com.jimmy.mvp.external.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author yangyoujun
 */

public class MonitorManager {

    private static MonitorManager mInstance = null;

    public static MonitorManager ins() {
        if (mInstance == null) {
            synchronized (MonitorManager.class) {
                if (mInstance == null) {
                    mInstance = new MonitorManager();
                }
            }
        }
        return mInstance;
    }

    private Map<String, List<IOnBroadcastListener>> register = new WeakHashMap<>();
    private BroadcastReceiver mReceiver;

    public void registerBroadcast(Context context, String action, IOnBroadcastListener listener) {
        if (TextUtils.isEmpty(action) || listener == null || context == null) {
            return;
        }

        Context appCtx = context.getApplicationContext();
        List<IOnBroadcastListener> listeners = register.get(action);
        if (listeners == null) {
            mReceiver = getReceiver();
            IntentFilter filter = new IntentFilter(action);
            filter.setPriority(Integer.MAX_VALUE);
            appCtx.registerReceiver(mReceiver, filter);
            listeners = new ArrayList<>();
            register.put(action, listeners);
        }
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unregisterBroadcast(String action, IOnBroadcastListener listener) {
        if (TextUtils.isEmpty(action) || listener == null) {
            return;
        }

        List<IOnBroadcastListener> listeners = register.get(action);
        if (listeners != null && listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void onDestroy(Context context) {
        mInstance = null;

        if (mReceiver != null && context != null) {
            context.getApplicationContext().unregisterReceiver(mReceiver);
            mReceiver = null;
        }

        if (register != null) {
            register.clear();
        }
    }

    private BroadcastReceiver getReceiver() {
        if (mReceiver == null) {
            mReceiver = new MonitorReceiver();
        }
        return mReceiver;
    }

    private class MonitorReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            List<IOnBroadcastListener> listeners = register.get(action);
            for (IOnBroadcastListener listener : listeners) {
                listener.onAction(context, intent);
            }
        }
    }

    public interface IOnBroadcastListener {
        void onAction(Context context, Intent intent);
    }
}
