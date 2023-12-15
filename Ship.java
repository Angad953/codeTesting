import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ship{
    //instancee vars
    private int x, y;
    private int width, height;
    private boolean level1;
    private boolean fire;
    private int lives = 3;

    //instantiate objects
    private Projectile pObj;
	private Enemy eObj;

    //images
    private BufferedImage shipBufferedImage;
    private BufferedImage gunBufferedImage;

    public Ship(int x, int y) {
        //set instance vars
        this.x = x;
        this.y = y;
        fire = false;

        //instantiate objs
        pObj = new Projectile(x, y);

        try { //images
            shipBufferedImage = ImageIO.read(new File("Ship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            gunBufferedImage = ImageIO.read(new File("gun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, boolean level1) {
        this.level1 = level1;
        // check to see if projectile is fired
        if (fire) {
            pObj.draw(g, level1);
        }

        if (level1){ //level 1 - space ship
            g.drawImage(shipBufferedImage, x, y, null);
            width = 50;
            height = 50;
        } else if (!level1){ //level 2 - gun
            g.drawImage(gunBufferedImage, x, y, null);
            width = 50;
            height = 30;
        }
    }

    //movement
    public void moveUp() { //up
        y -= 10;
        if (!fire) {
            pObj.moveUp();
        }
    }
    public void moveDown() { //down
        y += 10;
        if (!fire) {
            pObj.moveDown();
        }
    }

    // set fire to true
    public void fire() {
        fire = true;
    }

    public void moveProjectile(int limit) {
        if (fire) { //move projectile
            pObj.move();
        }
        // if the projectile went to the end of the screen
        if (pObj.getX() > limit) {
            pObj.reset(x, y);
            fire = false;
        }
    }

    public void getLevel(boolean level1){
        this.level1 = level1;
    }

    public int getLives(){
        return lives;
    }

    // check to see if projectile hit an enemy
    public boolean checkProjectileCollision(Enemy e) {
        if (fire){ //if projectile is fired
        if (e.getVisible()) {
            System.out.println("hit testing");
            // declaring variables
            int pX = pObj.getX();
            int pY = pObj.getY(); 
            int pWidth = pObj.getWidth();
            int pHeight = pObj.getHeight();

            int eX = e.getX();  
            int eY = e.getY();  
            int eWidth = e.getWidth();
            int eHeight = e.getHeight();

            if (pX + pWidth >= eX && pX <= eX + eWidth && pY + pHeight >= eY && pY <= eY + eHeight) {
				e.disappear();
				pObj.reset(x,y);
				fire = false;
                return true;
            }
        }
    }
        return false;
    }

    public boolean checkEdgeCollision(Enemy e) { //check to see if enemey reaches left side of screen
        if (e.getVisible()) {
            // declaring variables
            int edge = 0;
            int eX = e.getX();  

            if (edge >= eX) {
                e.reset();
                lives --;

            }
        }
        return false;
    }
    
    public boolean checkShipCollision(Enemy e) { //checks if enemy hit ship
        if (fire){ //if projectile is fired
        if (e.getVisible()) {
            // declaring variables
            int sX = x;
            int sY = y; 
            int sWidth = width;
            int sHeight = height;

            int eX = e.getX();  
            int eY = e.getY();  
            int eWidth = e.getWidth();
            int eHeight = e.getHeight();

            if (sX + sWidth >= eX && sY + sHeight >= eY && sY <= eY + eHeight) {
                lives --;
				fire = false;
                return true;
            }
        }
    }
        return false;
    }
}