package com.example.unitywrapper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.unity3d.player.UnityPlayer;

public class UnityFragment extends Fragment {
    protected UnityPlayer mUnityPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mUnityPlayer = new UnityPlayer(getActivity());
        mUnityPlayer.requestFocus();
        mUnityPlayer.windowFocusChanged(true);
        return mUnityPlayer.getView();
    }

    @Override
    public void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }
}