/* //commented out for testing
public class Collision {
    // Check to see if the projectile has hit an enemy
	public boolean checkProjectileCollision(Enemy e){
		
		if( e.getVisible() ){ //checkCollision only when the enemy is visible
			
			
			if( pX+pWidth >= tX && pX <= tX + tWidth  &&  
				pY+pHeight >= tY && pY <= tY + tHeight ) {
				System.out.println("Collision");	// for testing 
				
			}
		}
		
	}
}

*/