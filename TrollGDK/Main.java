package scripts.TrollGDK;

import java.awt.Graphics;

import org.tribot.script.Script;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

public class Main extends Script implements Starting, Ending, Painting {

	@Override
	public void onStart() {
		
	}
	
	@Override
	public void run() {
		while(Variables.running) {
			GDK.run();
			sleep(20, 30);
		}
	}


	@Override
	public void onEnd() {
		
	}

	@Override
	public void onPaint(Graphics g) {
		
	}

}
