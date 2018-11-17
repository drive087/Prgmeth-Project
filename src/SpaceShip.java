import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpaceShip extends Sprite implements Shoot{
	
	Sprite bullet=new Sprite();
	public SpaceShip() {
        Image i = new Image("spaceship.png",80,110,false,false);
		this.setImage(i);
		this.setPosition(200, 600);
		this.setVelocity(180, 150);
		
	}
	
	@Override
	public void shoot() {
        Image i = new Image("fireball.png",50,50,false,false);
		bullet.setImage(i);
		bullet.setPosition(this.getPositionX(), this.getPositionY());
		bullet.setVelocity(0, 0);
        gc.drawImage( bullet.getImage(), bullet.getPositionX(), bullet.getPositionY() );		
	}

	@Override
    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
        bullet.positionY+=bullet.getVelocityY()*time;
      
    }
	@Override
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
        gc.drawImage( bullet.getImage(), bullet.getPositionX(), bullet.getPositionY() );
    }


}
