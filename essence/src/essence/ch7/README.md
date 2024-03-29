# Chapter07. 객체지향 프로그래밍2

<br/>

# 1. 상속(inheritance)

## 상속의 정의와 장점

상속이란 **기존의 클래스를 재사용하여 새로운 클래스를 작성**하는 것이다.  
상속을 통해서 클래스를 작성면 적은 양의 코드로 새로운 클래스를 작성할 수 있고, 코드를 공통적으로 관리할 수 있기 때문에 **코드의 추가 및 변경**이 용이하다.  
  
새로 작성하고자 하는 클래스의 이름 뒤에 상속받고자 하는 클래스의 이름을 키워드 `extends`와 함께 써주기만 하면 된다.

```
class Child extends Parent {

}
```

이 두 클래스는 서로 **상속 관계**에 있다고 하며, 상속해주는 클래스를 **조상 클래스**라 하고, 상속 받는 클래스를 **자손 클래스**라 한다.  

자손 클래스는 조상 클래스의 모든 멤버를 상속받기 때문에, Child클래스는 Parent클래스의 멤버들을 포함한다고 할 수 있다.  
반면 Child클래스에 새로운 코드가 추가되어도 조상인 Parent클래스는 아무런 영향도 받지 않는다.  
즉, 조상 클래스가 변경되면 자손 클래스는 자동적으로 영향을 받게 되지만, 자손클래스가 변경되는 것은 조상 클래스에 아무런 영향을 주지 않는다.  

> 생성자와 초기화 블럭은 상속되지 않는다. 멤버만 상속된다.  
> 자손 클래스의 멤버 개수는 조상 클래스보다 항상 같거나 많다.  

* 접근 제어자가 private 또는 default인 멤버들은 상속되지 않는다기보다 상속은 받지만 자손클래스로부터 접근이 제한되는 것이다.  

- 자손 클래스의 인스턴스를 생성하면 조상 크래스의 멤버가 합쳐진 하나의 인스턴스로 생성되기 때문에 따로 조상 클래스의 인스턴스를 생성하지 않고도 조상 클래스의 멤버들을 사용할 수 있다. [[예제 7-1]](./CaptionTvTest.java)  

<br/>

## 클래스간의 관계 - 포함관계
  
클래스 간의 포함관계를 맺어주는 것은 **한 클래스의 멤버변수로 다른 클래스 타입의 참조변수를 선언하는 것**을 뜻한다.  

Car클래스를 작성할 때, Car클래스의 단위구성요소인 Engine, Door와 같은 클래스를 미리 작성해 놓고 이 들을 Car클래스의 멤버변수로 선언하여 포함관계를 맺어 줘서 클래스를 재사옹할 수 있다.

```
class Car {
	Engine e = new Engine();	// 엔진
	Door[] d = new Door[4];	// 문
}
```

<br/>

## 클래스간의 관계 설정하기

> **상속관계** '\~은 ~이다. (is-a)'  
> **포함관계** '\~은 ~을 가지고 있다. (has-a)'

- 도형그리기 예제 [[예제 7-2]](./DrawShape.java)    
- 카드뽑기 예제 [[예제 7-3]](./DeckTest.java)    

<br/>

## 단일 상속(single inheritance)

자바에서는 오직 단일 상속만을 허용하기 때문에 둘 이상의 클래스로부터 상속을 받을 수 없다.  

- 다중상속 대신 상속과 포함관계를 사용한다. [[예제 7-4]](./TVCR.java)  

<br/>

## Object클래스 - 모든 클래스의 조상

Object클래스는 **모든 클래스 상속계층도의 최상위에 있는 조상클래스**이다.  
다른 클래스로부터 *상속 받지 않는 모든 클래스들은 자동적으로 Object클래스로부터 상속받게 함*으로써 이것을 가능하게 한다.  
반면, 이미 어떤 클래스로부터 상속받도록 작성된 클래스에 대해서는 컴파일러가 `extends Object`를 추가하지 않는다.  
만일 다른 클래스로부터 상속을 받는다고 하더라도 상속계층도를 따라 조상클래스, 조상클래스의 조상클래스를 찾아 올라가다 보면 결국 마지막 최상위 조상은 Object클래스일 것이다.  

<br/><br/>

# 2. 오버라이딩(overriding)

## 오버라이딩이란?

조상 클래스로부터 **상속받은 메서드의 내용을 변경**하는 것을 오버라이딩이라고 한다.  
상속받은 메서드를 그대로 사용하기도 하지만, 자손 클래스 자신에 맞게 변경해야 하는 경우 조상의 메서드를 오버라이딩한다.  

<br/>

## 오버라이딩의 조건

오버라이딩은 메서드의 내용만을 새로 작성하는 것이므로 메서드의 선언부는 조상의 것과 완전히 일치해야 한다.  

> 이름이 같아야 한다.  
> 매개변수가 같아야 한다.  
> 반환타입이 같아야 한다.  

*JDK1.5부터는 '공변 반환타입(covariant return type)'이 추가되어, 반환타입을 자손 클래스의 타입으로 변경하는 것은 가능하도록 조건이 완화되었다.*  

**1. 접근 제어자는 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.**  
만일 조상 클래스에 정의된 메서드의 접근 제어자가 `protected`라면, 이를 오버라이딩하는 자손 클래스의 메서드는 접근 제어자가 `protected`나 `public`이어야 한다.  

**2. 조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다.**  
자손 클래스 메서드의 예외 개수가 조상 클래스 메서드의 예외 개수보다 적을 경우 오버라이딩이 된다.  
주의할 점은 `Exception`이다. Exception은 모든 예외의 최고 조상이므로 가장 많은 개수의 예외를 던질 수 있도록 선언한 것과 마찬가지이다.  

조상 클래스의 메서드를 자손 클래스에서 오버라이딩 할 때  
> 접근 제어자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.  
> 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.  
> 인스턴스메서드를 static메서드로 또는 그 반대로 변경할 수 없다.  

*조상 클래스에서 정의된 static메서드를 자손클래스에서 똑같은 이름의 static메서드로 정의한다면, 이는 각 클래스 별개의 static메서드를 정의한 것일 뿐 오버라이딩을 한 것은 아니다.*  

<br/>

## 오버로딩 vs. 오버라이딩

**오버로딩(overloading)** : 기존에 없는 새로운 메서드를 정의하는 것 (new)  
**오버라이딩(overriding)** : 상속받은 메서드의 내용을 변경하는 것 (change, modify)  

<br/>

## super

super는 자손 클래스에서 **조상 클래스로부터 상속받은 멤버를 참조하는데 사용되는 참조변수**이다.  
상속받은 멤버와 자신의 멤버와 이름이 같을 경우 `super`를 붙여서 구별할 수 있다.  
조상 클래스로부터 상속받은 멤버도 자손 클래스 자신의 멤버이므로 `super`대신 `this`를 사용할 수 있다.  
그래도 조상 클래스의 멤버와 자손클래스의 멤버가 중복 정의되어 서로 구별해야하는 경우에만 `super`를 사용하는 것이 좋다.  
모든 인스턴스메서드에는 자신이 속한 인스턴스의 주소가 지역변수로 저장되는데, 이것이 참조변수인 `this`와 `super`의 값이 된다.  
반면, static메서드는 인스턴스와 관련이 없다. 그래서 this와 마찬가지로 super역시 사용할 수 없다.  

- [[예제 7-5]](./SuperTest.java)  
- [[예제 7-6]](./SuperTest2.java)  

<br/>

## super() - 조상 클래스의 생성자

`this()`와 마찬가지로 `super()`역시 생성자이다.  
`this()`는 **같은 클래스의 다른 생성자**를 호출하는 데 사용되지만,  
`super()`은 **조상 클래스의 생성자**를 호출하는 데 사용된다.  

자손 클래스의 인스턴스를 생성하면, 자손의 멤버와 조상의 멤버가 모두 합쳐진 하나의 인스턴스가 생성되기 때문에 조상 클래스 멤버들을 바로 사용할 수 있다.  
이 때 조상 클래스 멤버의 초기화 작업이 수행되어야 하기 때문에 **자손 클래스의 생성자에서 조상 클래스의 생성자가 호출**되어야 한다.  
생성자의 첫 줄에서 조상클래스의 생성자를 호출해야하는 이유는 자손 클래스의 멤버가 조상 클래스의 멤버를 사용할 수 있으므로 조상의 멤버들이 먼저 초기화되어 있어야 하기 때문이다.  
이와 같은 조상 클래스 생성자의 호출은 클래스의 상속관계를 거슬러 올라가면서 계속 반복된다. 마지막으로 모든 클래스의 최고 조상인 Object클래스의 생성자인 Object()까지 가서야 끝이 난다.  
그래서 **Object클래스를 제외한 모든 클래스의 생성자는 첫 줄에 반드시 자신의 다른 생성자 또는 조상의 생성자를 호출해야 한다.** 그렇지 않으면 컴파일러는 생성자의 첫 줄에 `super();`을 자동적으로 추가할 것이다.  

- **조상 클래스의 멤버변수는 조상의 생성자에 의해 초기화 되어야 한다.** [[예제 7-7]](./PointTest.java)  
- [[예제 7-8]](./PointTest2.java)  

<br/>

# 3. package와 import 

## 패키지(package)

패키지란, **클래스의 묶음**이다. 패키지에는 클래스 또는 인터페이스를 포함시킬 수 있으며, 서로 관련된 클래스들끼리 그룹 단위로 묶어 놓음으로써 클래스를 효율적으로 관리할 수 있다.  
**클래스가 물리적으로 하나의 클래스파일(.class)인 것과 같이 패키지는 물리적으로 하나의 디렉토리이다.**  
그래서 어떤 패키지에 속한 클래스는 해당 디렉토리에 존재하는 클래스파일(.class)이어야 한다.  

> 하나의 소스파일에는 첫 번째 문장으로 단 한번의 패키지 선언만을 허용한다.  
> 모든 클래스는 반드시 하나의 패키지에 속해야 한다.  
> 패키지는 점(.)을 구분자로 하여 계층구조로 구성할 수 있다.  
> 패키지는 물리적으로 클래스파일(.class)을 포함하는 하나의 디렉토리이다.  

<br/>

## 패키지의 선언 

패키지 선언문은 반드시 소스파일에서 주석과 공백을 제외한 첫 번째 문장이어야 하며, 하나의 소스파일에 단 한번만 선언될 수 있다.  
해당 소스파일에 포함된 모든 클래스나 인터페이스는 선언된 패키지에 속하게 된다.

```
package 패키지명;
```

모든 클래스는 반드시 하나의 패키지에 포함되어야 함에도 불구하고 패키지를 작성하지 않아도 문제가 없었던 이유는 자바에서 기본적으로 제공하는 `이름없는 패키지(unnamed package)`때문이다.  
소스파일에 자신이 속할 패키지를 지정하지 않는 모든 클래스들은 자동적으로 '이름 없는 패키지'에 속하게 된다. 

- [[예제 7-9]](./PackageTest.java) 

<br/>

## import문

소스코드를 작성할 때 다른 패키지의 클래스를 사용하려면 패키지명이 포함된 클래스 이름을 사용해야 하지만, import문으로 사용하고자 하는 클래스의 패키지를 미리 명시해주면 소스코드에 사용되는 클래스이름에서 패키지명은 생략할 수 있다.  
import문의 역할은 컴파일러에게 소스파일에 사용된 클래스의 패키지에 대한 정보를 제공하는 것이다.  
컴파일 시 컴파일러는 import문을 통해 소스파일에 사용된 클래스들의 패키지를 알아 낸 다음, 모든 클래스 이름 앞에 패키지명을 붙여 준다.  
*import문은 프로그램의 성능에 전혀 영향을 끼치지 않는다.*  

<br/>

## import문의 선언 

모든 소스파일에서 import문은 package문 다음에, 그리고 클래스 선언문 이전에 위치해야 한다.  

```
import 패키지명.클래스명;
import 패키지명.*;
```

- [[예제 7-10]](./ImportTest.java)

<br/>

## static import문

static import문을 사용하면 **static멤버를 호출할 때 클래스 이름을 생략할 수 있다.**  
따라서 특정 클래스의 static멤버를 자주 사용할 때 편리하며, 코드가 간결해지는 것을 볼 수 있다. 

- [[예제 7-11]](./StaticImportEx1.java)  

<br/>

# 4. 제어자(modifier)

## 제어자란?

제어자(modifier)는 클래스, 변수 또는 메서드의 선언부에 함께 사용되어 부가적인 의미를 부여한다.  

> **접근 제어자** : public, protected, default, private  
> **그 외** : static, final, abstract, native, translent, synchronized, volatile, strictfp  


<br/>

## static - 클래스의, 공통적인

static은 **클래스의**, **공통적인**의 의미를 갖고 있다.  
인스턴스변수는 하나의 클래스로부터 생성되었더라도 각기 다른 값을 유지하지만, 클래스변수는 인스턴스에 관계 없이 같은 값을 갖는다. 하나의 변수를 모든 인스턴스가 공유하기 때문이다.  

> static이 사용될 수 있는 곳 - 멤버변수, 메서드, 초기화블럭  

<br/>

## final - 마지막의, 변경될 수 없는

final은 **마지막의**, 또는 **변경될 수 없는**의 의미를 가지고 있으며 거의 모든 대상에 사용될 수 있다.  
변수에 사용하면 값을 변경할 수 없는 상수가 되며, 메서드에 사용하면 오버라이딩을 할 수 없게 되고, 클래스에 사용하면 자손클래스를 정의하지 못하게 된다.  

> final이 사용될 수 있는 곳 - 클래스, 메서드, 멤버변수, 지역변수  

### 1. 생성자를 이용한 final멤버 변수의 초기화

final이 붙은 변수는 상수이므로 일반적으로 선언과 초기화를 동시에 하지만, 인스턴스변수의 경우 생성자에서 초기화 되도록 할 수 있다.  
클래스 내에 매개변수를 갖는 생성자를 선언하여, 인스턴스를 생성할 때 final이 붙은 멤버변수를 초기화하는데 필요한 값을 생성자의 매개변수로부터 제공받는 것이다.  

- [[예제 7-12]](./FinalCardTest.java)  

<br/>

## abstract - 추상의, 미완성의 

abstract는 **미완성**의 의미를 갖고 있다. 메서드의 선언부만 작성하고 실제 수행내용은 구현하지 않은 추상 메서드를 선언하는데 사용된다.  
그리고 클래스에 사용되어 클래스 내에 추상메서드가 존재한다는 것을 쉽게 알 수 있게 한다.  

> abstract가 사용될 수 있는 곳 - 클래스, 메서드  

추상클래스는 아직 완성되지 않은 메서드가 존재하는 **미완성 설계도**이므로 **인스턴스를 생성할 수 없다**.  
인스턴스를 생성하지 못하게 하기 위해 완성된 클래스도 `abstract`를 붙여서 추상클래스로 만드는 경우도 있다.  

```
abstract class AbstractTest {		// 추상 클래스 (추상 메서드를 포함한 클래스)
	abstract void move();			// 추상 메서드 (구현부가 없는 메서드)
}
```

<br/>

## 접근 제어자(access modifier)

접근제어자는 멤버 또는 클래스에 사용되어, 해당하는 멤버 또는 킇래스를 **외부에서 접근하지 못하도록 제한하는 역할**을 한다.  

> 접근 제어자가 사용될 수 있는 곳 - 클래스, 멤버변수, 메서드, 생성자  
> **private** : 같은 클래스 내에서만 접근 가능  
> **default** : 같은 패키지 내에서만 접근 가능  
> **protected** : 같은 패키지 내에서, 그리고 다른 패키지의 자손클래스에서 접근 가능  
> **public** : 접근 제한이 전혀 없다.  
> public > protected > (default) > private  

### 1. 접근 제어자를 이용한 캡슐화

클래스나 멤버, 주로 멤버에 접근 제어자를 사용하는 이유는 **클래스 내부에 선언된 데이터를 보호**하기 위해서이다.  
이것을 **데이터 감추기(data hiding)**이라고 하며, 객체지향개념의 **캡슐화(encapsulation)**에 해당하는 내용이다.  
또 다른 이유는 클래스 내에서만 사용되는, 내부 작업을 위해 임시로 사용되는 멤버변수나 부분작업을 처리하기 위한 메서드 등의 멤버들을 클래스 내부에 감추기 위해서이다.  
외부에서 접근할 필요가 없는 멤버들을 private으로 지정하여 외부에 노출시키지 않음으로써 복잡성을 줄일 수 있다.  

> 접근 제어자를 사용하는 이유  
> \- 외부로부터 데이터를 보호하기 위해서  
> \- 외부에느 불필요한, 내부적으로만 사용되는, 부분을 감추기 위해서  

만일 상속을 통해 확장될 것이 예상되는 클래스라면 멤버에 접근 제한을 주되 자손클래스에서 접근하는 것이 가능하도록 private대신 `protected`를 사용한다.  

- [[예제 7-13]](./TimeTest.java)  

### 2. 생성자의 접근 제어자

생성자에 접근 제어자를 사용함으로써 **인스턴스의 생성을 제한**할 수 있다.  
생성자의 접근 제어자를 private으로 지정하면, 내부에서 인스턴스를 생성할 수 있어도 외부에서 생성자에 접근할 수 없으므로 인스턴스를 생성할 수 없게 된다.  
대신 인스턴스를 생성해서 반환해주는 public메서드를 제공함으로써 외부에서 이 클래스의 인스턴스를 사용하도록 할 수 있다. 이 메서드는 public인 동시에 static이어야 한다.  

이처럼 생성자를 통해 직접 인스턴스를 생성하지 못하게 하고 public메서드를 통해 인스턴스에 접근하게 함으로써 사용할 수 있는 인스턴스의 개수를 제한할 수 있다.  

생성자가 private인 클래스는 다른 클래스의 조상이 될 수 없다. 따라서 앞에 final을 추가하여 상속할 수 없는 클래스라는 것을 알리는 것이 좋다.  

- [[예제 7-14]](./SingletonTest.java) 

<br/>

## 제어자(modifier)의 조합

> **메서드에 static과 abstract를 함께 사용할 수 없다.**  

static메서드는 몸통이 있는 메서드만 사용할 수 있다.  

> **클래스에 abstract와 final을 동시에 사용할 수 없다.**  

클래스에 사용되는 final은 클래스를 확장할 수 없다는 의미이고, abstract는 상속을 통해서 완성되어야 한다는 의미이므로 서로 모순된다.  

> **abstract메서드의 접근 제어자가 private일 수 없다.**  

abstract메서드는 자손클래스에서 구현해주어야 하는데 접근제어자가 private이라면 자손클래스에서 접근할 수 없다.  

> **메서드에 private과 final을 같이 사용할 필요는 없다.**  

접근 제어자가 private인 메서드는 오버라이딩될 수 없기 때문에 이 둘 중 하나만 사용해도 의미가 충분하다.

<br/>

# 5. 다형성(polymorphism)

## 다형성이란?  

객체지향개념에서의 다형성이란 **여러 가지 형태를 가질 수 있는 능력**을 의미하며, 자바에서는 한 타입의 참조변수로 여러 타입의 객체를 참조할 수 있도록 함으로써 다형성을 프로그램적으로 구현하였다.  
즉, **조상클래스 타입의 참조변수로 자손클래스의 인스턴스를 참조할 수 있도록 하였다**는 것이다.  

```
class Tv {

	boolean power;
	int channel;
	
	void power() { power = !power; }
	void channelUp() { ++channel; }
	void channelDown() { --channel; }
}

class CaptionTv extends Tv {
	String text;
	void caption() {}
}
```

두 클래스가 상속관계에 있을 때, 아래와 같이 조상 클래스 타입의 참조변수로 자손 클래스의 인스턴스를 참조하는 것이 가능하다.

```
Tv t = new CaptionTv();
```

이 경우 실제 인스턴스가 CaptionTv타입이라 할 지라도, 참조 변수 t로는 CaptionTv인스턴스의 모든 멤버를 사용할 수 없다.  
Tv타입의 참조변수로는 CaptionTv인스턴스 중에서 Tv클래스의 멤버들만 사용할 수 있다.  
따라서 CaptionTv인스턴스의 멤버 중에서 Tv클래스에 정의되지 않은 멤버, text와 caption()은 참조변수 t로 사용이 불가능하다.  
**같은 타입의 인스턴스지만, 참조변수의타입에 따라 사용할 수 있는 멤버변수가 달라진다.**  
  
반대로 아래와 같이 자손 타입의 참조변수로 조상타입의 인스턴스를 참조하는 것은 불가능하다.

```
CaptionTv c = new Tv();
```

실제 인스턴스인 Tv의 멤버 개수보다 참조변수 c가 사용할 수 있는 멤버 개수가 더 많기 때문이다.  
**참조변수가 사용할 수 있는 멤버의 개수는 인스턴스의 멤버 개수보다 같거나 적어야 한다.**  

> 조상타입의 참조변수로 자손타입의 인스턴스를 참조할 수 있다.  
> 그러나 자손타입의 참조변수로 조상타입의 인스턴스를 참조할 수는 없다.  
> 참조변수가 사용할 수 있는 멤버의 개수는 인스턴스의 멤버 개수보다 같거나 적어야 하기 때문이다.  

<br/>

## 참조변수의 형변환

참조변수는 서로 **상속관계에 있는 클래스 사이에서만 형변환**을 할 수 있다.  
자손타입의 참조변수를 조상타입의 참조변수로, 조상타입의 참조변수를 자손타입의 참조변수로의 형변환이 가능하다.  
또한, 바로 윗 조상이나 자손이 아닌, 조상의 조상으로도 형변환이 가능하기 때문에 모든 참조변수는 모든 클래스의 조상인 Object클래스 타입으로 형변환이 가능하다.  

참조형 변수의 형변환에서는 자손타입의 참조변수를 조상타입으로 형변환하는 경우에는 형변환을 생략할 수 있다.

```
자손타입 -> 조상타입 (Up-casting) : 형변환 생략 가능
자손타입 <- 조상타입 (Down-casting) : 형변환 생략 불가
```

**형변환은 참조변수의 타입을 변환하는 것이지, 인스턴스를 변환하는 것은 아니기 때문에 참조변수의 형변환은 인스턴스에 아무런 영향을 미치지 않는다.**  
**단지, 참조변수의 형변환을 통해서, 참조하고 있는 인스턴스에서 사용할 수 있는 멤버범위를 조절하는 것뿐이다.**  


- [[예제 7-15]](./CastingTest1.java)  
- 조상타입의 인스턴스를 자손타입의 참조변수로 참조하는 것은 허용되지 않는다. [[예제 7-16]](./CastingTest2.java)  

> 서로 상속관계에 있는 타입간의 형변환은 양방향으로 자유롭게 수행될 수 있으나, 참조변수가 가리키는 인스턴스의 자손타입으로 형변환은 허용되지 않는다. 그래서 참조변수가 가리키는 인스턴스의 타입이 무엇인지 확인하는 것이 중요하다.  

<br/>

## instanceof 연산자

**참조변수가 참조하고 있는 인스턴스의 실제 타입**을 알아보기 위해 `instanceof`연산자를 사용한다.  
주로 조건문에 사용되며, instanceof의 왼쪽에는 참조변수를, 오른쪽에는 타입을 피연산자로 위치한다. 그리고 연산의 결과로 boolean값을 반환하는데, 값이 null인 경우에는 false를 반환한다.  
instanceof의 결과로 true를 얻었다는 것은 참조변수가 검사한 타입으로 **형변환이 가능**하다는 것을 뜻한다.


```
void doWork(Car c) {
	if(c instanceof FireEngine) {
		FireEngine fe = (fireEngine)c;
		...
	} else if(c instanceof Ambulance) {
		Ambulance a = (Ambulance) c;
		...
	}
}
```

- 어떤 타입에 대한 instanceof연산자의 결과가 true라는 것은 검사한 타입으로 형변환이 가능하다는 것을 뜻한다. [[예제 7-17]](./InstanceofTest.java)  

<br/>

## 참조변수와 인스턴스의 연결

조상 클래스에 선언된 멤버변수와 같은 이름의 인스턴스변수를 자손 클래스에 중복으로 정의했을 때, 조상타입의 참조변수로 자손 인스턴스를 참조하는 경우와 자손타입의 참조변수로 자손 인스턴스를 참조하는 경우는 서로 다른 결과를 얻는다.  
메서드의 경우 참조변수의 타입에 관계없이 항상 실제 인스턴스의 메서드가 호출되지만, 멤버변수의 경우 참조변수의 경우 참조변수의 타입에 따라 달라진다.  

*static메서드는 static변수처럼 참조변수의 타입에 영향을 받는다. 참조변수의 타입에 영향을 받지 않는 것은 인스턴스메서드 뿐이다. 그래서 static메서드는 반드시 참조변수가 아닌 `클래스이름.메서드()`로 호출해야 한다.*  

멤버변수가 조상 클래스와 자손 클래스에 중복으로 정의된 경우, 조상타입의 참조변수를 사용했을 때는 조상 클래스에 선언된 멤버변수가 사용되고, 자손 타입의 참조변수를 사용했을 때는 자손 클래스에 선언된 멤버변수가 사용된다.  
하지만 중복 정의되지 않은 경우, 조상타입의 참조변수를 사용했을 때와 자손타입의 참조변수를 사용했을 때의 차이는 없다. 

- 멤버변수가 조상 클래스와 자손 클래스에 중복으로 정의된 경우 참조변수의 타입에 따라 달라진다. [[예제 7-18]](./BindingTest.java)  
- 자손 클래스에서 조상 클래스의 멤버를 중복으로 정의하지 않았을 때는 참조변수의 타입에 따른 변화는 없다. [[예제 7-19]](./BindingTest2.java)  
- 참조변수의 타입에 따라 사용되는 인스턴스변수가 달라질 수 있다. [[예제 7-20]](./BindingTest3.java)  

<br/>

## 매개변수의 다형성

- 매개변수의 참조변수 타입을 각 클래스 대신 조상 클래스로 한다. [[예제 7-21]](./PolyArgumentTest.java)  

<br/>

## 여러 종류의 객체를 배열로 다루기

- 조상타입의 참조변수 배열을 사용하면, 공통의 조상을 가진 서로 다른 종류의 객체를 배열로 묶어서 다룰 수 있다. [[예제 7-22]](./PolyArgumentTest2.java)  
- `Vector`클래스를 사용하면 배열의 크기를 알아서 관리해주기 때문에 저장할 인스턴스 개수에 신경쓰지 않아도 된다. [[예제 7-23]](./PolyArgumentTest3.java)  

<br/><br/>

# 6. 추상클래스(abstract class)

<br/>

## 추상클래스란?

추상클래스는 **미완성 설계도**와 같고, 이 추상클래스로는 **인스턴스를 생성할 수 없으며**, **상속을 통해서 자손클래스에 의해서만 완성**될 수 있다.  
추상클래스 자체로는 클래스로서의 역할을 다 못하지만, 새로운 클래스를 작성할 때 **틀**이 되어준다.  
추상클래스는 키워드 `abstract`를 붙이기만 하면 된다.  

```
 abstract class 클래스 이름 {
 	...
 }
```

*추상메서드를 포함하고 있지 않은 클래스도 인스턴스 생성을 막기 위해 키워드 'abstract'를 붙여서 추상클래스로 지정할 수도 있다.*  

<br/>

## 추상메서드(abstract Method)

선언부만 작성하고 구현부는 작성하지 않은 **미완성 메서드**가 추상메서드이다.  
메서드 내용이 상속받는 클래스에 따라 달라질 수 있기 때문에 조상 클래스에서는 선언부만 작성하고, 주석을 이용해 어떤 기능을 수행할 목적으로 작성되었는지 알려주고, 실제 내용은 상속받는 클래스에서 구현하도록 비워 두는 것이다.    

```
 abstract 리턴타입 메서드이름();
```

추상클래스로부터 상속받은 자손클래스는 오버라이딩을 통해 조상인 추상클래스의 추상메서드를 모두 구현해주어야 한다. 만일 하나라도 구현하지 않는다면, 자손 클래스 역시 추상클래스로 지정해 주어야 한다.  

<br/>

## 추상클래스의 작성

상속이 자손 클래스를 만드는데 조상 클래스를 사용하는 것이라면, 추상화는 기존의 클래스의 공통부분을 뽑아내서 조상 클래스를 만드는 것이라고 할 수 있다.  

상속계층도를 따라 내려갈수록 클래스는 점점 기능이 추가되어 구체화의 정도가 심해지며, 상속계층도를 따라 올라갈수록 클래스는 추상화의 정도가 심해진다고 할 수 있다. 즉, 상속계층도를 따라 내려갈수록 세분화되며, 올라갈수록 공통요소만 남게 되는 것이다.  

<br/><br/>

# 7. 인터페이스(interface)

<br/>

## 인터페이스란?

인터페이스는 추상클래스보다 추상화 정도가 높은 **기본 설계도**와 같다.    
오직 **추상메서드**와 **상수**만을 멤버로 가질 수 있으며, 그 외의 다른 어떠한 요소도 허용하지 않는다.  

<br/>

## 인터페이스의 작성

```
interface 인터페이스이름 {
	public static final 타입 상수이름 = 값;
	public abstract 메서드이름(매개변수목록);
}
```

- 모든 멤버변수는 `public static final`이어야 하며, 이를 생략할 수 있다.  
- 모든 메서드는 `public abstract`이어야 하며, 이를 생략할 수 있다. (*JDK1.8부터는 static메서드와 default메서드는 예외*)  

제어자를 생략하면 컴파일 시 컴파일러가 자동적으로 추가해준다. 

<br/>

## 인터페이스의 상속

인터페이스는 인터페이스로부터만 상속받을 수 있으며, 클래스와 달리 **다중상속**이 가능하다.  

*인터페이스는 Object클래스와 같은 최고 조상이 없다.*

```
interface Movable {
	void move(int x, int y);
}

interface Attackable {
	void attack(Unit u);
}

interface Fightable extends Movable, Attackable {}
```

<br/>

## 인터페이스의 구현

인터페이스도 그 자체로는 인스턴스를 생성할 수 없다.

```
class 클래스이름 implements 인터페이스이름 {
	// 인터페이스에 정의된 추상메서드 구현 
}
```

만일 구현하는 인터페이스의 메서드 중 일부만 구현한다면, `abstract`를 붙여서 추상메서드로 구현해야 한다.  

- [[예제 7-24]](./FighterTest.java)  

<br/>

## 인터페이스를 이용한 다형셩

인터페이스는 타입의 참조변수로 이를 구현한 클래스의 인스턴스를 참조할 수 있으며, 인터페이스 타입으로 형변환도 가능하다.  
예를들어, 인터페이스 Fightable을 클래스 Fighter가 구현했을 때, 다음과 같이 Fighter인스턴스를 Fightable타입의 참조변수로 참조하는 것이 가능하다.  

```
Fightable f = (Fightable)new Fighter();
Fightable f = new Fighter();
```

인터페이스는 메서드의 매개변수 타입으로도 사용될 수 있다. 

```
void attack(Fightable f) {}
```

인터페이스 타입의 매개변수가 갖는 의미는 메서드 호출 시 해당 인터페이스를 구현한 클래스의 인스턴스를 매개변수로 제공해야한다는 것이다.  

그리고 메서드의 리턴타입으로 인터페이스의 타입을 지정하는 것 역시 가능하다. 

```
Fightable method() {
	...
	Fighter f = new Fighter();
	return f;
}
```

**리턴타입이 인터페이스라는 것은 메서드가 해당 인터페이스를 구현한 클래스의 인스턴스를 반환한다는 것을 의미한다.** [[예제 7-25]](./ParserTest.java)  

<br/>

## 인터페이스의 장점

### 1. 개발시간을 단축시킬 수 있다.
인터페이스가 작성되면 메서드를 호출하는 쪽과 인터페이스를 구현하는 쪽 양쪽에서 동시에 개발을 진행할 수 있다.  

### 2. 표준화가 가능하다.
프로젝트에 사용되는 기본 틀을 인터페이스로 작성한 다음, 개발자들에게 인터페이스를 구현하게 하여 일관되고 정형화된 프로그램의 개발이 가능하다.  

### 3. 서로 관계없는 클래스들에게 관계를 맺어 줄 수 있다.
서로 상속관계도 아니고, 같은 조상클래스를 가지고 있지도 않은 클래스들에게 하나의 인터페이스를 공통적으로 구현하게 함으로써 관계를 맺어 줄 수 있다.

### 4. 독립적인 프로그래밍이 가능하다.
인터페이스를 이용하면 클래스의 선언과 구현을 분리시킬 수 있기 때문에 독립적인 프로그램을 작성하는 것이 가능하다.  
- 인터페이스를 이용해 기존의 상속체계를 유지하면서 공통점을 부여할 수 있다. [[예제 7-26]](./RepairableTest.java)  

<br/>

## 인터페이스의 이해

> 클래스를 사용하는 쪽(User)와 클래스를 제공하는 쪽(Provider)이 있다.  
> 메서드를 호출하는 쪽(User)에서는 사용하려는 메서드(Provider)의 선언부만 알면 된다.  

- 두 클래스가 서로 직접적인 관계에 있다. provider클래스가 변경되면 user클래스도 변경되어야 한다. [[예제 7-27]](./InterfaceTest.java)  
- 두 클래스가 서로 직접 호출하지 않고 인터페이스를 매개체로한다면, 클래스간 영향을 받지 않도록 하는 것이 가능하다. [[예제 7-28]](./InterfaceTest2.java)  
- 매개변수를 통해 인터페이스를 구현한 클래스의 인스턴스를 동적으로 제공받지 않고 제 3의 클래스를 통해 제공받을 수도 있다. [[예제 7-29]](./InterfaceTest3.java)   

<br/>

## 디폴트 메서드와 static 메서드
인터페이스에는 추상 메서드만 선언할 수 있다. 그러나 JDK1.8부터는 **default**메서드와 **static**메서드도 추가할 수 있게 되었다.  

### 1. 디폴트 메서드
디폴트 메서드는 **추상 메서드의 기본적인 구현을 제공하는 메서드**로, 추상메서드가 아니기 때문에 디폴트 메서드가 새로 추가되어도 해당 인터페이스를 구현한 클래스를 변경하지 않아도 된다. 디폴트 메서드는 키워드 `default`를 붙이며, 일반메서드처럼 몸통`{}`이 있어야 한다. 접근제어자 역시 `public`이며 생략가능하다.

```
interface MyInterface {
	void method();
	default void newMethod();	
}
```

- [[예제 7-30]](./DefaultMethodTest.java)  

<br/><br/>

# 8. 내부 클래스(inner class)

내부 클래스는 클래스 내에 선언된다는 점을 제외하고 일반적인 클래스와 다르지 않다.  

<br/>

## 내부 클래스란?

내부 클래스는 **클래스 내에 선언된 클래스**이다.  

- 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있다.  
- 코드의 복잡성을 줄일 수 있다. (캡슐화)  

```
class A {
	class B {
	
	}
}
```

<br/>

## 내부 클래스의 종류와 특징

### 1. 인스턴스 클래스 (instance class)
외부 클래스의 멤버변수 선언위치에 선언하며, 외부 클래스의 인스턴스멤버처럼 다루어진다.  
주로 외부 클래스의 인스턴스멤버들과 관련된 작업에 사용될 목적으로 선언된다.

### 2 스태틱 클래스 (static class)
외부 클래스의 멤버변수 선언위치에 선언하며, 외부 클래스의 static멤버처럼 다루어진다.
주로 외부 클래스의 static멤버, 특히 static메서드에서 사용될 목적으로 선언된다.

### 3. 지역 클래스 (local class)
외부 클래스의 메서드나 초기화 블럭 안에 선언하며, 선언된 영역 내부에서만 사용될 수 있다.

### 4. 익명 클래스 (anonymous class)
클래스의 선언과 객체의 생성을 동시에 하는 이름없는 클래스(일회용)

<br/>

## 내부 클래스의 선언

```
class Outer {
	// 인스턴스 클래스
	class InstatnceInner {}
	// 스태틱 클래스
	static class StaticInner{}
	
	void myMethod() {
		//지역 클래스
		class LocalInner{}
	}
}
```

<br/>

## 내부 클래스의 제어자와 접근정

- [[예제 7-31]](./InnerEx1.java)  
- [[예제 7-32]](./InnerEx2.java)
- [[예제 7-33]](./InnerEx3.java)
- [[예제 7-34]](./InnerEx4.java)
- [[예제 7-35]](./InnerEx5.java)

<br/>

## 익명 클래스(anonymous class)
익명 클래스는 다른 내부 클래스들과는 달리 이름이 없다. **클래스의 선언과 객체의 생성을 동시에**하기 때문에 **단 한번만 사용**될 수 있고 **오직 하나의 객체만을 생성**할 수 있는 **일회용 클래스**이다.

```
new 조상클래스이름() {

}

또는

new 구현인터페이스이름() {

}
```

이름이 없기 때문에 **생성자도 가질 수 없으며**, 조상클래스의 이름이나 구현하고자 하는 인터페이스의 이름을 사용해서 정의하기 때문에 하나의 클래스를 상속받는 동시에 인터페이스를 구현하거나 둘 이상이 인터페이스를 구현할 수 없다. 오로지 단 하나의 클래스를 상속받거나 단 하나의 인터페이스만을 구현할 수 있다.

- [[예제 7-36]](./InnerEx6.java)  
- [[예제 7-37]](./InnerEx7.java) -> (익명클래스 전환) -> [[예제 7-38]](./InnerEx8.java)  