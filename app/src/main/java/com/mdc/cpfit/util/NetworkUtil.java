package com.mdc.cpfit.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mdc.cpfit.util.listener.iNetwork;

import java.io.IOException;

public class NetworkUtil extends BroadcastReceiver {

    private iNetwork Listener;
    private Activity A;

    public NetworkUtil(Activity activity) {
        this.A = activity;
        if (A == null) {
            NullPointerException AcNull = new NullPointerException("systemInfo.getMainActivity Cannot be Null Value");
            throw (AcNull);
        }
    }

    public void StartReceiveNetworkChange(iNetwork Listener) {

        this.A.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.Listener = Listener;
    }

    public void StopReceiveNetowkChange() {
        this.A.unregisterReceiver(this);
        this.Listener = null;
    }

    private int getConnectionType() {

        ConnectivityManager manager = (ConnectivityManager) this.A.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo NetInfo = manager.getActiveNetworkInfo();
            if (NetInfo != null) {
                return NetInfo.getType();
            }
        }
        return Integer.MIN_VALUE;
    }

    public boolean Is3GConnected() {
        if (!IsNotAirPlaneMode()) {
            if (getConnectionType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    public boolean IsNotAirPlaneMode() {
        ConnectivityManager manager = (ConnectivityManager) this.A.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo NetInfo = manager.getActiveNetworkInfo();
            if (NetInfo != null) {
                return NetInfo.isAvailable();
            }
        }
        return false;
    }

    private boolean IsInternetAvailable() {
        boolean IsAvai = false;
        try {
            CommendLine C = new CommendLine();
            String Result = C.CallCommand("ping -c 1 -W 1 8.8.8.8");
            if (Result != null) {
                int StatisticFound = Result.lastIndexOf("---");
                if (StatisticFound > 0) {
                    String TransmitResult = Result.substring(StatisticFound + 3);
                    String[] TransmitRecord = TransmitResult.split("\\n");
                    if (TransmitRecord != null && TransmitRecord.length > 1) {
                        String[] Precious = TransmitRecord[1].split(",");
                        if (Precious != null && Precious.length > 2) {
                            String[] ReceiveResult = Precious[1].trim().split(" ");
                            if (ReceiveResult != null && ReceiveResult.length > 1) {
                                int TotalReceive = Integer.parseInt(ReceiveResult[0]);
                                if (TotalReceive > 0) {
                                    IsAvai = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return IsAvai;
    }

    public boolean IsCanUseInternet() {
        boolean Result = false;
        if (IsInternetAvailable()) {
            Result = true;
        } else {

            ConnectivityManager manager = (ConnectivityManager) this.A.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo NetInfo = manager.getActiveNetworkInfo();
                if (NetInfo != null) {
                    Result = NetInfo.isConnected();
                }
            }
        }
        return Result;
    }

    public boolean IsWifiConnected() {
        if (!IsNotAirPlaneMode()) {
            if (getConnectionType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager ConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (ConnManager != null) {
            if (this.Listener != null) {
                if (IsCanUseInternet()) {
                    this.Listener.CanUseInternet();
                } else {
                    this.Listener.CannotUseInternet();
                }
                NetworkInfo NetInfo = ConnManager.getActiveNetworkInfo();
                if (NetInfo != null) {
                    this.Listener.NetworkChange(ConnManager.getActiveNetworkInfo().getType());
                }
            }
        }
    }


}
