import java.awt.Graphics2D;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.api.model.Entity;

@ScriptManifest(author = "Andrew Miculka", info = "Bank search", name = "Bank Test", version = 0, logo = "")
public class Main extends Script {

	Entity closestBank = null;
	
	@Override
	public void onStart() {
		log("Bank search beginning . . . ");
	}

	@Override
	public int onLoop() throws InterruptedException {
		if(getWalking().webWalk(Banks.AL_KHARID, Banks.EDGEVILLE, Banks.FALADOR_EAST, Banks.FALADOR_WEST, Banks.LUMBRIDGE_UPPER)) {
			log("At the bank . . .");
			closestBank = getBank().closest();
			if(closestBank != null && !getBank().isOpen()) {
				getBank().open();
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