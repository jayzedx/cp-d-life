package com.mdc.cpfit.listener;


import com.mdc.cpfit.util.ScreenUnit;

public interface iFragmentActivity {

    public void ReplaceFragment(ScreenUnit Screen);

    public void RemoveCurrentFragment();

    public void ReCreateViewFragment(ScreenUnit scr);

    public void RemoveAllFragment();

    public void HideCurrentFragment();

    public void ShowCurrentFragment();

    public void IntentFragment(ScreenUnit Screen);


}
