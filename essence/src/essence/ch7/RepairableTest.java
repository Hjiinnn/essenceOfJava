package essence.ch7;

class RepairableTest {

	public static void main(String[] args) {
		Tank tank = new Tank();
		Dropship dropship = new Dropship();
		Marine marine = new Marine();
		SCV scv = new SCV();
		
		scv.repair(tank);
		scv.repair(dropship);
		// scv.repair(marine); 	// ERROR!! he method repair(Repairable) in the type SCV is not applicable for the arguments (Marine) 
	}

}

interface Repairable {}

class Unit2 {
	int hitPoint;
	final int MAX_HP;
	
	Unit2(int hp) {
		MAX_HP = hp;
	}
}

class GroundUnit extends Unit2 {
	GroundUnit(int hp) {
		super(hp);
	}
}

class AirUnit extends Unit2 {
	AirUnit(int hp) {
		super(hp);
	}
}

class Tank extends GroundUnit implements Repairable {
	Tank() {
		super(150);
		hitPoint = MAX_HP;
	}
	
	public String toString() {
		return "Tank";
	}
}

class Dropship extends AirUnit implements Repairable {
	Dropship() {
		super(125);
		hitPoint = MAX_HP;
	}
	
	public String toString() {
		return "Dropship";
	}
}

class Marine extends GroundUnit {
	Marine() {
		super(40);
		hitPoint = MAX_HP;
	}
}

class SCV extends GroundUnit implements Repairable {
	SCV() {
		super(60);
		hitPoint = MAX_HP;
	}
	
	void repair(Repairable r) {
		if(r instanceof Unit2) {
			Unit2 u = (Unit2)r;
			
			while(u.hitPoint != u.MAX_HP) {
				// Unit의 HP를 증가시킨다.
				u.hitPoint++;
			}
			
			System.out.println(u.toString() + "의 수리가 끝났습니다.");
		}
	}
}

