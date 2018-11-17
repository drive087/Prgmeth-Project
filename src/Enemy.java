import javafx.scene.canvas.GraphicsContext;

public class Enemy extends Sprite implements Shoot {
	public Enemy() {
		this.setImage("mine.png");
		this.setPosition(350 * Math.random() + 50, 100);
		this.setVelocity(0, -50);
	}

	@Override
	public void shoot() {
		Sprite b=new Sprite();
		b.setImage("fireball.png");
		b.setVelocity(0, -200);
		b.setPosition(this.getPositionX(), this.getPositionY());
		gc.drawImage(b.getImage(), b.getPositionX(), b.getPositionY());
	}
}
