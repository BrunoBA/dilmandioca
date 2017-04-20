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
		
		while (true) {
			
			System.out.println("Daale");
			
			movimentar();

			movingForward = true;
			setTurnRight(90);
			waitFor(new TurnCompleteCondition(this));
			setTurnLeft(180);
			waitFor(new TurnCompleteCondition(this));
			setTurnRight(180);
			waitFor(new TurnCompleteCondition(this));
		}
	}

	
	public void movimentar(){
		setAhead(40000);
		
		if(40000 + getX() > getBattleFieldHeight()){
			setAhead(getX() - getBattleFieldHeight());
		}
		if(40000 + getY() > getBattleFieldWidth()){
			setAhead(getY() - getBattleFieldWidth());
		}
		if(getX() - 40000 < 0){
			setAhead(getX() - 40000);
		}
		if(getY() - 40000 < 0){
			setAhead(getY() - 40000);
		}
	}
	
	public void onHitWall(HitWallEvent e) {
		System.out.println("Merda, bati na parede");
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
		

		//para mirar o radar no adversário.
		turnRadarRight(anguloRelativo(e.getBearing()+getHeading()-getRadarHeading()));
		
		//para mirar o canhão no adversário.
		turnGunRight(anguloRelativo(e.getBearing()+getHeading()-getGunHeading())); 
		
		//para virar seu robô em direção do adversário
		turnRight(anguloRelativo(e.getBearing())); 
		if(myLife(getEnergy())){	
			if(e.getDistance() >= 0.0  && e.getDistance() <= 50.00 ){
				fire(3);
			}
			else if(e.getDistance() > 50.0  && e.getDistance() <= 200.00 ){
				if(enemyLife(e.getEnergy())){
					fire(3);	
				}
				fire(2);
			}
			else if(e.getDistance() > 200.0  && e.getDistance() <= 300.00 ){
				if(enemyLife(e.getEnergy())){
					fire(3);		
				}
				fire(1);
			}
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {	
		
		//para mirar o radar no adversáriåo.
		turnRadarRight(anguloRelativo(e.getBearing()+getHeading()-getRadarHeading()));
		
		//para mirar o canhão no adversário.
		turnGunRight(anguloRelativo(e.getBearing()+getHeading()-getGunHeading())); 	
			
	}
	
	public void onRobotDeath(RobotDeathEvent e){
		System.out.println(e.getName()+" aquele abraço!");
	}
	
	public void onWin(){
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}
	
	public double anguloRelativo(double ANG) {
		if (ANG> -180 && ANG<= 180) {
			return ANG;
		}
		double REL = ANG;
		while (REL<= -180) {
			REL += 360;
		}
		while (ANG> 180) {
			REL -= 360;
		}
		return REL;
	}
	
	// Método feito para a mudança de comportamento de ataque do rôbo (atacar mais)
	private boolean enemyLife(double life){
	
		if(life < 20)
			return true;
		return false;
	}
	
	// Método feito para a mudança de comportamento para defesa do rôbo (defesa/sobrevivencia)
	private boolean myLife(double life){
		if(life < 10)
			return true;
		return false;
	}
}
