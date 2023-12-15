/* import javax.swing.JPanel;
import java.awt.Graphics;

public class Animate extends JPanel{
	private Background[] planet;
 
    // Run continuously to simulate animation
	public void animate(){
		while(true){
			//wait for .01 second
			try {
			    Thread.sleep(10);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
 
			for (Background each:planet){
				each.moveLeft();
			}
		
			//repaint the graphics
			repaint();
		}

	}
}
*/