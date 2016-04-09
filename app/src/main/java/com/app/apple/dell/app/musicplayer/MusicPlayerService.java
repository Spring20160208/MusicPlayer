package com.app.apple.dell.app.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by Nov.Rain on 2016/4/7.
 */
public class MusicPlayerService extends Service
{
  private final IBinder mBinder=new LocalBinder();
  private MediaPlayer mMediaPlayer=null;

  public static final String PLAYER_PREPARE_END = "musicplayerservice.prepared";
  public static final String PLAY_COMPLETED = "musicplayerservice.playcompleted";

  MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener()
  {
      public void onCompletion(MediaPlayer mp)
      {
          broadcastEvent(PLAY_COMPLETED);
      }
  };

  MediaPlayer.OnPreparedListener mPrepareListener= new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mp) {
          broadcastEvent(PLAYER_PREPARE_END);
      }
  };

  private void broadcastEvent(String what)
  {
      Intent i = new Intent(what);
      sendBroadcast(i);
  }

  public void onCreate()
  {
      super.onCreate();
      mMediaPlayer= new MediaPlayer();
      mMediaPlayer.setOnPreparedListener(mPrepareListener);
      mMediaPlayer.setOnCompletionListener(mCompleteListener);
  }

  public class LocalBinder extends Binder
  {
      public MusicPlayerService getService()
      {
          return MusicPlayerService.this;
      }
  }
  public IBinder onBind(Intent intent)
  {
      return mBinder;
  }

  public void setDataSource(String path)
  {
      try
      {
          mMediaPlayer.reset();
          mMediaPlayer.setDataSource(path);
          mMediaPlayer.prepare();
      }
      catch (IOException e)
      {
          return;
      }
      catch (IllegalArgumentException e)
      {
          return;
      }
  }
   public void start()
   {
       mMediaPlayer.start();
   }
   public void stop() {
       mMediaPlayer.stop();
   }
    public void pause()
    {
        mMediaPlayer.pause();
    }
    public boolean isPlaying()
    {
        return mMediaPlayer.isPlaying();
    }
    public int getDuration()
    {
        return mMediaPlayer.getDuration();
    }
    public int getPosition()
    {
        return mMediaPlayer.getCurrentPosition();
    }
    public long seek(long whereto)
    {
        mMediaPlayer.seekTo((int)whereto);
        return whereto;
    }
}
