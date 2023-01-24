package essence.ch7;

public class DrawShape {

	public static void main(String[] args) {
		
		// 1. 삼각형을 위한 Point 객체배열을 생성하여 삼각형을 그린다.
		Point[] p  = {
				new Point(100, 100),
				new Point(140, 50),
				new Point(200, 100)
		};
		
		Triangle t = new Triangle(p);
		
		// 2. Point객체를 생성하여 원을 그린다.
		Circle c = new Circle(new Point(150, 150), 50);
		
		t.draw();	
		c.draw();	
	}

}

class Shape {
	String color = "black";
	void draw() {
		System.out.printf("[colors=%s]%n", color);
	}
}

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point() {
		this(0, 0);
	}
	
	String getXY() {
		return "(" + x + ", " + y + ")";
	}
}

// 원은 도형이다. (is-a : 상속)
class Circle extends Shape {
	// 원은 점을 갖는다. (has-a : 포함)
	// Point클래스와 포함관계를 맺는다.
	Point center;	// 원의 원점좌표
	int r;			// 반지름
	
	Circle() {
		this(new Point(0, 0), 100);
	}
	
	Circle(Point center, int r) {
		this.center = center;
		this.r = r;
	}
	
	// 오버라이딩
	void draw() {
		System.out.printf("[center=(%d, %d), r=%d, color=%s]%n", center.x, center.y, r, color);
	}
}

// 삼각형은 도형이다. (is-a : 상속)
class Triangle extends Shape {
	// 삼각형은 3개의 점을 갖는다. (has-a : 포함)
	// Point클래스와 포함관계를 맺는다. 
	Point[] p = new Point[3];	
	
	Triangle(Point[] p) {
		this.p = p;
	}
	
	// 오버라이딩
	void draw() {
		System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]%n", p[0].getXY(), p[1].getXY(), p[2].getXY(), color);
	}
}