import java.awt.Graphics2D;

//import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.map.*;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.utility.*;

@ScriptManifest(author = "Andrew Miculka", info = "Harvest chicken bones for sale", name = "Bone Reaper", version = 0, logo = "")
public class Main extends Script {

	Entity closestBank = null;
	Entity closestChicken = null;
	static Area chickenPen = new Area(new Position(3171, 3289, 0), new Position(3183, 3301, 0));
	
	@Override
	public void onStart() {
		log("Bone reaper is out on the prowl . . . ");
	}

	@Override
	public int onLoop() throws InterruptedException {
		
		if(getInventory().getEmptySlots() == 0) {
			log("<<< Inventory Full >>>");
			//Turn on run
			//Go to bank
			if(getWalking().webWalk(Banks.LUMBRIDGE_UPPER, Banks.DRAYNOR)) {
				log("<<< At The Bank >>>");
				closestBank = getBank().closest();
				if(closestBank != null && !getBank().isOpen()) {
					getBank().open();
				}
				else if(getBank().isOpen()) {
					//deposit bones
					//check bone amount
				}
			}
		}
		else {
			
			if(!chickenPen.contains(myPlayer())) {
				getWalking().webWalk(chickenPen);
			}
			else {
				
				getSettings().setRunning(false);
				log("<<< At Chicken Pen >>>");

				if(closestChicken != null && closestChicken.exists()) {
					log("<<< Attacking chicken >>>");
					closestChicken.interact("Attack");
					new ConditionalSleep(20000, 500) {
						@Override
						public boolean condition() throws InterruptedException{
							return !closestChicken.exists();
						}
					};
					log("<<< Dead chicken >>>");
					
				}	
				else {
					log("<<< Targeting chicken >>>");
					closestChicken = getNpcs().closest("Chicken");
				}
				/*else if(getCombat().getFighting() != null && getCombat().getFighting().getHealthPercent() == 0){

					
				}*/

			}

		}
		return random(600, 900);
	}

	@Override
	public void onExit() {
		log("Bone reaper is full of bones . . . ");
	}

	@Override
	public void onPaint(Graphics2D g) {

	}

}