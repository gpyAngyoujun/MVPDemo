package com.jimmy.mvp.external.helper;

import android.util.Log;

/**
 * @author yangyoujun
 * @Date 17-11-4
 */
public class LogHelper {

    private static final LogInternal LOG = new LogInternal(LogHelper.class.getSimpleName());

    private static LogInternal ins() {
        return LOG;
    }

    private LogHelper() {
    }

    /**
     * 设置调试开关
     */
    public static void setupDbg(IOnLogging logging) {
        ins().setupDbg(logging);
    }

    public static void resetDbg() {
        ins().resetDbg();
    }

    public static void setupTAG(String tag) {
        ins().setupTag(tag);
    }

    /**
     * 必须设置了调试开关才能打印d的log
     * {@link LogHelper#setupDbg(IOnLogging)}
     */
    public static void d(Object... msg) {
        ins().debug(msg);
    }

    /**
     * 打印i的信息，无论如何都会打印
     */
    public static void i(Object... msg) {
        ins().info(msg);
    }

    public interface IOnLogging {
        /**
         * 判断当前是否为调试模式
         *
         * @return true则是调试模式，false则不是
         */
        boolean isDebug();
    }

    private static class LogInternal {

        private IOnLogging mLogging;
        private String LOG_TAG;

        LogInternal(String tag) {
            LOG_TAG = tag;
        }

        void setupDbg(IOnLogging logging) {
            mLogging = logging;
        }

        void resetDbg() {
            mLogging = null;
        }

        void setupTag(String tag) {
            LOG_TAG = tag;
        }

        void debug(Object... msg) {
            if (mLogging != null && mLogging.isDebug()) {
                Log.d(LOG_TAG, compose(msg));
            }
        }

        void info(Object... msg) {
            Log.i(LOG_TAG, compose(msg));
        }

        private String compose(Object... msg) {
            if (msg == null && msg.length < 1) {
                return String.valueOf(msg);
            }
            final StringBuilder builder = new StringBuilder();
            for (Object o : msg) {
                builder.append(String.valueOf(o));
            }
            return builder.toString();
        }
    }
}
