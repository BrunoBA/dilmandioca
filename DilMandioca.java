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

		// Loop forever
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
			System.out.println("Tiro com potencia 5");
			fire(5);
		}
		else if(e.getDistance() > 100.0  && e.getDistance() <= 200.00 ){
			System.out.println("Tiro com potencia 4");
			fire(4);
		}
		else if(e.getDistance() > 200.0  && e.getDistance() <= 300.00 ){
			System.out.println("Tiro com potencia 3");
			fire(3);
		}
		else if(e.getDistance() > 300.0  && e.getDistance() <= 400.00 ){
			System.out.println("Tiro com potencia 2");
			fire(2);
		}
		else if(e.getDistance() > 500.0  && e.getDistance() <= 600.00 ){
			System.out.println("Tiro com potencia 1");
			fire(1);
		}
		
		System.out.println("_______________");
	}

	public void onHitRobot(HitRobotEvent e) {
		
		//e.get
	
	}
}
