package essence.ch7;

/**
 * 조상타입의 인스턴스를 자손타입의 참조변수로 참조하는 것은 허용되지 않는다.
 */
public class CastubgTest2 {

	public static void main(String[] args) {
		Car car = new Car();
		Car car2 = null;
		FireEngine fe = null;
		
		car.drive();
		fe = (FireEngine)car;	// ERROR!!!  java.lang.ClassCastException: class essence.ch7.Car cannot be cast to class essence.ch7.FireEngine
		fe.drive();				
		car2 = fe;
		car2.drive();			
	}

}
