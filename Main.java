import java.awt.Graphics2D;

//import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.map.*;
import org.osbot.rs07.api.map.constants.Banks;

@ScriptManifest(author = "Andrew Miculka", info = "Harvest chicken bones for sale", name = "Bone Reaper", version = 0, logo = "")
public class Main extends Script {

	Entity closestBank = null;
	static Area chickenPen = new Area(new Position(3171, 3289, 0), new Position(3183, 3301, 0));
	
	@Override
	public void onStart() {
		log("Bank search beginning . . . ");
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
			if(getWalking().webWalk(chickenPen)) {
				//Turn off run
				//Attack chickens
			}
			else {
				
			}
		}
		return random(200, 300);
	}

	@Override
	public void onExit() {
		log("Bank search ending . . . ");
	}

	@Override
	public void onPaint(Graphics2D g) {

	}

}