package cmt3319.mrnom;
import java.util.ArrayList;
import java.util.List;

public class Ghost1 {

	
	public static final int UP=0;
	public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
	
	public int way;
	public List<Ghost1Parts> parts1 = new ArrayList<Ghost1Parts>();
	

	public Ghost1() {        
	    way = UP;
	    parts1.add(new Ghost1Parts(6,1 ));
    }
	
	/** This method is in charge of the movement of the Ghost1
	 * 
	 */
	public void advance() {
        Ghost1Parts head1Ghost = parts1.get(0);               
        
        int len = parts1.size() - 1;
        for(int i = len; i > 0; i--) {
            Ghost1Parts before = parts1.get(i-1);
            Ghost1Parts part = parts1.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(way == UP)
            head1Ghost.y -= 1;
        if(way == LEFT)
            head1Ghost.x -= 1;
        if(way == DOWN)
            head1Ghost.y += 1;
        if(way == RIGHT)
            head1Ghost.x += 1;
        
        if(head1Ghost.x < 0)
            head1Ghost.x = 9;
        if(head1Ghost.x > 9)
            head1Ghost.x = 0;
        if(head1Ghost.y < 0)
            head1Ghost.y = 12;
        if(head1Ghost.y > 12)
            head1Ghost.y = 0;
    }
	    
}

