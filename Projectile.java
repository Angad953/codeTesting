import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Code that implements a projectile
public class Projectile {

    // Instance variables
    private int x, y;
    private int width, height;
    
    private Color red;
    private BufferedImage bulletBufferedImage;
    private BufferedImage laserBufferedImage;
    private boolean level1;

    // Constructor
    public Projectile(int x, int y) {
        //initialize instance variables
        this.x = x;
        this.y = y;

        this.width = 50;
        this.height = 25;

        this.red = new Color(255,0,0);

        try { //image
            bulletBufferedImage = ImageIO.read(new File("proj.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            laserBufferedImage = ImageIO.read(new File("laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //draw the projectile
    public void draw(Graphics g, boolean level1){
        if (level1){
            g.drawImage(laserBufferedImage, x, y, null);
        } else if (!level1){
            g.drawImage(bulletBufferedImage, x, y, null);
        }
        
    }

    //move projectile up
    public void moveUp(){
        y-=5;
    }
    //move projectile down
    public void moveDown(){
        y+=5;
    }
    //move the projectile to the right
    public void move(){
        x+=65;    
    }

    //reset the projectile
    public void reset(int x, int y){
        this.x = x;
        this.y = y;
    }

    //return x coords
    public int getX(){
        return x;
    }
    
    //return y coords
    public int getY(){
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
