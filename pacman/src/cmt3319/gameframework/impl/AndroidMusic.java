package cmt3319.gameframework.impl;

import cmt3319.gameframework.Music;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.res.AssetFileDescriptor;

import java.io.IOException;

public class AndroidMusic implements Music, OnCompletionListener {
	
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	/*
	 * We can only call MediaPlayer start(), stop(), pause() when the
	 * MediaPlayer is 'prepared'.
	 */
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor) {
		
		mediaPlayer = new MediaPlayer();
		
		try {
			
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), 
					                    assetDescriptor.getStartOffset(),
					                    assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
			
		} catch (IOException e) {
			throw new RuntimeException("Could not load music");
		}
	}
	
	public void onCompletion(MediaPlayer player) {
		synchronized(this) {
			isPrepared = false;
		}
	}

	public void play() {
		
        if (mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared) {
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void stop() {
		mediaPlayer.stop();
		synchronized(this) {
			isPrepared = false;
		}
	}
	public void pause() {
		if (mediaPlayer.isPlaying()) {
		    mediaPlayer.pause(); // book seems to have omitted this ?
		}
	}
	
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}
	
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}
	
	public boolean isStopped() {
		return !isPrepared;
	}
	
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}
	
	public void dispose() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		mediaPlayer.release();
	}
}
