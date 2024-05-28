
import javax.swing.*;

public class LeagueInvaders {
	public static final int HEIGHT = 800;
	public static final int WIDTH = 500;
	JFrame frame;
	GamePanel pan;
	
	public LeagueInvaders() {
		frame = new JFrame();
		pan = new GamePanel();
		frame.addKeyListener(pan);
	}
	
	void setup() {
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(pan);
		//frame.pack();
	}
	
	public static void main(String[] args) {
		LeagueInvaders li = new LeagueInvaders();
		li.setup();
	}
}
