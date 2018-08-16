
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;

import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Prayer;
import org.rev317.min.api.methods.SceneObjects;

import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.methods.Prayer.Curse;

import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;

import org.rev317.min.api.methods.Items.Option;
import org.rev317.min.api.methods.Menu;

public class StartArcade implements Strategy {
	private int scenario;

	public int getPrayer() {
		String prayer = Interfaces.getInterface(687).getMessage().substring(0, 2);
		if (prayer.contains("/")) {
			return Integer.parseInt(prayer.substring(0, 1));
		} else {
			return Integer.parseInt(prayer);
		}
	}

	public boolean activate() {
		for (SceneObject i : SceneObjects.getNearest(27237)) {
			if (i != null && !Players.getMyPlayer().isInCombat()) {
				scenario = 1;
				return true;
			}
		}

		if (Players.getMyPlayer().isInCombat() && !Prayer.isEnabled(Curse.SOUL_SPLIT)) {
			scenario = 2;
			return true;
		} else if (getPrayer() < 90 && (Inventory.contains(3031) || Inventory.contains(3029) || Inventory.contains(3027)
				|| Inventory.contains(3025))) {
			scenario = 5;
			return true;

		} else if (!Players.getMyPlayer().isInCombat() && Prayer.isEnabled(Curse.SOUL_SPLIT)) {
			scenario = 3;
			return true;
		} else if (Players.getMyPlayer().isInCombat() && Prayer.isEnabled(Curse.SOUL_SPLIT)) {
			scenario = 4;
			return true;
		}
		return false;
	}

	public void execute() {
		if (scenario == 1) {
			System.out.println("Attempting to enter Arcade...");
			Menu.sendAction(502, 60005, 48, 54, 56);

			Time.sleep(3000);

		} else if (scenario == 2) {
			System.out.println("Combat detected... enabling soul split to heal...");
			Prayer.enable(Curse.SOUL_SPLIT);
		} else if (scenario == 3) {
			System.out.println("Disabling Soulsplit because player is out of combat...");
			Prayer.disable(Curse.SOUL_SPLIT);
		} else if (scenario == 4) {

			System.out.println("Walking to avoid poison damage enabled...");
			Tile prev = new Tile(2272, 5333);
			Tile next = new Tile(2271, 5333);
			System.out.println("Walking to previous tile");
			Walking.walkTo(prev);
			Time.sleep(2000);
			System.out.println("Walking to next tile");
			Walking.walkTo(next);
			Time.sleep(10000);

		} else if (scenario == 5) {
			System.out.println("Restoring prayer...");
			if (Inventory.contains(3031)) {
				System.out.println("Drinking 1 sip");
				Inventory.getItem(3031).interact(Option.DRINK);
				Time.sleep(2000);
			} else if (Inventory.contains(3029)) {
				System.out.println("Drinking 2 sip");
				Inventory.getItem(3029).interact(Option.DRINK);
				Time.sleep(2000);
			} else if (Inventory.contains(3027)) {
				System.out.println("Drinking 3 sip");
				Inventory.getItem(3027).interact(Option.DRINK);
				Time.sleep(2000);
			} else if (Inventory.contains(3025)) {
				System.out.println("Drinking 4 sip");
				Inventory.getItem(3025).interact(Option.DRINK);
				Time.sleep(2000);
			}

		}

	}

}
