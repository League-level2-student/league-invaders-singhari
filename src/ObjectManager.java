
import java.awt.*;
import java.util.*;

public class ObjectManager {
	RocketShip rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	public ObjectManager(RocketShip rocket) {
		this.rocket = rocket;
	}
	void addProjectiles(Projectile pro) {
		projectiles.add(pro);
	}
	void addAliens(Alien alien) {
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
}
