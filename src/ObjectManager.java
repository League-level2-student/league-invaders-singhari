
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ObjectManager implements ActionListener{
	RocketShip rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;
	
	public ObjectManager(RocketShip rocket) {
		this.rocket = rocket;
	}
	void addProjectiles(Projectile pro) {
		projectiles.add(pro);
	}
	void addAliens() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	void update() {
		for(Alien ali : aliens) {
			ali.update();
			if(ali.y > LeagueInvaders.HEIGHT) {
				ali.isActive = false;
			}
		}
		for(Projectile pro : projectiles) {
			pro.update();
			if(pro.y < 0) {
				pro.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	void draw(Graphics g) {
		rocket.draw(g);
		for(Alien ali : aliens) {
			ali.draw(g);
		}
		for(Projectile pro : projectiles) {
			pro.draw(g);
		}
	}
	void purgeObjects() {
		for(int i = 0; i < aliens.size(); i++) {
			if(!aliens.get(i).isActive) {
				aliens.remove(i);
			}
		}
		for(int i = 0; i < projectiles.size(); i++) {
			if(!projectiles.get(i).isActive) {
				projectiles.remove(i);
			}
		}
	}
	
	void checkCollision() {
		for(Alien ali : aliens) {
			if(rocket.collisionBox.intersects(ali.collisionBox)) {
				rocket.isActive = false;
				ali.isActive = false;
			}
			else {
				for(Projectile pro : projectiles) {
					if(pro.collisionBox.intersects(ali.collisionBox)) {
						pro.isActive = false;
						ali.isActive = false;
						score++;
					}
				}
			}
	
		}
	}
	
	int getScore(){
		return score;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAliens();
	}
}
