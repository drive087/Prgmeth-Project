import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    protected double positionX;
    protected double positionY;    
    protected double velocityX;
    protected double velocityY;
    protected GraphicsContext gc;
    private double width;
    private double height;
    private boolean status;
    private boolean shoott;
    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        status=true;
    }
    
    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {	if(filename == "mine.png") {
        Image i = new Image(filename,50,50,false,false);
        setImage(i);
        
    }else {
        Image i = new Image(filename,10,10,false,false);
        setImage(i);
    	}
    }
    public Image getImage() {
    	return image;
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }
    public double getVelocityX() {
    	return velocityX;
    }
    public double getVelocityY() {
    	return velocityY;
    }
    public double getPositionX() {
    	return positionX;
    }
    public double getPositionY() {
    	return positionY;
    }
    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
      
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
        
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    public boolean getStatus() {
    	return status;
    }
    public void clear() {
    	image=null;
    }

}