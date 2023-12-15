import java.lang.Math;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class GamePanel extends JPanel implements KeyListener {
    //class files
    private Background bg;
    private Ship ship;
    //arrays
    private Enemy[] enemies;
    private Background[] planet;
    //font stuff
	private Font font;
	private Color textColor;
    //instance vars
	private int score, lives;
    private boolean level1;
    private int orgX, orgY;
    private int eMove;

    //constructor
    public GamePanel() {
        //set to level 1
        level1 = true;

        //instantiate other classes
		bg = new Background();
        ship = new Ship(50, 300);

        //setting instance vars
        lives = 3;
        score = 0;

        //instantiating arrays
        planet = new Background[50];
        enemies = new Enemy[6];
        int sum, enemyX, enemyY; //array that sets grid of enemies
        for (int r = 0; r < 2; r++) { //2 columns
            for (int c = 0; c < 3; c++) { //3 rows
                sum = c + r * 3; //sum = index of enemy obj
                enemyY = (c + 1) * 150; //sets y for enemies
                enemyX = 600 + r * 50; //sets x for enemies
                orgX = enemyX; //sets orgX and orgY for resetting eenemy objects
                orgY = enemyY;
                enemies[sum] = new Enemy(enemyX, enemyY, orgX, orgY); //
            }
        } 

		//for loop that instantiates parts of the array planet 
        //x of planets is evenly spaced so that 50 planets can fit on the screen
		int plX = 0;
        for (int i = 0; i < planet.length; i++) {
            planet[i] = new Background(plX);
            plX += 16;
		}

        //setting font stuff
		font = new Font("Arial", Font.PLAIN, 25);
		textColor = new Color(255,255,255);

        //other stuff
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public Dimension getPreferredSize() { //sets dimensions of the screen
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw backgroud - space if level 1, desert if level 2
        bg.drawBg(g, level1);

        //draw planets in background with an array
        for (Background each : planet){
            each.drawBg1(g);
        }
        
        //draws the enemies - aliens if level 1, bison if level 2
        for (Enemy each : enemies){
            if (each.getVisible()){
                System.out.println(each.getVisible());
                each.draw(g, level1);
            }
        }

        //checks level
        if (level1){ //if level 1, makes it so only 3 enemies are visible
            for (int i = 0; i < 3; i++){
                enemies[i].disappear();
            }
        } else if (!level1){ //if level 2, makes all 6 enemies visible
            for (int i = 0; i < 6; i++){
                enemies[i].appear();
            }
        }

        //draws the ship, spaceship if level 1, gun if level 2
        ship.draw(g, level1);
        
		//draw the score and lives
		g.setFont(font);
		g.setColor(textColor);
		g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 20, 60);
    }

    public void resetAll(){ //resets all enemies
        for (Enemy each : enemies){
            each.reset();
            System.out.println("resetting all");
        }
    }

    public void resetVisible(){ //resets all visible
        for (Enemy each : enemies){
            if (each.getVisible()){
                each.reset();
            }
        }
    }

    public void animate() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            lives = ship.getLives();
            //moves planets to the left (animated background)
            for (Background each : planet) {
                each.moveLeft();
            }

            //moves the projectile (limit passed in)
            ship.moveProjectile(800);

            //chooses a random movement : up,down,left
            
            for (int i = 0; i < 6; i++){
                eMove = (int)(Math.random()*3) + 0;
                if (eMove == 0){
                    enemies[i].moveUp(); 
                } else if (eMove == 1){
                    enemies[i].moveDown(); 
                } else if (eMove == 2){
                    enemies[i].moveLeft(); 
                }
            }
            

            //gives the ship  class the level
            ship.getLevel(level1);

            if (score == 3){ //checks if level 2
                if (level1){ // sets level to 2, makes all  6 enemies visible
                    level1 = false;
                    for (Enemy each : enemies){
                        each.appear();
                    }
                    resetAll();
                }
            }
            // Check for collisions and update the score
            // Check for collisions and update the score
            for (Enemy each : enemies) {
                if (each.getVisible() && ship.checkProjectileCollision(each)) {
                    each.disappear(); // Make the enemy disappear when hit
                    score++;
                }

                //check if enemy is touching side
                if (each.getVisible() && ship.checkEdgeCollision(each)) {
                    //reset  x & y
                    resetAll();
                    //takes a life
                }

                if (each.getVisible() && ship.checkShipCollision(each)) {
                    //reset  x & y
                    resetAll();
                    //takes a life
                }
            }

            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38) { // up arrow was pressed
            ship.moveUp();
        }
        if (e.getKeyCode() == 40) { // down arrow was pressed
            ship.moveDown();
        }
        if (e.getKeyCode() == 32) { // space was pressed
            ship.fire();
        }
        if (e.getKeyCode() == 79){ //"o" was pressed (skips to level 2)
            score = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}