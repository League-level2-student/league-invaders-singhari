import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font instructionFont;
    int fntx = 66;
    int fnty = 115;
    Timer frameDraw;
    Timer alienSpawn;
    RocketShip rocket = new RocketShip(250, 700, 50, 50);
    ObjectManager objMan = new ObjectManager(rocket);
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    
    
	public GamePanel() {
		titleFont= new Font("Arial", Font.PLAIN, 48);
		instructionFont = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, objMan);
	    alienSpawn.start();
	}
	
    void updateMenuState() {
    	
    }
    void updateGameState() {  
    	if(!rocket.isActive) {
    		currentState = END;
    	}
    	else {
    		objMan.update();
    	}
    }
    void updateEndState()  { 
    	
    }
    
    void drawMenuState(Graphics g) {
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("League Invaders", fntx, fnty);
    	g.setFont(instructionFont);
    	g.drawString("Press ENTER to play", fntx+75, fnty+90);
    	g.drawString("Press SPACE for instructions", fntx+45, fnty+150);
    	
    }
    void drawGameState(Graphics g) {
    	if (needImage) {
    	    loadImage ("space.png");
    	}
    	if (gotImage) {
    		g.drawImage(image, 0, 0, 500, 800, null);
    	}
    	//g.setColor(Color.BLACK);
    	//g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	objMan.draw(g);
    //	g.setFont(titleFont);
    //	g.setColor(Color.YELLOW);
//    	g.drawString("League Invaders", fntx, fnty);
//    	g.setFont(instructionFont);
//    	g.drawString("Press ENTER to play", fntx+75, fnty+90);
//    	g.drawString("Press SPACE for instructions", fntx+45, fnty+150);
    }
    void drawEndState(Graphics g)  {
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.BLACK);
    	g.drawString("You lost", fntx+20, fnty);
    	g.setFont(instructionFont);
    	g.drawString("You killed " + objMan.getScore() + " enemies.", fntx+75, fnty+90);
    	g.drawString("Press ENTER to restart", fntx+45, fnty+150);
    }
    
    void loadImage(String imageFile) {
        if (needImage) {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
    	    gotImage = true;
            } catch (Exception e) {
                
            }
            needImage = false;
        }
    }
    
	@Override
	public void paintComponent(Graphics g){
		//g.fillRect(10, 10, 100, 100);
		if(currentState == MENU){
		    drawMenuState(g);
		} else if(currentState == GAME){
		    drawGameState(g);
		} else if(currentState == END){
		    drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		//System.out.println("action");
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        rocket = new RocketShip(250, 700, 50, 50);
		        objMan = new ObjectManager(rocket);
		    } else {
		    	if(currentState == MENU) {
		    		currentState++;
		    		startGame();
		    	}
		    	else {
		    		currentState++;
		    		alienSpawn.stop();
		    	}
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		   // System.out.println("UP");
		    if(!(rocket.y < 0)) {
		    	rocket.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    //System.out.println("DOWN");
		    if(!(rocket.y > HEIGHT)) {
		    	rocket.down();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    //System.out.println("RIGHT");
		   // if((rocket.x < WIDTH)) {
		    	rocket.right();
		    
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    //System.out.println("LEFT");
		    if(!(rocket.x < 0)) {
		    	rocket.left();
		    }
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(currentState == GAME) {
				objMan.addProjectiles(rocket.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
