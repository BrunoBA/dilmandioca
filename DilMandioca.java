package dilmandioca;

import java.awt.*;

import robocode.*;

public class DilMandioca extends AdvancedRobot {
	boolean movingForward;
	
	public void run() {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.yellow);
		setScanColor(new Color(0, 255, 0));
		
		System.out.println(getName());

		while (true) {
			setAhead(40000);
			movingForward = true;
			setTurnRight(90);
			waitFor(new TurnCompleteCondition(this));
			setTurnLeft(180);
			waitFor(new TurnCompleteCondition(this));
			setTurnRight(180);
			waitFor(new TurnCompleteCondition(this));
		}
	}

	public void onHitWall(HitWallEvent e) {
		// Bounce off!
		reverseDirection();
	}

	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}
 
	public void onScannedRobot(ScannedRobotEvent e) {
		
		System.out.println(e.getDistance());
		
		if(e.getDistance() >= 0.0  && e.getDistance() <= 100.00 ){
			fire(3);
		}
		else if(e.getDistance() > 100.0  && e.getDistance() <= 200.00 ){
			fire(2);
		}
		else if(e.getDistance() > 200.0  && e.getDistance() <= 300.00 ){
			fire(1);
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {		

		System.out.println(e.getBearing());
			
	}
	
}
