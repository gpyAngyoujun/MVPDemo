package com.jimmy.mvp.external.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created on 17-11-2.
 *
 * @author yangyoujun
 */
public final class PermissionHelper {

    private static final String CONFIG = "CONFIG";
    private static final String KEY_PERMISSION = "Permission";
    private boolean sPERMISSION;

    private static PermissionHelper mInstance = null;

    public static PermissionHelper ins() {
        if (mInstance == null) {
            synchronized (PermissionHelper.class) {
                if (mInstance == null) {
                    mInstance = new PermissionHelper();
                }
            }
        }
        return mInstance;
    }

    private PermissionHelper() {
    }

    /**
     * 判断是否有运行权限
     *
     * @return
     */
    public boolean has() {
        return sPERMISSION;
    }

    /**
     * 重新从配置文件中读取权限
     *
     * @return
     */
    public boolean init(Context context) {
        sPERMISSION = shared(context).getBoolean(KEY_PERMISSION, false);
        return sPERMISSION;
    }

    /**
     * 更新权限
     */
    public void update(Context context, boolean hasPermission) {
        sPERMISSION = hasPermission;
        shared(context).edit().putBoolean(KEY_PERMISSION, hasPermission).apply();
    }

    private SharedPreferences shared(Context context) {
        return context.getSharedPreferences(CONFIG, MODE_PRIVATE);
    }
}
