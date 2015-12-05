package com.lzc.company.utils;
import android.util.Log;
/**
 * 安卓系统日志类。通过setLoggerLevel确定日志显示等级。
 * @author ada
 *
 */
public class LogUtils {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int DEFAULT =-1;
    public static final int MAX =8;

    public static int v(String tag, String msg) {
    	if(LoggerLevel>VERBOSE){
    		return DEFAULT;
    	}
        return Log.v(tag, msg);
    }
    public static int v(String tag, String msg, Throwable tr) {
    	if(LoggerLevel>VERBOSE){
    		return DEFAULT;
    	}
        return Log.v(tag, msg,tr);
    }
    public static int d(String tag, String msg) {
    	if(LoggerLevel>DEBUG){
    		return DEFAULT;
    	}
        return Log.d(tag, msg);
    }

    private static int LoggerLevel;
    public static int getLoggerLevel() {
		return LoggerLevel;
	}

	public static void setLoggerLevel(int loggerLevel) {
		LoggerLevel = loggerLevel;
	}
    public static int d(String tag, String msg, Throwable tr) {
    	if(LoggerLevel>DEBUG){
    		return DEFAULT;
    	}
        return Log.d(tag, msg,tr);
    }
    public static int i(String tag, String msg) {
    	if(LoggerLevel>INFO){
    		return DEFAULT;
    	}
        return Log.i(tag, msg);
    }
    public static int i(String tag, String msg, Throwable tr) {
    	if(LoggerLevel>INFO){
    		return DEFAULT;
    	}
        return Log.i(tag, msg,tr);
    }
    public static int w(String tag, String msg) {
    	if(LoggerLevel>WARN){
    		return DEFAULT;
    	}
        return Log.w(tag, msg);
    }
    public static int w(String tag, String msg, Throwable tr) {
    	if(LoggerLevel>WARN){
    		return DEFAULT;
    	}
        return Log.w(tag, msg,tr);
    }
    public static int w(String tag, Throwable tr) {
     	if(LoggerLevel>WARN){
    		return DEFAULT;
    	}
        return Log.w(tag,tr);
    }
    public static int e(String tag, String msg) {
    	if(LoggerLevel>ERROR){
    		return DEFAULT;
    	}
        return Log.e(tag, msg);
    }
    public static int e(String tag, String msg, Throwable tr) {
    	if(LoggerLevel>ERROR){
    		return DEFAULT;
    	}
        return Log.e(tag, msg,tr);
    }
}
