package cmt3319.mrnom;
/**
 * Sets the static fields for the red ball which pacman eats.
 * @author kirillbokov
 *
 */
public class Ball {
	
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    public int x, y;
    public int type;

    public Ball(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
