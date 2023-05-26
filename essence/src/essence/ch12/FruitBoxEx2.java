package essence.ch12;

import java.util.ArrayList;

class Fruit2 implements Eatable {
	public String toString() { return "Fruit"; }
}

class Apple2 extends Fruit2 { public String toString() { return "Apple"; } }
class Grape2 extends Fruit2 { public String toString() { return "Grape"; } }
class Toy2 { public String toString() { return "Toy"; } }

interface Eatable {}

public class FruitBoxEx2 {

	public static void main(String[] args) {
		FruitBox<Fruit2> fruitBox = new FruitBox<Fruit2>();
		FruitBox<Apple2> appleBox = new FruitBox<Apple2>();
		FruitBox<Grape2> grapeBox = new FruitBox<Grape2>();
//		FruitBox<Grape2> grapeBox = new FruitBox<Apple2>();	// ERROR!! Type mismatch: cannot convert from FruitBox<Apple2> to FruitBox<Grape2>
//		FruitBox<Toy2> toyBox = new FruitBox<Toy2>();	// ERROR!! Bound mismatch: The type Toy2 is not a valid substitute for the bounded parameter <T extends Fruit2 & Eatable> of the type FruitBox<T>
		
		fruitBox.add(new Fruit2());
		fruitBox.add(new Apple2());
		fruitBox.add(new Grape2());
		appleBox.add(new Apple2());
//		appleBox.add(new Grape2());	// ERROR!! The method add(Apple2) in the type Box2<Apple2> is not applicable for the arguments (Grape2)
		grapeBox.add(new Grape2());
		
		System.out.println("fruitBox-" + fruitBox);
		System.out.println("appleBox-" + appleBox);
		System.out.println("grapeBox-" + grapeBox);
	}

}

/*
 *  클래스 Fruit2의 자손이면서, Eatable인터페이스를 구현한다.
 *  이제 FruitBox에는 Fruit2의 자손이면서, Eatable을 구현한 클래스만 매개변수 T에 대입될 수 있다.
 */
class FruitBox<T extends Fruit2 & Eatable> extends Box2<T> {}

class Box2<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}