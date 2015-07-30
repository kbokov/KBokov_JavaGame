package cmt3319.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Ghost {

	
	public static final int UP=0;
	public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
	
	public int way;
	public List<GhostParts> parts = new ArrayList<GhostParts>();
	

	public Ghost() {        
	    way = UP;
	    parts.add(new GhostParts(8, 2));
    }
	
	/** This method is in charge of the movement of the Ghost
	 * 
	 */
	public void advance() {
        GhostParts headGhost = parts.get(0);               
        
        int len = parts.size() - 1;
        for(int i = len; i > 0; i--) {
            GhostParts before = parts.get(i-1);
            GhostParts part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(way == UP)
            headGhost.y -= 1;
        if(way == LEFT)
            headGhost.x -= 1;
        if(way == DOWN)
            headGhost.y += 1;
        if(way == RIGHT)
            headGhost.x += 1;
        
        if(headGhost.x < 0)
            headGhost.x = 9;
        if(headGhost.x > 9)
            headGhost.x = 0;
        if(headGhost.y < 0)
            headGhost.y = 12;
        if(headGhost.y > 12)
            headGhost.y = 0;
    }
	    
}







