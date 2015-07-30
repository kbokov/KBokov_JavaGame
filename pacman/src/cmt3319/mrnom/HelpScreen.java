package cmt3319.mrnom;

import java.util.List;

import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Input.TouchEvent;
import cmt3319.gameframework.Screen;

public class HelpScreen extends Screen {      
    public HelpScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        //game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 256 && event.y > 416 ) {
                    game.setScreen(new HelpScreen2(game));
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
        g.drawPixmap(Assets.help1, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
