package cmt3319.mrnom;

import java.util.List;

import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Screen;
import cmt3319.gameframework.Input.TouchEvent;

public class HelpScreen3 extends Screen {
    
    public HelpScreen3(Game game) {
        super(game);
    }
    

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 256 && event.y > 416 ) {
                    game.setScreen(new MainMenuScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.help3, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 128, 64, 64);
    }

    public void pause() {
        // TODO Auto-generated method stub

    }

    public void resume() {
        // TODO Auto-generated method stub

    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

}
