import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;


public class Main extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Collect the Money Bags!" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 512, 800 );
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);
        
        SpaceShip briefcase = new SpaceShip();
    
        
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        
        for (int i = 0; i < 15; i++)
        {
            Enemy enemy = new Enemy();
            enemy.setImage("mine.png");
           
            double px = 350 * Math.random() + 50;
            double py = 100 ;          
            enemy.setPosition(px,py);
            enemyList.add( enemy );
        }
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                
                // game logic
                
                briefcase.setVelocity(0,0);
                if (input.contains("LEFT"))
                    briefcase.addVelocity(-180,0);
                if (input.contains("RIGHT"))
                    briefcase.addVelocity(180,0);
                if (input.contains("UP"))
                    briefcase.addVelocity(0,-150);
                if (input.contains("DOWN"))
                    briefcase.addVelocity(0,150);
                    
                briefcase.update(elapsedTime);
                
                // collision detection
                
                Iterator<Enemy> moneybagIter = enemyList.iterator();
                while ( moneybagIter.hasNext() )
                {
                    Enemy moneybag = moneybagIter.next();
                    if ( briefcase.intersects(moneybag) )
                    {
                        moneybagIter.remove();
                        score.value++;
                        Enemy enemy = new Enemy();
                        enemy.setImage("mine.png");
                       
                       
                    }
                }
                
                // render
                
                gc.clearRect(0, 0, 512,800);
                briefcase.render( gc );
                
                for (Enemy moneybag : enemyList )
                    moneybag.render( gc );

                String pointsText = "Score : " + (100 * score.value);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();

        theStage.show();
    }
}