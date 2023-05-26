# Chapter12. 지네릭스, 열거형, 애너테이션

<br/>

# 1. 지네릭스(Generics)

## 지네릭스란?
다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스를 **컴파일 시 타입을 체크(compile-time type check)**해주는 기능이다.  

- **타입 안정성 제공** : 의도하지 않은 타입의 객체가 저장되는 것을 방지함  
- **타입체크와 형변환을 생략할 수 있음** : ArrayList같은 컬렉션 클래스에서 객체를 꺼낼 때마다 타입쳌를 하고 형변환을 하지 않아도 됨

## 지네릭 클래스의 선언
지네릭 타입은 클래스와 메서드에 선언할 수 있다.  
클래스를 지네릭 클래스로 변경하면 아래와 같이 `<T>`를 붙여주면 된다.

```
class Box {
	Object item;
	
	void setItem(Object item) {this.item = item;}
	Object getItem() {return item;}
}

class Box<T> {	// 지네릭 타입 T선언
	T item;
	
	void setItem(T item) {this.item = item;}
	T getItem() {return item;}
}
```

여기서 `<T>`는 **타입 변수(type variable)**라고 한다.  
타입 변수가 여러 개인 경우에는 `Map<K,V>`와 같이 콤마`,`를 구분자로 나열한다.  
이들은 **기호의 종류만 다를 뿐 임의의 참조형 타입을 의미한다는 것은 모두 같다.**  

이 지네릭 클래스인 Box클래스의 객체를 생성할 때는 참조변수와 생성자에 타입 T대신 사용될 실제 타입을 지정해 주어야 한다.

```
Box<String> b = new Box<String>();	// 타입T대신 실제 타입 지정
//b.setItem(new Object());				// ERROR. String이외의 타입은 지정 불가
b.setItem("ABC");		
String item = b.getItem();				// 형변환이 따로 필요없음
```

### 지네릭스의 용어
- **Box<T>** : 지네릭 클랫, 'T의 Box' 또는 'T Box'
- **T** : 타입 변수 또는 타입 매개변수
- **Box** : 원시 타입(raw type)

### 지네릭스의 제한
모든 객체에 대해 동일하게 동작해야하는 static멤버에 타입 변수 T를 사용할 수 없다. T는 인스턴스변수로 간주되기 때문이다.  

```
class Box<T> {
	static T item;	// ERROR
	static int compare(T t1, T t2){//...} // ERROR
}
```

그리고, 지네릭 타입의 배열을 생성하는 것도 허영되지 않는다.  
배열 생성시 사용되는 `new`연산자는 컴파일 시점에 타입T가 뭔지 정확히 알아야 하기 때문이다.

```
class Box<T> {
	T[] itemArr;	// OK. T타입의 배열을 위한 참조변수
	
	T[] toArray() {
		T[] tmpArr = new T[itemArr.length];	// ERROR. 지네릭 배열 생성 불가
	}
}
```

## 지네릭 클래스의 객체 생성과 사용
- [[예제 12-1]](./FruitBoxEx1.java)

## 제한된 지네릭 클래스
만일 클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 `extends`를 사용한다.

```
interface Eatable {}
class FruitBox<T extends Eatable> {...}
```

클래스 Fruit자송니면서 Eatable인터페이스도 구현해야 한다면 아래와 같이 기호 `&`로 연결한다.

```
class FruitBox<T extends Fruit & Eatable> {...}
```

- [예제 12-2]](./FruitBoxEx2.java)

## 와일드 카드

```
class Juicer {
	static Juice makeJuice(FruitBox<Fruit> box) { //... }
}

FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

Juicer.makeJuice(fruitBox);
// Juicer.makeJuice(appleBox); // ERROR!

// ------------------------------------------------------

// ERROR!! 메서드 중복정의
static Juicer makeJuice(FruitBox<Fruit> box) { //... }
static Juicer makeJuice(fruitBox<Apple> box) { //... }
```

** 지네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않는다. **  
지네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버리기 때문에 메서드 중복 정의가 되는 것이다.  
이럴 때 사용하기 위해 고안된 것이 바로 **와일드 카드**이다.  

와일드 카드는 `?`로 표현하고, 어떠한 타입도 될 수 있다는 의미이다.

- **<? extends T>** : 와일드 카드의 상한 제한. T와 그 자손들만 가능
- **<? super T>** : 와일드 카드의 하한 제한. T와 그 조상들만 가능
- **<?>** : 제한 없음. 모든 타입이 가능. <T extends Object>와 동일

```
class Juicer {
	static Juice makeJuice(FruitBox<? extends Fruit> box) { //... }
}

FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

// 다른 종류의 FruitBox가 메서드의 매개변수로 가능하다.
Juicer.makeJuice(fruitBox);
Juicer.makeJuice(appleBox);
```

## 지네릭 메서드
지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것이다.  
static멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능하다.  
이 타입 매개변수는 메서드 내에서만 지역적으로 사용될 것이기 때문에 메서드가 static이든 아니든 상관 없다.

<br>

# 2. 열거형(enums)

## 열거형이란?
**서로 관련된 사웃를 편리하게 선언하기 위한 것**으로, 여러 상수를 정의할 때 사용하면 유용하다.

```
class Card {
	static final int CLOVER = 0;
	static final int HEARD = 1;
	static final int DIAMOND = 2;
	static final int SPADE = 3;
	
	static final int TWO = 0;
	static final int THREE = 1;
	static fing int FOUR = 2;
	
	final int kind;
	final int num;
}

// ---------------------------------------------

class Card {
	enum Kind {CLOVER, HEART, DIAMOND, SPADE}
	enum Value {TWO, THREE, FOUR}
	
	final Kind kind;		// 타입이 Kind임
	find Value value;
}
```

자바의 열거형은 **타입에 안전한 열거형(typesafe enum)**이라 실제 값이 같아도 타입이 다르면 컴파일 에러가 발생한다.  

```
if(Card.CLOVER == Card.TWO)	// true지만 false겨야 의미상 맞음
if(Card.Kind.CLOVER == Card.Value.TWO)	// 컴파일에러. 값은 같지만 타입이 다름
```

상수의 값이 바뀌면 해당 상수를 참조하는 모든 소스를 다시 컴파일 해야하지만, 열거형 상수를 사용하면 그럴 필요가 없다.

## 열거형의 정의와 사용

```
// 열거형 정의
enum 열거형이름 {상수명1, 상수명2, ...}

// 열거형 사용
열거형이름.상수명
```

열거형 상수간의 비교에는 equals()가 아닌 `==`를 사용할 수 있지만, `<`같은 비교연산자는 사용할 수 없다.

### 모든 열거형의 조상 - java.lang.Enum

- [예제 12-5](./EnumEx1.java)

## 열거형에 멤버 추가하기

열거형에 멤버를 추가하려면 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자도 추가해 주어야 한다.

```
enum Direction {
	EAST(1), SOUTH(5), WEST(-1), NORTH(10);

	private final int value; 	// 정수를 저장할 필드 추가

	Direction(int value) {		// 생성자 추가
		this.value = value;		
	}

	public int getValue() {
		return value;
	}
}
```



