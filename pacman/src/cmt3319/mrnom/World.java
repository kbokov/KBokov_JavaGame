package cmt3319.mrnom;

import java.util.Random;

public class World {
	
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;
    
    public Ghost2 ghost2;
    public Ghost1 ghost1;
    public Ghost ghost;//ghost
    public Pacman pacman;
    public Ball ball;
    public boolean gameOver = false;;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    
    
    public World() {
        ghost2 = new Ghost2();
    	ghost1 = new Ghost1();
    	ghost = new Ghost();//ghost
    	pacman = new Pacman();
    	placeBall();
        
    }

    private void placeBall() {
    	
    	for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        

        int ballX = random.nextInt(WORLD_WIDTH);
        int ballY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[ballX][ballY] == false)
                break;
            ballX += 1;
            if (ballX >= WORLD_WIDTH) {
                ballX = 0;
                ballY += 1;
                if (ballY >= WORLD_HEIGHT) {
                    ballY = 0;
                }
            }
        }
        ball = new Ball(ballX, ballY, random.nextInt(3));
     
    }
    
    
    
    
    
    /**
     * This method updates the state of the world. It also calls the methods to start
     * the movement of the pac man and enemies.
     * @param deltaTime
     */
    public void update(float deltaTime) {
        if (gameOver)
            return;

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
            pacman.advance();
            ghost.advance();
            ghost1.advance();
            ghost2.advance();
            
            if (pacman.checkBitten(this)) {
            	gameOver = true;
            	return;
            }

            PacmanParts head = pacman.parts.get(0);
            if (head.x == ball.x && head.y == ball.y) {
                score += SCORE_INCREMENT;
                
                
                placeBall();
           

                if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
                    tick -= TICK_DECREMENT;
                }
            }
        }
    }
}
