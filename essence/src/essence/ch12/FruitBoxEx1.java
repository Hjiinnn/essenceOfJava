package essence.ch12;

import java.util.ArrayList;

class Fruit { public String toString() { return "Fruit"; } }
class Apple extends Fruit { public String toString() { return "Apple"; } }
class Grape extends Fruit { public String toString() { return "Grape"; } }
class Toy { public String toString() { return "Toy"; } }

public class FruitBoxEx1 {

	public static void main(String[] args) {
		Box<Fruit> fruitBox = new Box<Fruit>();
		Box<Apple> appleBox = new Box<Apple>();
		Box<Toy> toyBox = new Box<Toy>();
//		Box<Grape> grapeBox = new Box<Apple>();	// ERROR. type mismatch: cannot convert from Box<Apple> to Box<Grape>
		
		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());

		// 대입된 타입과 다른 타입의 객체는 추가할 수 없다.
		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Toy());	// ERROR. The method add(Apple) in the type Box<Apple> is not applicable for the arguments (Toy)
		
		toyBox.add(new Toy());
//		toyBox.add(new Apple());	// ERROR. The method add(Toy) in the type Box<Toy> is not applicable for the arguments (Apple)
		
		System.out.println(fruitBox);
		System.out.println(appleBox);
		System.out.println(toyBox);
	}

}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}