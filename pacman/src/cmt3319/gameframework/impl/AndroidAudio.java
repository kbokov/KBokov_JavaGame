package cmt3319.gameframework.impl;

import cmt3319.gameframework.Audio;
import cmt3319.gameframework.Music;
import cmt3319.gameframework.Sound;

import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;

import android.app.Activity;

import java.io.IOException;

public class AndroidAudio implements Audio {
	
	AssetManager assets;
	SoundPool soundPool;
	
	/*
	 *  Pass the Activity object to allow us to set the  control to the media stream, and
	 *  so that we can get an AssetManager
	 */
	public AndroidAudio(Activity activity) { 
		
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC, 0); //play 20 sound effects in parallel
	}

	public Music newMusic(String fileName)  {
		
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetDescriptor);
		} catch (IOException e) {
			
			/*
			 * See page 188 for note on rationale for throwing a RuntimeException
			 */
			throw new RuntimeException("Could not load music: " + fileName);
		}
		
	}
	
	public Sound newSound(String fileName)  {
		
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch (IOException e) {
			
			/*
			 * See page 188 for note on rationale for throwing a RuntimeException
			 */
			throw new RuntimeException("Could not load sound: " + fileName);
		}
	}
}
