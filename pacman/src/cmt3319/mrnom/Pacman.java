package cmt3319.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Pacman {
	
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    
    public List<PacmanParts> parts = new ArrayList<PacmanParts>();
    public int direction;    
    
    public Pacman() {        
        direction = UP;
        parts.add(new PacmanParts(5, 6));
        parts.add(new PacmanParts(5, 7));
        parts.add(new PacmanParts(5, 8));
    }
    
    public void turnLeft() {
        direction += 1;
        if(direction > RIGHT)
            direction = UP;
    }
    
    public void turnRight() {
        direction -= 1;
        if(direction < UP)
            direction = RIGHT;
    }
    
    
    
    public void advance() {
        PacmanParts head = parts.get(0);               
        
        int len = parts.size() - 1;
        for(int i = len; i > 0; i--) {
            PacmanParts before = parts.get(i-1);
            PacmanParts part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(direction == UP)
            head.y -= 1;
        if(direction == LEFT)
            head.x -= 1;
        if(direction == DOWN)
            head.y += 1;
        if(direction == RIGHT)
            head.x += 1;
        
        if(head.x < 0)
            head.x = 9;
        if(head.x > 9)
            head.x = 0;
        if(head.y < 0)
            head.y = 12;
        if(head.y > 12)
            head.y = 0;
    }
    
    /**
     * Checks if the pacman was has collided with the ghost
     * @return
     */
    public boolean checkBitten(World world) {
        int len = parts.size();
        GhostParts ghost = world.ghost.parts.get(0);
        Ghost1Parts ghost1 = world.ghost1.parts1.get(0);
        Ghost2Parts ghost2 = world.ghost2.parts2.get(0);
        PacmanParts pacman = parts.get(0);
        for(int i = 1; i < len; i++) {
            if(pacman.x == ghost.x && pacman.y == ghost.y)
                return true;
            if(pacman.x == ghost1.x && pacman.y == ghost1.y)
                return true;
            if(pacman.x == ghost2.x && pacman.y == ghost2.y)
                return true;
        }        
        
        return false;
    }
}

