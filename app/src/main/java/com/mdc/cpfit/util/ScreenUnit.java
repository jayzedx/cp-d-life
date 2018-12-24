package com.mdc.cpfit.util;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mdc.cpfit.listener.iFragmentActivity;
import com.mdc.cpfit.util.listener.iNetwork;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import com.mdc.cpfit.R;



/**
 * Created by patchara_suk on 10/30/2017.
 */

public class ScreenUnit extends Fragment {
    protected boolean overrideBack = true;
    private String FragmentName;
    private Activity activity;
    private ScreenUnit currentScreen;
    private ScreenUnit currentIntentScreen;
    protected ArrayList<Runnable> TaskUIWorker = new ArrayList<>();
    protected ArrayList<Runnable> TaskUIWorkerLogic = new ArrayList<>();
    private View currentFrangmentView;
    HandlerThread backgroundhandlerThread;
    Handler backgroundhandler;
    CompositeDisposable disposable = new CompositeDisposable();

    public void setFrangment(String FragmentName, View view) {
        this.FragmentName = FragmentName;
        this.currentFrangmentView = view;
        this.activity = getActivity();
        ImageView gifFile = view.findViewById(R.id.gifFile);
        if (gifFile != null)
            Glide.with(this).asGif().load(R.drawable.progress).into(gifFile);
    }

    public ActivityUnit getActivityMain() {
        return (ActivityUnit) activity;
    }


    @Override
    public void onDestroy() {
        //Logging.LogError("BaseScreen", "onDestroy");

        disconnectApi();
        Runtime.getRuntime().gc();
        super.onDestroy();
    }

    public void callApi(Disposable disposable) {
        this.disposable.add(disposable);
    }

    public void disconnectApi() {

        disposable.clear();
    }

    public void ReplaceScreen(ScreenUnit scr, int containView) {
        if (scr != null) {
            currentScreen = scr;
            RemoveAllScreen();
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(containView, scr);
            transaction.setCustomAnimations(
                    R.anim.translate_from_bottom, R.anim.translate_to_top,
                    R.anim.translate_from_top, R.anim.translate_to_bottom
            );
            transaction.commit();

        }
    }

    public void IntentFragment(ScreenUnit scr) {
        if (scr != null) {
            Activity A = getActivityMain();
            if (A != null) {
                if (A instanceof iFragmentActivity) {
                    if (TaskUIWorker != null) {
                        TaskUIWorker.clear();
                    }
                    iFragmentActivity FAC = (iFragmentActivity) A;
                    FAC.IntentFragment(scr);
                }
            }
        }
    }

    public void NetworkCheckStatus(iNetwork network) {
        ActivityUnit A = getActivityMain();
        if (A != null) {
            A.networkCheckStatus(network);
        }
    }

    public void ReplaceFragment(ScreenUnit scr) {
        if (scr != null) {
            Activity A = getActivityMain();
            if (A != null) {
                if (A instanceof iFragmentActivity) {
                    if (TaskUIWorker != null) {
                        TaskUIWorker.clear();
                    }
                    iFragmentActivity FAC = (iFragmentActivity) A;
                    FAC.ReplaceFragment(scr);
                }
            }
        }
    }

    public synchronized void IntentScreen(ScreenUnit Screen, int containView) {
        if (Screen != null) {
            if (currentIntentScreen == null || !currentIntentScreen.getClass().getSimpleName().equals(Screen.getClass().getSimpleName())) {
                HideCurrentFragment();
                String FragmentName = System.currentTimeMillis() + "";

                FragmentManager Manager = getChildFragmentManager();
                FragmentTransaction transaction = Manager.beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.translate_from_bottom, R.anim.translate_to_top,
                        R.anim.translate_from_top, R.anim.translate_to_bottom
                );
                transaction.add(containView, Screen, FragmentName);
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                transaction.addToBackStack(FragmentName);
                transaction.commit();
            }
        }
    }

    public void HideCurrentFragment() {
        FragmentManager Manager = getChildFragmentManager();
        int totalFragment = Manager.getBackStackEntryCount();
        if (totalFragment > 0) {
            FragmentManager.BackStackEntry B = Manager.getBackStackEntryAt(--totalFragment);
            Fragment F = Manager.findFragmentByTag(B.getName());
            if (F != null) {
                FragmentTransaction T = Manager.beginTransaction();
                T.hide(F);
                T.commit();
            }
        } else {
            FragmentTransaction T = Manager.beginTransaction();
            T.hide(currentScreen);
            T.commit();
        }
    }


    public void RemoveAllScreen() {
        FragmentManager Manager = getChildFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
        }
    }

    public void RemoveCurrentRemoveAllScreen() {
        FragmentManager Manager = getChildFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
            break;
        }
    }

    public void RemoveAllFragment() {
        FragmentManager Manager = getFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
        }
    }

    public void RemoveCurrentFragmentChild() {
        FragmentManager Manager = getFragmentManager();
        while (Manager.getBackStackEntryCount() > 0) {
            Manager.popBackStackImmediate();
            break;
        }
    }

    public void UpdateUI(Runnable UIWorker) {
        UpdateUI(UIWorker, 0);
    }

    public void UpdateUI(Runnable UIWorker, int Timeout) {
        if (UIWorker != null) {
            Handler mainHandler = new Handler(getActivityMain().getMainLooper());
            if (Timeout > 0) {
                mainHandler.postDelayed(UIWorker, Timeout);
            } else {
                mainHandler.post(UIWorker);
            }
        }
    }

    public void UpdateLogic(Runnable UIWorker, int Timeout) {
        if (UIWorker != null) {
            backgroundhandlerThread = new HandlerThread("BackgroundThread");
            backgroundhandlerThread.start();
            Looper looper = backgroundhandlerThread.getLooper();
            Handler handler = new Handler(looper);
            TaskUIWorkerLogic.add(UIWorker);
            if (Timeout > 0) {
                handler.postDelayed(UIWorker, Timeout);

            } else {
                handler.post(UIWorker);
            }
        }
    }

    public void UpdateLogicSc(Runnable LogicWorker) {
        if (LogicWorker != null) {
            ActivityUnit A = getActivityMain();
            if (A != null) {
                LogicThread T = A.getLogicThread();
                if (T != null) {
                    T.AddTask(LogicWorker);
                }
            }
        }
    }

    public void UpdateLogicSc(final Runnable LogicWorker, final int Timeout) {
        if (LogicWorker != null) {
            ActivityUnit A = getActivityMain();
            if (A != null) {
                LogicThread T = A.getLogicThread();
                T.AddTask(LogicWorker, Timeout);
            }
        }
    }

    public void UpdateLogic(Runnable UIWorker) {
        if (UIWorker != null) {
            UpdateLogic(UIWorker, 0);
        }
    }

    public void UpdateLogicDelay(Runnable UIWorker, int Timeout) {
        if (UIWorker != null) {
            UpdateLogic(UIWorker, Timeout);
        }
    }

    protected void goback(boolean backAllScreen) {
        if (!backAllScreen) {
            getActivityMain().onBackPressed();
        } else {
            Activity MainActivity = getActivityMain();
            if (MainActivity != null) {
                if (MainActivity instanceof iFragmentActivity) {
                    iFragmentActivity FAC = (iFragmentActivity) MainActivity;
                    FAC.RemoveAllFragment();
                }
            }
            getActivityMain().onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (backgroundhandlerThread != null)
            backgroundhandlerThread.quit();
    }

    public void UpdateUIImmediately(Runnable UIWorker) {
        if (UIWorker != null) {
            Activity A = getActivityMain();
            if (A != null) {
                A.runOnUiThread(UIWorker);
            } else {
                UpdateUI(UIWorker);
            }
        }
    }

    public View getCurrentFrangmentView() {
        return currentFrangmentView;
    }
}
