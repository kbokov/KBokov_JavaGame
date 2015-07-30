package cmt3319.mrnom;

import java.util.List;

import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Screen;
import cmt3319.gameframework.Input.TouchEvent;

public class HelpScreen2 extends Screen {

    public HelpScreen2(Game game) {
        super(game);
        
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        
        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > g.getWidth() - 64 && event.y > g.getHeight() - 64 ) {
                    game.setScreen(new HelpScreen3(game));
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
        g.drawPixmap(Assets.help2, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
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
