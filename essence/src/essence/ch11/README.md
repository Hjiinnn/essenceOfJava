# Chapter11. 컬렉션 프레임웍

<br/>

# 1. 컬렉션 프레임웍(Collections Framework)

**데이터 군을 저장하는 클래스들을 표준화한 설계**  
- 컬렉션(Collection) : **다수의 데이터, 데이터 그룹**  
- 프레임웍 : ** 표준화된 프로그래밍 방식** 

<br/>

## 컬렉션 프레임웍의 핵심 인터페이스

컬렉션 프레임웍에서는 컬렉션데이터 그룹을 크게 3가지로 나누어 각 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의하였다.  

### List
- 순서가 있는 데이터의 집합
- 데이터의 중복 허용  
- ex) 대기자 명단  
- `ArrayList`, `LinkedList`, `Stack`, `Vector`

### Set
- 순서를 유지하지 않는 데이터의 집합  
- 데이터의 중복을 허용하지 않음  
- ex) 양의 정수집합, 소수의 집합  
- `HashSet`, `TreeSet`

### Map
- 키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합  
- 순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값은 중복을 허용한다.  
- ex) 우편번호, 지역번호  
- `HashMap`, `TreeMap`, `Hashtable`, `Properties`

<br/>

## ArrayList

ArrayList는 `Object`배열을 이용해서 데이터를 **순차적**으로 저장한다.  
그리고 배열에 더 이상 저장할 공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로운 배로 복사한 다음에 저장된다.  

- 메서드 활용 [[예제 11-1]](./ArrayListEx1.java)
- 문자열 자르기 [[예제 11-2]](./ArrayListEx2.java)
- Vector의 용량(capacity)와 크기(size) [[예제 11-3]](./VectorEx1.java)
- 메서드 동작 원리 [[예제 11-4]](./MyVector.java)

<br/>

## LinkedList

배열은 크기를 변경할 수 없고, 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸린다는 단점이 있다. 이러한 배열의 단점을 보완하기 위해 나온 것이 `링크드 리스트(linked list)`이다.  
배열은 모든 데이터가 연속적으로 존재하지만, 링크드 리스트는 **불연속적**으로 존재하는 데이터를 서로 **연결(link)**한 형태로 구성되어 있다.  

링크드 리스트의 각 요소(node)들은 **자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성되어 있다.** 

```
class Node {
	Node next;	// 다음 요소의 주소를 저장
	Object obj;	// 데이터를 저장
}
```

링크드 리스트에서의 데이터 삭제는 배열 복사를 하는 배열에 비해 간단하고 처리속도가 빠르다.  
삭제하고자 하는 요소의 이전요소가 삭제하고자 하는 요소의 다음 요소를 참조하도록 변경하기만 하면 된다. 
<br/>
링크드 리스트는 이동방향이 **단방향**이기 때문에 다음 요소에 대한 접근은 쉽지만, 이전요소에 대한 접근은 어렵다.  
이 점을 보완한 것이 `더블 링크드 리스트(이중 연결리스트, doubly linked list)`이다.  

```
class Node {
	Node next;	// 다음 요소이 주소를 저장
	Node previous; // 이전 요소의 주소를 저장
	OBject obj;	// 데이터를 저장
}
```

더블 링크드 리스트의 접근성을 보다 향상시킨 것이 `더블 써큘러 링크드 리스트(이중 원형 연결리스트, doubly circular linked list)`이다.  
이것은 단순히 더블 링크드 리스트의 첫 번째 요소와 마지막 요소를 서로 연결시킨 것이다.  
- ArrayList와 LinkedList의 데이터 추가 및 삭제 시 소요 시간 테스트 [[예제 11-5]](./ArrayListLinkedListTest.java)
- 데이터에 순차적으로 접근하는 것은 LinkedList보다 ArrayList의 속도가 더 빠르다. [[예제 11-6]](./ArrayListLinkedListTest2.java)

<br/>

## Stack과 Queue

- **Stack** : 마지막에 저장한 데이터를 가장 마지막에 꺼낸다. `LIFO(Last In First Out)`  
- **Queue** : 처음에 저장한 데이터를 가장 먼저 꺼낸다. `FIFO(First In First Out)` 

자바에서는 스택을 `Stack`클래스로 구현하여 제공하고 있지만, 큐는 Queue인터페이스로만 정의해 놓았을 뿐 별도의 클래스를 제공하고 있지 않다.  
대신 Queue인터페이스를 구현한 클래스들이 있다.
- [[예제 11-7]](./StackQueueEx.java)

### 1. Stack 직접 구현하기
Stack은 컬렉션 프레임웍 이전부터 존재했던 것이기 때문에 ArrayList가 아닌 `Vector`로부터 상속받아 구현하였다.
- [[예제 11-8]](./MyStack.java)

### 2. Stack와 Queue의 활용
- 웹브라우저의 '뒤로', '앞으로' 구현 [[예제 11-9]](./StackEx1.java)
- 입력한 수식의 괄호가 올바른지 체크 [[예제 11-10]](./ExpValidCheck.java)
- 유닉스의 history명령어를 Queue를 이용하여 구현 [[예제 11-11]](./QueueEx1.java)

### 3. PriorityQueue
Queue인터페이스의 구현체 중 하나로, **저장한 순서에 관계없이 우선순위(priority)가 높은 것부터 꺼내게 된다는 특징**이 있다.  
그리고 null은 저장할 수 없다. null을 저장하면 `NullPointerException`이 발생한다.  
- PriorityQueue는 각 요소를 자료구조 힙(heap)형태로 저장하기 때문에 저장한 순서와 다르게 저장되어 있다. [[예제 11-12]](./PriorityQueueEx.java)

### 4. Deque(Double - Ended Queue)
한 쪽 끝으로만 추가/삭제할 수 있는 Queue와 달리, Deque(덱, 또는 디큐)은 **양쪽 끝에 추가/삭제**가 가능하다. 

<br/>

## Iterator, ListIterator, Enumeration

### 1. Iterator
컬렉션 프레임웍에서는 **컬렉션에 저장된 각 요소에 접근하는 기능**을 가진 Iterator인터페이스를 정의하여 요소들을 읽어오는 방법을 표준화했다.  
`iterator`는 `Collection`인터페이스에 정의된 메서드이므로 Collection인터페이스의 자손인 `List`와 `Set`에도 포함되어있다.  
그래서 컬렉션 클래스에 대해 `iterator()`를 호출하여 `Iterator`를 얻은 다음 반복문을 사용해서 컬렉션 클래스의 요소들을 읽어올 수 있다. 

```
Collection c = new ArrayList();
Iterator it = c.iterator();

while(it.hasNext()) {
	System.out.println(it.next());
}
```

Map인터페이스를 구현한 컬렉션 클래스는 키와 값을 쌍으로 저장하기 때문에 iterator()를 직접 호출할 수 없다.  
대신 `keySet()`이나 `entrySet()`같은 메서드를 통해 키와 값을 각각 따로 `Set`의 형태로 얻어 온 후에 다시 `iterator()`를 호출하면 된다.

```
Map map = new HashMap();
Iterator it = map.entrySet().iterator();
```

List클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해서 읽어 온 결과 역시 저장 순서와 동일하지만, Set클래스들은 순서가 유지되지 않기 때문에 Iterator로 읽어 와도 처음에 저장된 순서와 같지 않다.
   
- [[예제 11-13]](./IteratorEx1.java)

### 2. ListIterator와 Enumeration
- Enumeration : `Iterator`의 구버전 (컬렉션 프레임웍이 만들어지기 이전에 사용)  
- ListIterator : `Iterator`를 상속받아서 기능을 추가한 것으로, `List`인터페이스를 구현한 컬렉션에 한해 **양방향**이동이 가능함  

- [[예제 11-14]](./ListIteratorEx1.java)
- [[예제 11-15]](./IteratorEx2.java)
- remove()는 읽어온 값이 있어야 호출될 수 있다. [[예제 1  1-16]](./MyVector2.java)
- [[예제 11-17]](./MyVector2Test.java)

<br/>

## Arrays

### 1. 배열의 복사 - copyOf(), copyOfRange()
- `CopyOf()` : 배열 전체 복사
- `CopyOfRange()` : 배열 일부 복사하여 새로운 배열 반환

```
int[] arr = {0, 1, 2, 3, 4};

int[] arrr2 = Arrays.copyOf(arr, arr.length);		// arr2 = [0, 1, 2, 3, 4]
int[] arrr3 = Arrays.copyOf(arr, 3);				// arr3 = [0, 1, 2]
int[] arrr4 = Arrays.copyOfRange(arr, 2, 4);		// arr4 = [2, 3]
```

### 2. 배열 채우기 - fill(), setAll()
- `fill()` : 배열의 모든 요소를 지정된 값으로 채운다.
- `setAll()` : 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받는다.

```
int[] arr = new int[5];

Arrays.fill(arr, 9); 	// arr = [9, 9, 9, 9, 9]
Arrays.setAll(arr, () -> (int)(Math.random()*5)+1);	// arr = [1, 5, 2, 1, 1]
```

### 3. 배열의 정렬과 검색 - sort(), binarySearch()
- `sort()` : 배열을 정렬한다.
- `binarySearch()` : 배열에 지정된 값이 저장된 위치(index)를 찾아서 반환한다. (반드시 배열이 정렬된 상태여야 함)

```
int[] arr = {3, 2, 0, 1, 4};

int idx = Arrays.binarySearch(arr, 2);		// idx = -5 (배열 정렬을 하지 않아 잘못된 결과 반환)

Arrays.sort(arr);		// 배열 arr을 정렬한다.
System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4]
int idx = Arrays.binarySerach(arr, 2);		// idx = 2
```

### 4. 배열의 비교와 출력 - equals(), toString()
- `toString()` : 배열의 모든 요소를 문자열로 출력한다.
- `deepToString()` : 다차원 배열을 출력한다. 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원 뿐만아니라 3차원 이상의 배열에도 동작한다.

```
int[] arr = {0, 1, 2, 3, 4};
int[][] arr2D = {{11, 12}, {21, 22}};

System.out.println(Arrays.toString(arr)); 		// [0, 1, 2, 3, 4]
System.out.println(Arrays.deepToString(arr2D)); 	// [[11, 12], [21, 22]]
```

- `equals()` : 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false반환
- `deepEquals()` : 다차원 배열의 비교에서 사용

```
String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

System.out.println(Arrays.equals(str2D, str2D2));		// false
System.out.println(Arrays.deepEquals(str2D, str2D2));	// true
```

### 5. 배열을 List로 변환 - asList(Object... a)
- `asList()` : 배열을 List에 담아서 반환

```
List list = Arrays.asList(new Integer[]{1,2,3,4,5});		// list = [1, 2, 3, 4, 5]

list.add(6);		// ERROR! UnsuppoertedOperationException발생
```

*asList()가 반환한 List의 크기는 변경할 수 없음 (추가 및 삭제 불가)

- 배열 예제 [[예제 11-18]](./ArraysEx.java)

<br/>

## Comparator와 Comparable
컬렉션을 **정렬**하는데 필요한 메서드를 정의하고 있는 인터페이스  

- `Comparable` : 기본 정렬기준을 구현하는데 사용(오름차순)
- `Comparator` : 기본 정렬 외에 다른 기준으로 정렬하고자할 때 사용(내림차순 등)

- [[예제 11-19]](./ComparatorEx.java)

<br/>

## HashSet
`Set`인터페이스를 구현한 컬렉션으로, 중복된 요소를 저장하지 않는다.  

- [[예제 11-20]](./HashSetEx1.java)
- 로또번호 만들기 [[예제 11-21]](./HashSetLotto.java)
- 빙고 만들기 [[예제 11-22]](./Bingo.java)
- [[예제 11-23]](./HashSetEx3.java), [[예제 11-24]](./HashSetEx4.java)
- [[예제 11-25]](./HashSetEx5.java)

<br/>

## TreeSet
**이진 검색트리(binary search tree)**자료구조 형태로 데이터를 저장하는 컬렉션 클래스이다.  
`Set`인터페이스를 구현하였기 때문에 중복을 허용하지 않고, 저장순서를 유지하지도 않는다.  
이진트리(binary tree)는 **루트(root)**노드부터 시작하여 여러 개의 **노드(node)**가 서로 연결된 구조로, 각 노드에 최대 2개의 노드를 연결할 수 있다.  
이진검색트리(binary search tree)는 부모노드의 왼쪽에는 부모노드의 값보다 작은 값의 자식노드를, 오른쪽에는 큰 값의 자식노드를 저장한다.  

- [[예제 11-26]](./TreeSetLotto.java)
- subSet() : 범위검색 [[예제 11-27]](./TreeSetEx1.java)
- [[예제 11-28]](./AsciiPrint.java)
- headSet(), tailSet() : 지정된 기준 값보다 작거나 큰 객체 검색 [[예제 11-29]](./TreeSetEx2.java)

<br/>

## HashMap과 Hashtable

HashMap은 `Entry`라는 **내부 클래스**를 정의하고, 다시 `Entry`타입의 **배열**을 선언하고 있다.  
HashMap은 key나 value로 **null**을 허용하지만, HashTable은 null을 허용하지 않는다. 그래서 `map.put(null, null)`이나, `map.get(null)`이 가능하다.  

- [[예제 11-30]](./HashMapEx1.java)
- [[예제 11-31]](./HashMapEx2.java)
- [[예제 11-32]](./HashMapEx3.java)
- [[예제 11-33]](./HashMapEx4.java)

### 해싱과 해시함수
- 해싱 : **해시함수(hash function)를 이용해서 데이터를 해시테이블(hash table)에 저장하고 검색**하는 기법  
해싱에서 사용하는 자료구조는 **배열**과 **링크드 리스트**조합으로 이루어져 있다.  
저장할 데이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 값은 다시 그 곳에 연결되어 있는 링크드 리스트에 저장하게 된다.

1. 검색하고자 하는 값의 `key`로 해시함수를 호출한다.  
2. 해시함수의 계산결과(해시코드)로 해당 값이 저장되어 있는 `링크드 리스트`를 찾는다.  
3. 링크드 리스트에서 검색한 키와 일치하는 데이터를 찾는다.  

<br>

## TreeMap
이진검색트리의 형태로, 검색과 정렬에 적합한 컬렉션 클래스이다.
- [[예제 11-34]](./TreeMapEx1.java)

<br/>

## Properties
HashMap의 구버전인 Hashtable을 상속받아 구현한 것으로, **(String, String)**형태로 값을 저장한다.

- [[예제 11-35]](./PropertiesEx1.java)
- [[예제 11-36]](./PropertiesEx2.java)
- [[예제 11-37]](./PropertiesEx3.java)
- [[예제 11-38]](./PropertiesEx4.java)

<br>

## Collections

- [[예제 11-39]](./CollectionsEx.java)

