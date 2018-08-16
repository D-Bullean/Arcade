import org.parabot.environment.scripts.Script;
import java.util.ArrayList;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.environment.scripts.ScriptManifest;

@ScriptManifest(author = "Dittymasta", category = Category.COMBAT, description = "Script made to auto run Arcade in Dreamscape", name = "Easy Money Easy AFK", servers = {
		"Dreamscape" }, version = 1)

public class MainScript extends Script {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	@Override
	public boolean onExecute() {
		strategies.add(new StartArcade());
		provide(strategies);
		return true;
	}

	@Override
	public void onFinish() {

	}
}