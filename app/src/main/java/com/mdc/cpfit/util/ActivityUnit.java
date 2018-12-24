package com.mdc.cpfit.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mdc.cpfit.R;
import com.mdc.cpfit.listener.iFragmentActivity;
import com.mdc.cpfit.util.listener.cpuInfo;
import com.mdc.cpfit.util.listener.iNetwork;
import com.mdc.cpfit.util.listener.memInfo;


import java.util.HashMap;
import java.util.Set;

import io.reactivex.disposables.Disposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityUnit extends AppCompatActivity implements iFragmentActivity {
    private int containView = 0;
    public static boolean isCanUseInternet = false;
    protected boolean overrideBack = true;
    private static Activity activity;
    private ScreenUnit currentScreen;
    private ScreenUnit currentIntentScreen;
    boolean isConnectbeforeLostConnect = false;
    private static final String TAG = "ReactiveNetwork";
    private Disposable internetDisposable;
    private NetworkUtil networkUtil;

    public Activity getActivityMain() {
        return activity;
    }

    public static Activity getActivity() {
        return activity;
    }

    public void setActivityMain(Activity activity) {
        this.activity = activity;
        setLogicThread(new LogicThread(this));


    }

    public int getContainView() {
        return containView;
    }

    public void setContainView(int _containView) {
        containView = _containView;

    }


    public void startActivityUnit(Class<?> classAc, HashMap<String, Bundle> arg) {
        Intent i = new Intent(this, classAc);
        if (arg != null) {

            Set<String> keys = arg.keySet();

            for (String name : keys) {

                i.putExtra(name, arg.get(name));
            }
        }
        if (activity != null) {

            activity.startActivity(i);
            overridePendingTransition(R.anim.translate_from_right, R.anim.translate_to_left);
        }
    }

    public class startActivityUnitThead extends AsyncTask<Object, Void, Intent> {

        @Override
        protected Intent doInBackground(Object... objec) {

            Intent i = new Intent(activity.getApplication(), (Class<?>) objec[0]);

            HashMap<String, Bundle> arg = (HashMap<String, Bundle>) objec[1];
            if (arg != null) {

                Set<String> keys = arg.keySet();

                for (String name : keys) {

                    i.putExtra(name, arg.get(name));
                }
            }
            return i;
        }

        @Override
        protected void onPostExecute(Intent intent) {
            super.onPostExecute(intent);
            activity.startActivity(intent);
            overridePendingTransition(R.anim.translate_from_right, R.anim.translate_to_left);
        }

    }

    private iNetwork iNetworkListener;

    public void networkCheckStatus(iNetwork iNetwork) {
        iNetworkListener = iNetwork;
    }

    @Override
    protected void onPause() {
        super.onPause();
        setLogicThread(null);
        setActivityMain(null);
        if (networkUtil != null)
            networkUtil.StopReceiveNetowkChange();

    }

    @Override
    protected void onResume() {
        setActivityMain(this);
        networkUtil = new NetworkUtil(this);
        networkUtil.StartReceiveNetworkChange(new iNetwork() {
            @Override
            public void NetworkChange(int NetworkType) {
                if (iNetworkListener != null)
                    iNetworkListener.NetworkChange(NetworkType);
            }

            @Override
            public void CannotUseInternet() {
                if (iNetworkListener != null)
                    iNetworkListener.CannotUseInternet();
            }

            @Override
            public void CanUseInternet() {
                if (iNetworkListener != null)
                    iNetworkListener.CanUseInternet();

            }
        });
//
//        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(getApplicationContext())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Connectivity>() {
//                               @Override
//                               public void accept(Connectivity connectivity) throws Exception {
//                                   Log.d(TAG, connectivity.toString());
//                                   final NetworkInfo.State state = connectivity.getState();
//                                   final String name = connectivity.getTypeName();
//                                   Snackbar.make(findViewById(getContainView()), state + " 11 " + name,Snackbar.LENGTH_SHORT).show();
//                               }
//                           }
//                );

//        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean isConnected) throws Exception {
//                        if (!isConnected) {
//                            Snackbar.make(findViewById(getContainView()), ("Internet not Connect"), Snackbar.LENGTH_LONG)
//                                    .setActionTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Bean_Red)).show();
//                            isConnectbeforeLostConnect = true;
//                        } else {
//                            if (isConnectbeforeLostConnect) {
//                                Snackbar.make(findViewById(getContainView()), ("Internet  Connected"), Snackbar.LENGTH_LONG)
//                                        .setActionTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Bean_Red)).show();
//
//                                isConnectbeforeLostConnect = false;
//                            }
//                        }
//
//
//                    }
//                });
        /**
         * Do somthing
         */
        super.onResume();
    }


    /**
     * Obtain Total CpuCore From Android
     *
     * @return Number of Cpu Core
     */
    public void setLogicThread(LogicThread T) {
        Logic = T;
    }

    private LogicThread Logic;

    public static int getTotalCpuCore() {
        cpuInfo C = new cpuInfo();
        int CpuCore = C.getTotalCpuCore();
        return CpuCore;
    }

    public static double getFreeMemory(Context c) {
        memInfo m = new memInfo();
        return m.getFreeMemory(c);
    }

    public static double getTotalMemory() {
        memInfo m = new memInfo();
        return m.getTotalMemory();
    }

    public LogicThread getLogicThread() {
        return Logic;
    }

    public void runOnLogicThread(Runnable Task) {
        if (Logic != null && Task != null) {
            Logic.AddTask(Task);
        }
    }


    public void ReplaceFragment(ScreenUnit scr) {
        if (scr != null) {
            currentScreen = scr;
            RemoveAllFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(
                    R.anim.translate_from_right, R.anim.translate_to_left,
                    R.anim.translate_from_left, R.anim.translate_to_right)
                    .replace(containView, scr)
                    .commit();

        }
    }

    public void ReplaceFragmentNoAnimation(ScreenUnit scr) {
        if (scr != null) {
            currentScreen = scr;
            RemoveAllFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(containView, scr)
                    .commit();

        }
    }

    public synchronized void IntentFragment(ScreenUnit Screen) {
        if (Screen != null) {
            if (currentIntentScreen == null || !currentIntentScreen.getClass().getSimpleName().equals(Screen.getClass().getSimpleName())) {
                HideCurrentFragment();
                String FragmentName = System.currentTimeMillis() + "";

                FragmentManager Manager = getSupportFragmentManager();
                FragmentTransaction transaction = Manager.beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.translate_from_right, R.anim.translate_to_left,
                        R.anim.translate_from_left, R.anim.translate_to_right
                );
                transaction.add(containView, Screen, FragmentName);
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                transaction.addToBackStack(FragmentName);
                transaction.commit();
            }
        }
    }

    public void HideCurrentFragment() {
        FragmentManager Manager = getSupportFragmentManager();

        int totalFragment = Manager.getBackStackEntryCount();
        if (totalFragment > 0) {
            FragmentManager.BackStackEntry B = Manager.getBackStackEntryAt(--totalFragment);
            Fragment F = Manager.findFragmentByTag(B.getName());

            if (F != null) {
                FragmentTransaction T = Manager.beginTransaction();
                T.setCustomAnimations(
                        R.anim.translate_from_right, R.anim.translate_to_left,
                        R.anim.translate_from_left, R.anim.translate_to_right
                );
                T.hide(F);
                T.commit();
            }
        } else {
            FragmentTransaction T = Manager.beginTransaction();
            T.setCustomAnimations(
                    R.anim.translate_from_right, R.anim.translate_to_left,
                    R.anim.translate_from_left, R.anim.translate_to_right
            );
            T.hide(currentScreen);
            T.commit();
        }


    }

    public void ShowCurrentFragment() {
        FragmentManager Manager = getSupportFragmentManager();
        int totalFragment = Manager.getBackStackEntryCount();
        if (totalFragment > 0) {
            FragmentManager.BackStackEntry B = Manager.getBackStackEntryAt(--totalFragment);
            Fragment F = Manager.findFragmentByTag(B.getName());
            if (F != null && F != currentScreen) {
                if (F.isHidden()) {
                    FragmentTransaction T = Manager.beginTransaction();
                    T.setCustomAnimations(
                            R.anim.translate_from_left, R.anim.translate_to_right,
                            R.anim.translate_from_right, R.anim.translate_to_left

                    );
                    T.show(F);
                    T.commit();
                    currentIntentScreen = (ScreenUnit) F;
                }
            }
        } else {
            currentIntentScreen = null;
            if (currentScreen.isHidden()) {
                FragmentTransaction T = Manager.beginTransaction();
                T.setCustomAnimations(
                        R.anim.translate_from_left, R.anim.translate_to_right,
                        R.anim.translate_from_right, R.anim.translate_to_left

                );
                T.show(currentScreen);
                T.commit();
            }

        }
    }


    public void RemoveAllFragment() {
        FragmentManager Manager = getSupportFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
        }
//        ScreenTable.clear();
    }

    public void RemoveCurrentFragment() {
        FragmentManager Manager = getSupportFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
            break;
        }
    }

    /**
     * Re-Build View of Screen
     *
     * @param scr Specific Screen to Re-Build
     */
    public void ReCreateViewFragment(ScreenUnit scr) {
        FragmentTransaction Transaction = getSupportFragmentManager()
                .beginTransaction();
        Transaction.detach(scr);
        Transaction.attach(scr);
        Transaction.commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
        safelyDispose(internetDisposable);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            // Exit Program
            if (onExitProgram()) {
                super.onBackPressed();
                overridePendingTransition(R.anim.translate_from_left, R.anim.translate_to_right);


            }
        } else {

            super.onBackPressed();
            ShowCurrentFragment();
            overridePendingTransition(R.anim.translate_from_left, R.anim.translate_to_right);
        }
    }

    public void ExitProgram() {
        RemoveAllFragment();
        super.onBackPressed();
        finish();
    }

    public boolean onExitProgram() {
        return true;
    }

    private void safelyDispose(Disposable... disposables) {
        for (Disposable subscription : disposables) {
            if (subscription != null && !subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
