package essence.ch7;

/**
 * w조상 클래스의 멤버변수는 조상의 생성자에 의해 초기화 되어야 한다.
 */
public class PointTest {

	public static void main(String[] args) {
		Point3D p3 = new Point3D(1, 2, 3);
	}

}

class Point1 {
	int x, y;
	
	Point1(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	String getLocation() {
		return "x : " + x + ", y : " + y;
	}
}

class Point3D extends Point1 {
	int z;
	
	Point3D(int x, int y, int z) {
		
		// 생성자 첫 줄에서 다른 생성자를 호출하지 않기 때문에 컴파일러가 super();을 삽입해줌. 그러나 기본 생성자가 없으므로 컴파일 에러가 남
		super(x, y);
		this.z = z;
	}
	
	String getLocation() {
		return "x : " + x + ", y : " + y + ", z : " + z;
	}
}
