import java.awt.*;

public class RocketShip extends GameObject{

	public RocketShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
	}

	void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}
	void up() {
		y-=speed;
	}
	void down() {
		y+=speed;
	}
	void right() {
		x+=speed;
	}
	void left() { 
		x-=speed;
	}
}
