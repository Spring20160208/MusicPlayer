package com.app.apple.dell.app.musicplayer;

import android.app.Application;

public class MusicPlayerApp extends Application
{
    private MusicInfoController mMusicInfoController=null;
    public void onCreate()
    {
        super.onCreate();
        mMusicInfoController=MusicInfoController.getmInstance(this);
    }
    public MusicInfoController getMusicInfoController()
    {
        return mMusicInfoController;
    }
}
