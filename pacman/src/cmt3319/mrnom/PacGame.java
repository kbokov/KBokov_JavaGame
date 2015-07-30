package cmt3319.mrnom;

//import android.app.Activity;
//import android.os.Bundle;

import cmt3319.gameframework.Screen;
import cmt3319.gameframework.impl.AndroidGame;

public class PacGame extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}