package cmt3319.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Ghost2 {

	public static final int UP=0;
	public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
	
	public int way;
	public List<Ghost2Parts> parts2 = new ArrayList<Ghost2Parts>();
	

	public Ghost2() {        
	    way = UP;
	    parts2.add(new Ghost2Parts(0,12 ));
    }
	
	/** This method is in charge of the movement of the Ghost2
	 * 
	 */
	public void advance() {
        Ghost2Parts head2Ghost = parts2.get(0);               
        
        int len = parts2.size() - 1;
        for(int i = len; i > 0; i--) {
            Ghost2Parts before = parts2.get(i-1);
            Ghost2Parts part = parts2.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(way == UP)
            head2Ghost.y -= 1;
        if(way == LEFT)
            head2Ghost.x -= 1;
        if(way == DOWN)
            head2Ghost.y += 1;
        if(way == RIGHT)
            head2Ghost.x += 1;
        
        if(head2Ghost.x < 0)
            head2Ghost.x = 9;
        if(head2Ghost.x > 9)
            head2Ghost.x = 0;
        if(head2Ghost.y < 0)
            head2Ghost.y = 12;
        if(head2Ghost.y > 12)
            head2Ghost.y = 0;
    }
	    
}


