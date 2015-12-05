/**
 * Cobub Razor
 *
 * An open source analytics android sdk for mobile applications
 *
 * @package		Cobub Razor
 * @author		WBTECH Dev Team
 * @copyright	Copyright (c) 2011 - 2012, NanJing Western Bridge Co.,Ltd.
 * @license		http://www.cobub.com/products/cobub-razor/license
 * @link		http://www.cobub.com/products/cobub-razor/
 * @since		Version 0.1
 * @filesource
 */
package com.lzc.company.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CommonUtil {
	/**
	 * checkPermissions
	 * 
	 * @param context
	 * @param permission
	 * @return true or false
	 */
	public static boolean checkPermissions(Context context, String permission) {
		PackageManager localPackageManager = context.getPackageManager();
		return localPackageManager.checkPermission(permission,
				context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
	}

	/**
	 * Determine the current networking is WIFI
	 * 
	 * @param context
	 * @return
	 */
	public static boolean CurrentNoteworkTypeIsWIFI(Context context) {
		ConnectivityManager connectionManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return connectionManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
	}

	/**
	 * Judge wifi is available
	 * 
	 * @param inContext
	 * @return
	 */
	public static boolean isWiFiActive(Context inContext) {
		if (checkPermissions(inContext, "android.permission.ACCESS_WIFI_STATE")) {
			Context context = inContext.getApplicationContext();
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getTypeName().equals("WIFI")
								&& info[i].isConnected()) {
							return true;
						}
					}
				}
			}
			return false;
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("lost permission",
						"lost--->android.permission.ACCESS_WIFI_STATE");
			}

			return false;
		}
	}

	/**
	 * Testing equipment networking and networking WIFI
	 * 
	 * @param context
	 * @return true or false
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (checkPermissions(context, "android.permission.INTERNET")) {
			ConnectivityManager cManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				return true;
			} else {
				if (UmsConstants.DebugMode) {
					Log.e("error", "Network error");
				}

				return false;
			}

		} else {
			if (UmsConstants.DebugMode) {
				Log.e(" lost  permission",
						"lost----> android.permission.INTERNET");
			}

			return false;
		}

	}

	/**
	 * Get the current time format yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return localSimpleDateFormat.format(date);
	}

	/**
	 * get APPKEY
	 * 
	 * @param context
	 * @return appkey
	 */
	public static String getAppKey(Context paramContext) {
		String umsAppkey;
		try {
			PackageManager localPackageManager = paramContext
					.getPackageManager();
			ApplicationInfo localApplicationInfo = localPackageManager
					.getApplicationInfo(paramContext.getPackageName(), 128);
			if (localApplicationInfo != null) {
				String str = localApplicationInfo.metaData
						.getString("UMS_APPKEY");
				if (str != null) {
					umsAppkey = str;
					return umsAppkey.toString();
				}
				if (UmsConstants.DebugMode)
					Log.e("UmsAgent",
							"Could not read UMS_APPKEY meta-data from AndroidManifest.xml.");
			}
		} catch (Exception localException) {
			if (UmsConstants.DebugMode) {
				Log.e("UmsAgent",
						"Could not read UMS_APPKEY meta-data from AndroidManifest.xml.");
				localException.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get currnet activity's name
	 * 
	 * @param context
	 * @return
	 */
	public static String getActivityName(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		if (checkPermissions(context, "android.permission.GET_TASKS")) {
			ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
			return cn.getShortClassName();
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("lost permission", "android.permission.GET_TASKS");
			}

			return null;
		}

	}

	/**
	 * get PackageName
	 * 
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		if (checkPermissions(context, "android.permission.GET_TASKS")) {
			ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
			return cn.getPackageName();
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("lost permission", "android.permission.GET_TASKS");
			}

			return null;
		}

	}

	/**
	 * get OS number
	 * 
	 * @param context
	 * @return
	 */
	public static String getOsVersion(Context context) {
		String osVersion = "";
		if (checkPhoneState(context)) {
			osVersion = android.os.Build.VERSION.RELEASE;
			if (UmsConstants.DebugMode) {
				printLog("android_osVersion", "OsVerson" + osVersion);
			}

			return osVersion;
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("android_osVersion", "OsVerson get failed");
			}

			return null;
		}
	}

	/**
	 * get deviceid
	 * 
	 * @param context
	 *            add <uses-permission android:name="READ_PHONE_STATE" />
	 * @return
	 */
	public static String getDeviceID(Context context) {
		if (checkPermissions(context, "android.permission.READ_PHONE_STATE")) {
			String deviceId = "";
			if (checkPhoneState(context)) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				deviceId = tm.getDeviceId();
			}
			if (deviceId != null) {
				if (UmsConstants.DebugMode) {
					printLog("commonUtil", "deviceId:" + deviceId);
				}

				return deviceId;
			} else {
				if (UmsConstants.DebugMode) {
					Log.e("commonUtil", "deviceId is null");
				}

				return null;
			}
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("lost permissioin",
						"lost----->android.permission.READ_PHONE_STATE");
			}

			return "";
		}
	}

	/**
	 * check phone _state is readied ;
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkPhoneState(Context context) {
		PackageManager packageManager = context.getPackageManager();
		if (packageManager
				.checkPermission("android.permission.READ_PHONE_STATE",
						context.getPackageName()) != 0) {
			return false;
		}
		return true;
	}

	/**
	 * get sdk number
	 * 
	 * @param paramContext
	 * @return
	 */
	public static String getSdkVersion(Context paramContext) {
		String osVersion = "";
		if (!checkPhoneState(paramContext)) {
			osVersion = android.os.Build.VERSION.RELEASE;
			if (UmsConstants.DebugMode) {
				Log.e("android_osVersion", "OsVerson" + osVersion);
			}

			return osVersion;
		} else {
			if (UmsConstants.DebugMode) {
				Log.e("android_osVersion", "OsVerson get failed");
			}

			return null;
		}
	}

	/**
	 * Get the version number of the current program
	 * 
	 * @param context
	 * @return
	 */

	public static String getCurVersion(Context context) {
		String curversion = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			curversion = pi.versionName;
			if (curversion == null || curversion.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			if (UmsConstants.DebugMode) {
				Log.e("VersionInfo", "Exception", e);
			}

		}
		return curversion;
	}

	/**
	 * Get the current send model
	 * 
	 * @param context
	 * @return
	 */
	public static int getReportPolicyMode(Context context) {
		String str = context.getPackageName();
		SharedPreferences localSharedPreferences = context
				.getSharedPreferences("ums_agent_online_setting_" + str, 0);
		int type = localSharedPreferences.getInt("ums_local_report_policy", 0);
		return type;
	}

	/**
	 * To determine whether it contains a gyroscope
	 * 
	 * @return
	 */
	public static boolean isHaveGravity(Context context) {
		SensorManager manager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		if (manager == null) {
			return false;
		}
		return true;
	}

	/**
	 * Get the current networking
	 * 
	 * @param context
	 * @return WIFI or MOBILE
	 */
	public static String getNetworkType(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		int type = manager.getNetworkType();
		String typeString = "UNKOWN";
		if (type == TelephonyManager.NETWORK_TYPE_CDMA) {
			typeString = "CDMA";
		}
		if (type == TelephonyManager.NETWORK_TYPE_EDGE) {
			typeString = "EDGE";
		}
		if (type == TelephonyManager.NETWORK_TYPE_EVDO_0) {
			typeString = "EVDO_0";
		}
		if (type == TelephonyManager.NETWORK_TYPE_EVDO_A) {
			typeString = "EVDO_A";
		}
		if (type == TelephonyManager.NETWORK_TYPE_GPRS) {
			typeString = "GPRS";
		}
		if (type == TelephonyManager.NETWORK_TYPE_HSDPA) {
			typeString = "HSDPA";
		}
		if (type == TelephonyManager.NETWORK_TYPE_HSPA) {
			typeString = "HSPA";
		}
		if (type == TelephonyManager.NETWORK_TYPE_HSUPA) {
			typeString = "HSUPA";
		}
		if (type == TelephonyManager.NETWORK_TYPE_UMTS) {
			typeString = "UMTS";
		}
		if (type == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
			typeString = "UNKOWN";
		}

		return typeString;
	}

	/**
	 * Determine the current network type
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkTypeWifi(Context context) {
		// TODO Auto-generated method stub

		if (checkPermissions(context, "android.permission.INTERNET")) {
			ConnectivityManager cManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cManager.getActiveNetworkInfo();

			if (info != null && info.isAvailable()
					&& info.getTypeName().equals("WIFI")) {
				return true;
			} else {
				if (UmsConstants.DebugMode) {
					Log.e("error", "Network not wifi");
				}
				return false;
			}
		} else {
			if (UmsConstants.DebugMode) {
				Log.e(" lost  permission",
						"lost----> android.permission.INTERNET");
			}
			return false;
		}

	}

	/**
	 * Get the current application version number
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersion(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			if (UmsConstants.DebugMode) {
				Log.e("UmsAgent", "Exception", e);
			}

		}
		return versionName;
	}

	/**
	 * Set the output log
	 * 
	 * @param tag
	 * @param log
	 */

	public static void printLog(String tag, String log) {
		if (UmsConstants.DebugMode == true) {
			Log.d(tag, log);
		}
	}

	/**
	 * install apk
	 * 
	 * @param url
	 */
	public static void installApk(Context context, File apkfile) {
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		context.startActivity(i);
	}

	/**
	 * 判断手机是否安装了某个程序，如果安装了此程序，删除它。
	 * 
	 * @param context
	 * @param name
	 */
	public static void uninstallSoftware(Context context, String name) {
		final PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo pInfo = packageManager.getPackageInfo(name,
					PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
			if (pInfo != null) {
				// 删除软件
				Uri uri = Uri.parse("package:" + name);
				Intent intent = new Intent(Intent.ACTION_DELETE, uri);
				context.startActivity(intent);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断手机是否安装了某个程序，如果安装了此程序，删除它。
	 * 
	 * @param context
	 * @param name
	 */
	public static boolean isinstallSoftware(Context context, String name) {
		boolean isinstall = false;
		final PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo pInfo = packageManager.getPackageInfo(name,
					PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
			if (pInfo != null) {
				isinstall = true;
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return isinstall;
	}
	/**
	 * 系统中所有安装过的软件
	 * 
	 * @param context
	 * @param name
	 */
	public static void isinstallSoftware1(Context context, String name) {
		  final PackageManager packageManager = context.getPackageManager();//获取packagemanager 
	        List< PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息 
	            if(pinfo != null){ 
	            for(int i = 0; i < pinfo.size(); i++){ 
	                String packName = pinfo.get(i).packageName; 
	                Log.d("info", "-->"+packName);
	            } 
	        } 
	}
	/**
	 * 判断程序是否安装到SD卡上
	 * 
	 * @param context
	 * @param name
	 */
	public static void isInstallOnSd(Context context, String name) {
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appInfo;
		try {
			appInfo = pm.getApplicationInfo(name, 0);
			if ((appInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
				// App on sdcard
				Log.d("info", "app on sd");
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

}
