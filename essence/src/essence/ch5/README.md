# Cahpter05. 배열 

<br/>

# 1. 배열(array)

## 배열(array)이란?

> **같은 타입의 여러 변수를 하나의 묶음으로 다루는 것**  

여기서 중요한 것은 **같은 타입**이어야 한다는 것이며, 서로 다른 타입의 변수들로 구성된 배열은 만들 수 없다.  

변수 대신 배열을 이용하면 다뤄야할 데이터의 수가 아무리 많아도 단지 배열의 길이만 바꾸면 되기 때문에 간단히 처리할 수 있다.  
또한, 변수와 달리 배열은 각 저장공간이 연속적으로 배치되어있다는 특징이 있다.

```
int[] score = new int[5];
```

값을 저장할 수 있는 공간은 score[0]부터 score[4]까지 모두 5개이며, 변수 score는 배열을 다루는데 필요한 참조변수일 뿐 값을 저장하기 위한 공간은 아니다. 

<br/>

## 배열의 선언과 생성

### 1. 배열의 생성
배열을 선언한 다음에는 배열을 생성해야한다. 배열을 선언하는 것은 단지 생성된 배열을 다루기 위한 참조변수의 공간이 만들어질 뿐이고, 배열을 생성해야만 비로소 값을 저장할 수 있는 공간이 만들어지는 것이다. 배열을 생성하기 위해서는 연산자 `new`와 함께 배열의 타입과 길이를 지정해 주어야 한다.  

```
타입[] 변수이름;			// 1. 배열 선언 (배열을 다루기 위한 참조변수 선언)
변수이름 = new 타입[길이]	// 2. 배열 생성 (실제 저장공간 생성)
```

배열을 생성하게 되면 연산자 `new`에 의해 메모리의 빈 공간에 데이터를 저장할 수 있는 공간이 마련된다.

<br/>

## 배열의 길이와 인덱스

생성된 배열의 각 저장공간을 **배열의 요소(element)**라고 하며, `배열이름[인덱스]`의 형식으로 요소에 접근할 수 있다.  
인덱스(index)는 배열의 요소마다 붙여진 일련번호로 각 요소를 구별하는데 사용된다.  

> 인덱스(index)의 범위는 **0부터 배열길이-1**까지  

배열에 값을 저장하고 읽어오는 방법은 변수와 같다.  

```
score[3] = 100;			// 배열 score의 4번째 요소에 100을 저장한다.
int value = score[3];	// 배열 score의 4번째 요소에 저장된 값을 읽어서 value에 저장한다.	
```

배열의 또 다른 장점은 index로 상수 대신 변수나 수식도 사용할 수 있다는 것이다.  
배열을 다룰 때 한 가지 주의할 점은 index를 벗어난 값을 index로 사용하지 않아야 한다는 것이다.  
변수의 값은 실행 시에 대입되므로 컴파일러는 이 값의 범위를 확인할 수 없다. 유효하지 않은 값을 index로 사용하면, 무사히 컴파일을 마쳤더라도 실행 시에 에러(ArrayIndexOutOfBoundsException)가 발생한다.

- 예제 [[예제 5-1](./ArrayEx1.java)]  

### 1. 배열의 길이
배열을 생성할 때 배열의 길이를 정해줘야 하는데, 배열의 길이는 배열의 요소 개수, 즉 **값을 저장할 수 있는 공간의 개수**다. 
배열의 길이는 양의 정수이어야 하며 최대값은 int타입의 최대값, 약 20억이다. 그런데 길이가 0인 배열도 생성 가능하다. 길이가 0이라는 것은 값을 저장할 수 있는 공간이 하나도 없다는 뜻이지만 불가능한 일은 아니다.  

> 배열의 길이는 int범위의 양의 정수(0도 포함)이어야 한다.  

### 2. 배열이름.length
자바에서는 JVM이 모든 배열의 길이를 별도로 관리하며, `배열이름.length`를 통해서 배열의 길이에 대한 정보를 얻을 수 있다.  

```
int[] arr = new int[5];
int tmp = arr.length;
```

배열은 한번 생성하면 **길이를 변경할 수 없기** 때문에, `배열이름.length`는 **상수**다.  

```
int[] arr = new int[5];
arr.length = 10;	// ERROR!!! 배열의 길이는 변경할 수 없음!!
```

### 3. 배열의 길이 변경하기
배열은 한번 선언되고 나면 길이를 변경할 수 없다. 배열에 저장할 공간이 부족하다면??  
이땐 더 큰 길이의 새로운 배열을 생성한 다음, 기존의 배열에 저장된 값들을 새로운 배열에 복사하면 된다.

> **배열의 길이를 변경하는 방법 : **  
> 1. 더 큰 배열을 새로 생성한다.  
> 2. 기존 배열의 내용을 새로운 배열에 복사한다.  

하지만 이런 작업들은 비용ㅇ이 많이 들기 때문에 처음부터 배열의 길이를 넉넉하게 잡아주는 것이 더 낫다. 그렇다고 배열의 길이를 너무 크게 하면 메모리를 낭비하게 되므로, 기존의 2배정도의 길이로 생성하는 것이 좋다.  

<br/>

## 배열의 초기화

배열은 생성과 동시에 자동적으로 자신의 타입에 해당하는 기본값으로 초기화되므로 배열을 사용하기 전에 따로 초기화를 해주지 않아도 되지만, 원하는 값을 저장하려면 각 요소마다 값을 지정해 줘야한다.  

저장할 값들은 괄호 안에 쉼표로 구분해서 나열하면, 값의 개수에 의해 배열의 길이가 자동적으로 결정되며, `new 타입[]`은 생략할 수도 있는데 생략할 수 없는 경우도 있다..

```
int[] score = new int[]{50, 60, 70. 80. 90};
int[] score = {50, 60, 70. 80. 90};

// new 타입[]을 생략할 수 없는 경우
result = {10, 40};	// ERROR!!! 배열의 선언과 생성을 따로하는 경우
int result = add({10, 40});	// ERROR!!! 메서드를 호출할때 파라미터로 사용되는 경우
```

그리고 괄호 안에 아무것도 넣지 않으면 길이가 0인 배열이 생성된다. 참조변수의 기본 값은 null이지만, 배열을 가리키는 참조변수는 null대신 길이가 0인 배열로 초기화하기도 한다.  

```
int[] score = new int[0];
int[] score = new int[]{};
int[] score = {};
```

### 1. 배열의 출력
for문을 사용하여 배열의 요소들을 출력할 수도 있지만 `Arrays.toString(배열이름)`메서드를 사용하면 배열의 모든 요소를 `[첫번째 요소, 두번째 요소, ...]`와 같은 형태의 문자열로 만들어서 반환한다. 

참조변수를 바로 출력하면 `타입@주소`형태로 출력이 되기 때문에 얻을 정보가 없지만 char배열의 경우엔 각 요소가 구분자 없이 그대로 출력된다. [[예제 5-2](./ArrayEx2.java)]  

<br/>

## 배열의 복사

배열을 복사하는 방법은 두 가지가 있다.  

### 1. for문을 이용해 복사하는 방법 

```
int[] arr = new int[5];
int[] tmp = new int[arr.length+2];

for(int i=0; i<arr.length; i++) {
	tmp[i] = arr[i];
}	

arr = tmp; // 참조변수 arr이 새로운 배열을 가리키게 한다.
```

*배열은 참조변수를 통해서만 접근할 수 있기 때문에, 자신을 가리키는 참조변수가 없는 배열은 사용할 수 없다. 이렇게 쓸모없게 된 배열은 JVM의 가비지컬렉터에 의해 자동적으로 메모리에서 제거된다.*

- 예제 [[예제 5-3](./ArrayEx3.java)] 

### 2. System.arraycopy()를 이용한 배열 복사

for문 대신 `System`의 `arrycopy()`메서드를 사용하면 간단하고 빠르게 배열을 복사할 수 있다.  
for문은 배열의 요소 하나하나에 접근해서 복사하지만, arraycopy()메서드는 **지정된 범위의 값들을 한 번에 통째로 복사한다.**  
각 요소들이 연속적으로 저장되어 있다는ㄷ 배열의 특성때문에 이렇게 처리하는 것이 가능한 것이다.  

- 예제 [[예제 5-4](./ArrayEx4.java)]

<br/>

## 배열의 활용

- 총합과 평균 [[예제 5-5](./ArrayEx5.java)]  
- 최대값과 최소값 [[예제 5-6](./ArrayEx6.java)]  
- 섞기(shuffle) [[예제 5-7](./ArrayEx7.java)], [[예제 5-8](./ArrayEx8.java)]  
- 임의의 값으로 배열 채우기 [[예제 5-9](./ArrayEx9.java)]  
- 정렬하기(sort) [[예제 5-10](./ArrayEx10.java)]
- 빈도수 구하기 [[예제 5-11](./ArrayEx11.java)]  

<br/><br/>

# 2. String배열

<br/>

## String배열의 선언과 생성

```
String[] name = new String[3];
```

3개의 String타입의 참조변수를 저장하기 위한 공간이 마련되고 참조형 변수의 기본값은 null이므로 각 요소의 값은 `null`로 초기화된다.

<br/>

## String배열의 초기화

```
String name[] = new String[]{"kim", "park", "Yi"};
String[] name = {"kim", "park", "Yi"};

name[0] = new String("kim");
name[1] = "Park";
```

기본형 배열이 아닌 참조형 배열의 경우 배열에 저장되는 것은 객체의 주소이다. 이런 참조형 배열을 객체 배열이라고도 한다.  

- 예제 [[예제 5-12](./ArrayEx12.java)]  
- 16진수를 2진수로 변환하기 [[예제 5-13](./ArrayEx13.java)]  

<br/>

## char배열과 String클래스

자바에서 char배열이 아닌 String클래스를 이용해서 문자열을 처리하는 이유는 String클래스가 char배열에 여러 가지 기능을 추가하여 확장한 것이기 때문이다.  
그래서 char배열을 사용하는 것보다 String클래스를 사용하는 것이 문자열을 다루기 더 쉽다. 

char배열과 String클래스의 한 가지 중요한 차이는 String객체는 읽을 수만 있을 뿐 내용을 변경할 수 없다는 것이다.

<br/>

## char배열과 String클래스의 변환

- charAt메서드와 toCharArray [[예제 5-14](./ArrayEx14.java)]  
- 문자열을 모르스부호로 변환하기 [[예제 5-15](./ArrayEx15.java)]  

<br/>

## 커맨드 라인을 통해 입력받기

Scanner클래스의 nextLine()외에도 사용자로부터 값을 입력받을 수 있는 간단한 방법이 바로 커맨드 라인을 이용한 것이다.  
프로그램을 실행할 때 클래스 이름 뒤에 공백문자로 구분하여 여러 개의 문자열을 프로그램에 전달할 수 있다.  

```
C:\jdk1.8\wordk\ch5>java MainTest abc 123
```

이렇게 커맨드라인을 통해 입력된 두 문자열은 String배열에 담겨서 MainTest클래스의 main메서드의 매개변수(args)에 전달된다. 그리고 main메서드 내에서 args[0], args[1]과 같은 방식으로 커맨드라인으로부터 전달받은 문자열에 접근할 수 있다.

커맨드라인에 입력된 매개변수는 공백문자로 구분하기 때문에 입력될 값에 공백이 있는 경우 큰따옴표로 감싸주어야 한다. 그리고 커맨드라인에서 숫자를 입력해도 숫자가 아닌 문자열로 처리된다는 것에 주의해야한다.  
그리고 커맨드라인에 매개변수를 입력하지 않으면 크기가 0인 배열이 생성되어 args, length의 값은 0이 된다. 

- 예제 [[예제 5-16](./ArrayEx16.java)]  
- 예제 [[예제 5-17](./ArrayEx17.java)]  

<br/><br/>

# 3. 다차원 배열

<br/>

## 2차원 배열의 선언과 인덱스

```
int[][] score;
int score[][];
int[] score[];

int[][] score = new int[4][3];
```

### 1. 2차원 배열의 index
2차원 배열은 행(row)과 열(column)으로 구성되어 있기 때문에 index도 행과 열에 각각 하나씩 존재한다.

```
score[0][0] = 100;	// 1행 1열에 100을 저장
```

<br/>

## 2차원 배열의 초기화

1차원 배열보다 괄호를 한번 더 써서 행별로 구분해 준다.

```
int[][] arr = new int[][]{{1,2,3}, {4,5,6}};
int[][] arr = {{1,2,3}, {4,5,6}};
```

- 예제 [[예제 5-18](./ArrayEx18.java)]  
- 예제 [[예제 5-19](./ArrayEx19.java)]  

<br/>

## 가변 배열

2차원 이상의 다차원 배열을 생성할 때 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않고, 추후에 각기 다른 길이의 배열을 생성함으로써 유동적인 가변 배열을 구성할 수 있다.  

```
int[][] score = new int[5][];
score[0] = new int[3];
score[1] = new int[4];
...
```

<br/>

## 다차원 배열의 활용

- 좌표에 X표하기 [[예제 5-20](./MultiArrayEx1.java)]  
- 빙고 [[예제 5-21](./MultiArrayEx2.java)]  
- 행렬의 곱셈 [[예제 5-22](./MultiArrayEx3.java)]  
- 단어 맞추기 [[예제 5-23](./MultiArrayEx4.java)]

