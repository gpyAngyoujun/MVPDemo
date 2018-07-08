package com.jimmy.mvp;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * 用于判断本app是否有权限执行，默认是true
 *
 * @author jimmy
 * @date 2018/7/7
 */
public class AppPermission {

    /**
     * 从用户的操作中获取到权限的结果后回调
     */
    public interface Callback {
        /**
         * 设置权限后回调此方法
         *
         * @param isAgree 是否同意权限，true为同意，false为拒绝
         */
        void result(boolean isAgree);
    }


    private static final String CONFIG = "CONFIG";
    private static final String KEY_PERMISSION = "Permission";
    private boolean sPERMISSION;

    public static AppPermission ins() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final AppPermission INSTANCE = new AppPermission();
    }

    private AppPermission() {
    }

    /**
     * 判断是否有运行权限
     */
    public boolean has() {
        return sPERMISSION;
    }

    /**
     * 重新从配置文件中读取权限
     */
    public boolean get(Context context) {
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
