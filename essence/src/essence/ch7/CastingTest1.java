package essence.ch7;

public class CastingTest1 {

	public static void main(String[] args) {
		Car car = null;
		FireEngine fe = new FireEngine();
		FireEngine fe2 = null;
		
		fe.water();
		car = fe;				// Up-Casting car = (Car)fe;
		//car.water();			// ERROR!!! The method water() is undefined for the type Car
		fe2 = (FireEngine)car;	// Down-Casting
		fe2.water();
	}

}

class Car {
	String color;
	int door;
	
	void drive() {
		System.out.println("drive, Brrr~");
	}
	
	void stop() {
		System.out.println("stop!!");
	}
}

class FireEngine extends Car {
	void water() {
		System.out.println("water!!!!");
	}
}
