package cmt3319.mrnom;

import java.util.List;

import android.graphics.Color;

import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Input.TouchEvent;
import cmt3319.gameframework.Pixmap;
import cmt3319.gameframework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    
    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);        
    }
    
    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
        	
            TouchEvent event = touchEvents.get(i);
            
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y < 64) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }
            }
            
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                    world.pacman.turnLeft();
                    
                }
                if(event.x > 256 && event.y > 416) {
                    world.pacman.turnRight();
                }
            }
        }
        
        world.update(deltaTime);
        
        if(world.gameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        if(oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if(Settings.soundEnabled)
                Assets.eat.play(1);
        }
    }
    
    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));                        
                        return;
                    }
                }
            }
        }
    }
    
    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }
    

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        
        drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);                
    }
    
    private void drawWorld(World world) {
    	
        
    	Graphics g = game.getGraphics();
        Pacman pacman = world.pacman;
        Ghost ghost = world.ghost;//ghost
        Ghost1 ghost1 = world.ghost1;
        Ghost2 ghost2 = world.ghost2;
        
        Ghost2Parts ghost2head = ghost2.parts2.get(0);
        Ghost1Parts ghost1head = ghost1.parts1.get(0);
        GhostParts ghosthead = ghost.parts.get(0);
        
        PacmanParts head = pacman.parts.get(0);
        
        Ball ball = world.ball;
        
        
        
        Pixmap ballPixmap = null;
        if(ball.type == Ball.TYPE_1)
            ballPixmap = Assets.stain1;
        if(ball.type == Ball.TYPE_2)
            ballPixmap = Assets.stain2;
        if(ball.type == Ball.TYPE_3)
            ballPixmap = Assets.stain3;
        int x = ball.x * 32;
        int y = ball.y * 32;      
        g.drawPixmap(ballPixmap, x, y);
        
        
        
        
        
        
        
        Pixmap headPixmap = null;
        if(pacman.direction == Pacman.UP) 
            headPixmap = Assets.headUp;
        if(pacman.direction == Pacman.LEFT) 
            headPixmap = Assets.headLeft;
        if(pacman.direction == Pacman.DOWN) 
            headPixmap = Assets.headDown;
        if(pacman.direction == Pacman.RIGHT) 
            headPixmap = Assets.headRight;        
        x = head.x * 32 + 16;
        y = head.y * 32 + 16;
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
        
        
        
        Pixmap headGhostPixmap = null;
        if(ghost.way == Ghost.UP) 
            headGhostPixmap = Assets.ghost;
        if(ghost.way == Ghost.LEFT) 
            headGhostPixmap = Assets.ghost;
        if(ghost.way == Ghost.DOWN) 
            headGhostPixmap = Assets.ghost;
        if(ghost.way == Ghost.RIGHT) 
            headGhostPixmap = Assets.ghost;


         x = ghosthead.x * 32 + 16;
         y = ghosthead.y * 32 + 16;
        g.drawPixmap(headGhostPixmap, x - headGhostPixmap.getWidth() / 2, y - headGhostPixmap.getHeight() / 2);
        
        
  
        Pixmap head1GhostPixmap = null;
        if(ghost1.way == Ghost.UP) 
            head1GhostPixmap = Assets.ghost;
        if(ghost1.way == Ghost.LEFT) 
            head1GhostPixmap = Assets.ghost;
        if(ghost1.way == Ghost.DOWN) 
            head1GhostPixmap = Assets.ghost;
        if(ghost1.way == Ghost.RIGHT) 
            head1GhostPixmap = Assets.ghost;


         x = ghost1head.x * 32 + 16;
         y = ghost1head.y * 32 + 16;
        g.drawPixmap(head1GhostPixmap, x - head1GhostPixmap.getWidth() / 2, y - head1GhostPixmap.getHeight() / 2);
    
    
        
        Pixmap head2GhostPixmap = null;
        if(ghost2.way == Ghost.UP) 
            head2GhostPixmap = Assets.ghost;
        if(ghost2.way == Ghost.LEFT) 
            head2GhostPixmap = Assets.ghost;
        if(ghost2.way == Ghost.DOWN) 
            head2GhostPixmap = Assets.ghost;
        if(ghost2.way == Ghost.RIGHT) 
            head1GhostPixmap = Assets.ghost;


         x = ghost2head.x * 32 + 16;
         y = ghost2head.y * 32 + 16;
        g.drawPixmap(head2GhostPixmap, x - head2GhostPixmap.getWidth() / 2, y - head2GhostPixmap.getHeight() / 2);
    
    
    
    
    
    
    
    
    }
    
    
    	
    
    
    
    

    
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }
    
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.pause, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
    
    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;
        
        if(world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    public void resume() {
        
    }

    public void dispose() {
        
    }
}
