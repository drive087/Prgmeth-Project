
public class Enemy extends Sprite implements Bullet{
	public Enemy() {
		this.setImage("mine.png");
		this.setPosition(350 * Math.random() + 50, 100);
		this.setVelocity(0, -50);
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
}
