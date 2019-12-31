package com.amt.indiaiptv.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by Administrator on 2019/4/17.
 */
public class CheckNet {

    /**
     * 判断当前网络有没有联网
     * 并且判断是有线还是无线
     * 0:无网络连接
     * 1：有线网络
     * 2：无线网络
     * @return
     */
    public static int getNetMode(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            return 0;
        }
        boolean iscon = info.isAvailable();
        LogUtils.d( "网络连接 =" + iscon + "，连接方式：" + info.getType() + " ," + info.getTypeName());
        if (!iscon) {
            return 0;
        }
        if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
            return 1;
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Android 6.0 之前（不包括6.0）获取mac地址
     * 必须的权限 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
     * @param context * @return
     */
    public static String getMacDefault(Context context) {
//        String mac = "";
//        if (context == null) {
//            return mac;
//        }
//        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = null;
//        try {
//            info = wifi.getConnectionInfo();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (info == null) {
//            return getLocalEthernetMacAddress();
//        }
//        mac = info.getMacAddress();
//        if (!TextUtils.isEmpty(mac)) {
//            mac = mac.toUpperCase(Locale.ENGLISH);
//        }
//        return mac;
        String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return m_szAndroidID;
    }



    public static String getLocalEthernetMacAddress() {
        String mac=null;
        try {
            Enumeration localEnumeration=NetworkInterface.getNetworkInterfaces();

            while (localEnumeration.hasMoreElements()) {
                NetworkInterface localNetworkInterface=(NetworkInterface) localEnumeration.nextElement();
                String interfaceName=localNetworkInterface.getDisplayName();

                if (interfaceName==null) {
                    continue;
                }

                if (interfaceName.equals("eth0")) {
                    // MACAddr = convertMac(localNetworkInterface
                    // .getHardwareAddress());
                    mac=convertToMac(localNetworkInterface.getHardwareAddress());
                    if (mac!=null&&mac.startsWith("0:")) {
                        mac="0"+mac;
                    }
                    break;
                }

                // byte[] address =
                // localNetworkInterface.getHardwareAddress();
                // Log.i(TAG, "mac=" + address.toString());
                // for (int i = 0; (address != null && i < address.length);
                // i++)
                // {
                // Log.i("Debug", String.format("  : %x", address[i]));
                // }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return mac;
    }

    private static String convertToMac(byte[] mac) {
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<mac.length; i++) {
            byte b=mac[i];
            int value=0;
            if (b>=0&&b<=16) {
                value=b;
                sb.append("0"+Integer.toHexString(value));
            } else if (b>16) {
                value=b;
                sb.append(Integer.toHexString(value));
            } else {
                value=256+b;
                sb.append(Integer.toHexString(value));
            }
            if (i!=mac.length-1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }






}
