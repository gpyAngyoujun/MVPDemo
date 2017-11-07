package com.jimmy.mvpcacheproxy.helper;

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
    private static boolean sPERMISSION;

    /**
     * 判断是否有运行权限
     *
     * @return
     */
    public static boolean has() {
        return sPERMISSION;
    }

    /**
     * 重新从配置文件中读取权限
     *
     * @return
     */
    public static boolean init(Context context) {
        sPERMISSION = shared(context).getBoolean(KEY_PERMISSION, false);
        return sPERMISSION;
    }

    /**
     * 更新权限
     */
    public static void update(Context context, boolean hasPermission) {
        sPERMISSION = hasPermission;
        shared(context).edit().putBoolean(KEY_PERMISSION, hasPermission).apply();
    }

    private static SharedPreferences shared(Context context) {
        return context.getSharedPreferences(CONFIG, MODE_PRIVATE);
    }
}
