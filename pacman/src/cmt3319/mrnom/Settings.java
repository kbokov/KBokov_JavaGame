package cmt3319.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cmt3319.gameframework.FileIO;

import android.util.Log;

public class Settings {
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };

    /**
     * Saves the highest earned scores to the pacgame.txt file. 
     * This provides with ability to get access to the highest earned scores.
     * @param files
     */
    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    
            		files.readFile("pacgame.txt")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i++) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            
        	
        	Log.v("SETTINGS:LOAD", "IOEXCEPTION");
        	
        } catch (NumberFormatException e) {
            
        	
        	Log.v("SETTINGS:LOAD", "NUMBER FORMAT EXCEPTION");
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    
                    files.writeFile("pacgame")));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            for (int i = 0; i < 5; i++) {
                out.write(Integer.toString(highscores[i]));
                out.write("\n");
            }

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
    /**This method is in charge of adding the scores to the highest 
     * scores
     * 
     * @param score
     */
    public static void addScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j--)
                    highscores[j] = highscores[j - 1];
                highscores[i] = score;
                break;
            }
        }
    }
}
