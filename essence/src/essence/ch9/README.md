# Chapter09. java.lang패키지와 유용한 클래스

<br/>

# 1. java.lang 패키지

## Object클래스

Object클래스는 멤버변수는 없고 오직 11개의 메서드만 가지고 있다.  

### 1. equals(Object obj)
매개변수로 객체의 참조변수를 받아서 비교하여 그 결과를 boolean값으로 리턴해준다.  
즉, Object클래스로부터 상속받은 equals메서드는 결국 두 개의 참조변수가 **같은 객체를 참조하고 있는지**, **참조변수에 저장된 값(주소값)**이 같은지를 판단하는 기능을 한다.

```
public boolean equals(Object obj) {
	return (this == obj);
}	
```

