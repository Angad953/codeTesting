import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background{
    //iinstance vars
    private int x, y, radius;
    private boolean level1;
    private int re, gr, bl;

    //color
    private Color planetC; 

    //images
    private BufferedImage bg1BufferedImage;
    private BufferedImage bg2BufferedImage;


    public Background(){
        //sets images
    try {
            bg1BufferedImage = ImageIO.read(new File("bg1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bg2BufferedImage = ImageIO.read(new File("bg2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //default construtor
    public Background(int x){ //constructor
        //instance vars
        this.x = x;
        level1 = true;
        //random # for y & radius
        y = (int)(Math.random()*250) + 0;
        radius = (int)(Math.random()*10)+5;

        //sets color to random val from 0 to 255
        re = red();
        gr = green();
        bl = blue();



    }
    //methods that calculate color with rand ints
    private int red(){
        int red = (int)(Math.random()*243);
        return red;
    }

    private int blue(){
        int blue = (int)(Math.random()*243);
        return blue;
    }

    private int green(){
        int green = (int)(Math.random()*243);
        return green;
    }
    //if level 1 - draws space bg, if level 2 - draws desert bg
    public void drawBg(Graphics g, boolean level1){
        this.level1 = level1;
        if (level1){
            g.drawImage(bg1BufferedImage, 0, 0, null); //draw space
        } else if (!level1){
            g.drawImage(bg2BufferedImage, 0, 0, null); //draws desert
        }

        
    }
    
    //draws an individual planet for an array
    public void drawBg1(Graphics g){

        planetC = new Color(this.re,this.gr,this.bl);
        
        g.setColor(planetC);
        
        g.fillOval(x,y,radius,radius);
    }

    //moves planets to the left
    public void moveLeft(){
        x -= 2;
        //restarting at the beginning
        if (x <= 0){
            x = 800;
        }
    }
}