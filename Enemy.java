import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy{
    //private vars
    private int x, y;
    private int width, height;
    private int orgX, orgY; //for resetting enemies

    private boolean visible; //is the enemy visible 
    private boolean fire; //projectile fired
    private boolean level1; //level 1/2
    
    //images
    private BufferedImage alienBufferedImage;
    private BufferedImage targetBufferedImage;


    public Enemy(int x, int y, int orgX, int orgY){
        //setting instance vars
        this.orgX = orgX;
        this.orgY = orgY;
        this.x =  x;
        this.y = y;
        this.width = 50;
        this.height = 25;
        //set visible to true
        visible = true;

        try { //images
            targetBufferedImage = ImageIO.read(new File("target.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            alienBufferedImage = ImageIO.read(new File("enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //draws an enemy
    public void draw(Graphics g, boolean level1){
        if (visible && level1){ //level 1 - alien
            g.drawImage(alienBufferedImage, x, y, null);
        }
        if (visible && !level1){ //level 2 - bison
            g.drawImage(targetBufferedImage, x, y, null);
        }
        
    }

    //return methods
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    //set visible to false
    public void disappear(){
        visible = false;
    }

    //sets visible to true
    public void appear(){
        visible = true;
    }

    //resets enemy at original x and y
    public void reset(){
        this.x = this.orgX; 
        this.y = this.orgY;
    }

    //return visible
    public boolean getVisible(){
        return visible;
    }
    

    //movement methods
    public void moveUp(){ //up
        if (level1){
            y  -=  8;
        } else if (!level1){
            y  -=  16;
        }
        if (y < 0){
            y = 0;
        }
    }
    public  void moveDown(){ //down
        if (level1){
            y  +=  8;
        } else if (!level1){
            y  +=  16;
        }
        if (y > 600-height){
            y = 600-height;
        }
    }
    public void moveLeft(){ //left
        if (level1){
            x  -=  8;
        } else if (!level1){
            x  -=  16;
        }
    }

}