# Chapter10. 날짜와 시간 & 형식화

<br/>

# 1. 날짜와 시간

## Calendar와 Date

Date는 JDK1.0부터 제공되어온 클래스이고 JDK1.1부터는 Date를 보완한 Calendar라는 클래스가 제공되었다. 기존의 Date 클래스보다 훨씬 나앗지만 아직 부족한 부분이 많았었다. 그래서 JDK1.8부터는 `java.time` 패키지로 기존의 단점들을 개선한 새로운 클래스들이 추가되었다. 

<br/> 

## Calendar와 GregorianCalendar

Calendar는 추상클래스이기 때문에, 직접 객체를 생성할 수 없고 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.  

```
Calendar cal = new Calendar();	// ERROR!!! 추상클래스는 인스턴스를 생성할 수 없음

Calendar cal = Calendar.getInstance();	// getInstance 메서드는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환한다.
```

Calendar를 상속받아 완전히 구현한 클래스로는 GregorianCalendar와 BuddhistCalendar가 있다.  
`getInstance()`는 **시스템의 국가와 지역설정을 확인**해서 태국인 경우에는 BuddhistCalendar의 인스턴스를 반환하고, 그 외에는 GregorianCalendar의 인스턴스를 반환한다.  
GragorianCalendar는 Calendar를 상속받아 오늘날 전세계 공통으로 사용하고 있는 그레고리력에 맞게 구현한 것으로 태국을 제외한 나머지 국가에서 사용하면 된다.  
이렇게 인스턴스를 직접 생성해서 사용하지 않고 메서드를 통해 인스턴스를 반환하게 하는 이유는 최소한의 변경으로 프로그램이 동작할 수 있도록 하기 위한 것이다.

아래와 같이 특정 인스턴스를 생성하도록 프로그램이 작성되어 있다면, 다른 종류의 역법을 사용하는 국가에서 실행하거나, 새로운 역법이 추가되는 경우, 즉 다른 종류의 인스턴스를 필요로 하는 경우에 MyApplication을 변경해야 한다.

```
class MyApplication {
	public static void main(String args[]) {
		Calendar cal = new GregorianCalendar();
	}
}
```

아래와 같이 메서드를 통해서 인스턴스를 얻어오도록 하면 getInstance()의 내용이 달라져야 하겠지만, 적어도 MyApplication을 변경하지 않아도 된다.  
getInstance()메서드가 static인 이유는 메서드 내의 코드에서 인스턴스 변수를 사용하거나 인스턴스 메서드를 호출하지 않기 때문이며, 또 다른 이유는 static이 아니라면 객체를 생성한 다음 호출해야 하는데 Calendar는 추상클래스이기 때문에 객체를 생성할 수 없기 때문이다.

```
class MyApplication {
	public static void main(String args[]) {
		Calendar cal = Calendar.getInstance();
	}
}
```

<br/>

## Date와 Calendar간의 변화

Calendar가 새로 추가되면서 Date는 대부분의 메서드가 `deprecated`되었으므로 잘 사용되지 않는다. 그럼에도 불구하고 Date를 필요로 하는 메서드들이 있는데 Calendar와 변환할 때 문제가 발생한다면 아래와 같이 하면 된다.

**Calendar를 Date로 변환**

```
Calendar cal = Calendar.getInstance();
...
Date d = new Date(cal.getTimeInMillis());	// Date(long date)
```

**Date를 Calendar로 변환**

```
Date d = new Date();
...
Calendar cal = Calendar.getInstance();
cal.setTime(d)
```


- getInstance() 메서드르 통해 얻은 인스턴스는 현재 시스템의 날짜와 시간에 대한 정보를 담고 있다. [[예제 10-1](./CalendarEx1.java)]  
- 날짜와 시간을 원하는 값으로 변경하려면 set메서드를 사용하면 된다. [[예제 10-2](./CalendarEx2.java)]  
	
	void set(int field, int value)
	void set(int year, int month, int date)
	void set(int year, int month, int date, int hourOfDay, int minute)
	void set(int year, int month, int date, int hourOfDay, int minute, int second)
	
- 두 개의 시간 데이터를 초 단위로 변환하여 차이를 구하고, 시분초로 바꿀 수 있다. [[예제 10-3](./CalendarEx3.java)]  
	**getTimeInMillis()** : 1/1000초 단위로 값을 반환  
- add 메서드와 roll 메서드 [[예제 10-4](./CalendarEx4.java)]  
	**add(int field, int amount)** : 원하는 만큼 증감시킬 수 있기 때문에 특정 날짜 또는 시간을 기점으로 일정기간 전후의 날짜와 시간을 알아낼 수 있다.  
	**roll(int filed, int amount)** : 값을 증감시킬 수 있지만, add메서드와 달리 다른 필드에 영향을 미치지 않는다.  
	예를 들어 add메서드는 날짜필드의 값을 31만큼 증가시키면 다음 달로 넘어가므로 월필드의 값도 1 증가하지만,  
	roll메서드는 월 필드의 값은 변하지 않고 일 필드의 값만 바뀐다.
- 단, 일 필드가 말일일 때, roll메서드를 이용하여 월 필드를 변경하면 일 필드에 영향을 미칠 수 있다. (add메서드도 마찬가지) [[예제 10-5](./CalendarEx5.java)]  
- 다음 달의 1일에서 하루를 빼면 이번 달의 마지막 일을 알 수 있다.[[예제 10-6](./CalendarEx6.java)]
- getActualMaximum()메서드를 사용해도 해당 월의 마지막 일을 알 수 있다. [[예제 10-7](./CalendarEx7.java)]
- 간단한 연산은 Calendar를 사용하지 않고 간단히 처리할 수도 있다. [[예제 10-8](./CalendarEx8.java)]
- [[예제 10-9](./CalendarEx9.java)]

<br/><br/>

# 2. 형식화 크래스

날짜를 형식에 맞게 출력하려면 Calendar클래스를 이용해서 년, 월, 일, 시, 분, 초를 각각 별도로 조합을 해야 한다. 자바에서는 이러한 문제들을 쉽게 해결할 수 있는 방법을 제공한다. 
이 형식화 클래스는 `java.text`패키지에 포함되어 있으며 숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화 하였다.
형식화 클래스는 형식화에 사용될 패턴을 정의하는데, 데이터를 정의된 패턴에 맞춰 형식화할 수 있을 뿐만 아니라 역으로 형식화된 데이터에서 원래의 데이터를 얻어낼 수도 있다.

## DecimalFormat

DecimalFormat은 **숫자를 형식화**하는 데 사용되는 클래스이다. 이를 이용하면 숫자 데이터를 정수, 부동소수점, 금액 등의 다양한 형식으로 표현할 수 있으며, 반대로 일정한 형식의 텍스트 데이터를 숫자로 쉽게 변환하는 것도 가능하다.  
형식화 클래스에서는 원하는 형식으로 표현 또는 변환하기 위해서 패턴을 정의한다.  
DecimalFormat을 사용하기 위해서 먼저 원하는 출력형식의 **패턴**을 작성하여 **DecimalFormat인스턴스를 생성**한 다음, 출력하고자 하는 **문자열로 format메서드를 호출**하면 원하는 패턴에 맞게 변환된 문자열을 얻을 수 있다.

```
double number = 1234567.89								
DecimalFormat df = new DecimalFormat("#.#E0");	// 1. 패턴으로 DecimalFormat 인스턴스 생성 
String result = df.format(number);					// 2. 출력하고자하는 문자열로 format()메서드 호출
```

- 다양한 숫자 패턴 예시 [[예제 10-10](./DecimalFormatEx1.java)]  
- parse()메서드를 이용하면 기호와 문자가 포함된 문자열을 숫자로 쉽게 변환할 수 있다. [[예제 10-11](./DecimalFormatEx2.java)]  

<br/>

## SimpleDateFormat

Date와 Calendar만으로 **날짜 데이터**를 원하는 형태로 다양하게 출력하는 것은 복잡하다. 이럴 때 SimpleDateFormat을 사용하면 된다.  
참고로 `DateFormat`은 추상클래스로 `SimpleDateFormat`의 조상이고, 인스턴스를 생성하기 위해서는 `getDateInstance()`와 같은 static 메서드를 이용한다. 이 메서드에 의해 반환되는 것은 DateFormat을 상속받아 완전하게 구현한 SimpleDateFormat인스턴스이다.  
SimpleDateFormat을 사용하기 위해서 먼저 원하는 출력형식의 **패턴**을 작성하여 **SimpleDateFormat인스턴스를 생성**한 다음, 출력하고자 하는 **Date인스턴스를 가지고 format메서드를 호출**하면 지정한 출력형식에 맞게 변환된 문자열을 얻게 된다.  

```
Date today = new Date();
SimpleDateFormat df = new SimpleDateFormate("yyyy-MM-dd");	// 1. 패턴으로 SimpleDateFormat 인스턴스 생성 
String result = df.format(today);								// 2. 출력하고자하는 Date 인스턴스로 format()메서드 호출
```

- 다양한 날짜 패턴 예시 [[예제 10-12](./DateFormatEx1.java)]  
- Date인스턴스만 format메서드에 사용될 수 있기 때문에 Calendar인스턴스를 Date인스턴스로 변환해야 한다.(`getTime()`메서드) [[예제 10-13](./DateFormatEx2.java)]  
- parse(String source)를 사용하여 문자열source를 날짜Date인스턴스로 쉽게 변환할 수 있다. [[예제 10-14](./DateFormatEx3.java)]  
	parse는 SimpleDateFormat의 조상인 `DateFormat`에 정의되어 있으며, 지정된 형식과 입력된 형식이 일치하지 않은 경우에는 예외가 발생하므로 적절한 `예외처리`가 필요하다.
- [[예제 10-15](./DateFormatEx4.java)] 

<br/>

## ChoiceFormat

**특정 범위에 속하는 값을 문자열로 변환**해준다. 연속적 또는 불연속적인 범위의 값들을 처리하는 데 있어서 if문이나 switch문은 적절하지 못하는 경우가 많다. 이 때 ChoiceFormat을 잘 사용하면 간단하게 만들 수 있다.

- 경계값의 범위와 치환할 문자열 사용 [[예제 10-16](./ChoiceFormatEx1.java)] 
- 패턴을 사용 [[예제 10-17](./ChoiceFormatEx2.java)] 
	**limit#value** : 경계값을 범위에 포함시킴  
	**limit<value** : 경계값을 범위에 포함시키지 않음

<br/>

## MessageFormat

데이터를 **정해진 양식**에 맞게 출력할 수 있도록 도와준다. 데이터가 들어갈 자리를 마련해 놓은 양식을 미리 작성하고 프로그램을 이용해서 다수의 데이터를 같은 양식으로 출력할 대 사용하면 좋다.

- [[예제 10-18](./MessageFormatEx1.java)] 
- 다수의 데이터를 변환하기 [[예제 10-19](./MessageFormatEx2.java)] 
- parse메서드를 이용하여 데이터 출력하기 [[예제 10-20](./MessageFormatEx3.java)] 
- 파일로부터 받기 [[예제 10-21](./MessageFormatEx4.java)] 

<br/><br/>

# 3. java.time 패키지

Date와 Calendar가 가지고 있던 단점들을 해소하기 위해 JDK1.8부터 `java.time`패키지가 추가되었다.  
이 패키지에 속한 클래스들의 가장 큰 특징은 String클래스처럼 **불변(immutable)**이라는 것이다. 그래서 날짜나 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 항상 변경된 새로운 객체를 반환한다. 기존 Calendar클래스는 변경 가능하므로, 멀티 쓰레드 환경에서 안전하지 못하다.  
멀티 쓰레드 환경에서는 동시에 여러 쓰레드가 같은 객체에 접근할 수 있기 때문에, 변경 가능한 객체는 데이터가 잘못될 가능성이 있으며, 이를 쓰레드에 안전하지 않다고 한다.

## java.time 패키지의 핵심 클래스

날짜와 시간을 하나로 표현하는 Calendar클래스와 달리, 날짜와 시간을 별도의 클래스로 분리해 놓았다.  
시간을 표현할 때는 `LocalTime`클래스를 사용하고, 날짜를 표현할 때는 `LocalDate`클래스를 사용한다. 그리고 날짜와 시간이 모두 필요할 때는 `LocalDateTime`클래스를 사용하면 된다. 더 나아가 시간대까지 다뤄야 한다면 `ZonedDateTime`클래스를 사용하면 된다.  

### 1. Pareiod와 Duration

**Pareiod** : 두 날짜의 차이를 표현하기 위함 (날짜 - 날짜)  
**Duration** : 두 시간의 차이를 표현하기 위함 (시간 - 시간)

### 2. 객체 생성하기 - now(), of()

java.time패키지에 속한 클래스의 객체를 생성하는 가장 기본적인 방법은 `now()`와 `of()`를 사용하는 것이다.  
**now()** : 현재 날짜와 시간을 저장하는 객체를 생성한다.  
**of()** : 해당 필드의 값을 순서대로 지정해주기만 하면 된다.  

```
LocalDate date = LocalDate.now();					// 2022-11-06	
LocalTime time = LocalTime.now();					// 19:06:32.875
LocalDateTime dateTime = LocalDateTime.now();		// 2022-11-06T19:06:32.875
ZonedDateTime dateTimeInKr = ZonedDateTime.now(); // 2022-11-06T19:06:32.875+09:00[Asia/Seoul]

LocalDate date = LocalDate.of(2022, 11, 06);		// 2022년 11월 06일
LocalTime time = LocalTime.of(23, 59, 59);			// 23시 59분 59초
LocalDateTime dateTime = LocalDateTime.of(date, time);
ZonedDateTime zDateTime = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Seoul"));
```

### 3. Temporal과 TemporalAmount

LocalDate, LocalTime, LocatDateTime, ZonedDateTime등 날짜와 시간을 표현하기 위한 클래스들은 모두 Temporal, TemporalAccesstor, TemporalAdjuster인터페이스를 구현했고, Duration과 Period는 TemporalAmount인터페이스를 구현하였다. 

### 4. TemporalUnit과 TemporalField

**날짜와 시간의 단위**를 정의해 놓은 것이 `TemporalUnit`인터페이스이고, 이 인터페이스를 구현한 것이 열거형 `ChronoUnit`이다. 그리고 `TemporalField`는 **년, 월, 일 등 날짜와 시간의 필드을 정의**해 놓은 것으로, 열거형 `ChronoField`가 이 인터페이스를 구현하였다.

```
LocalTime now = LocalTime.now();	// 현재 시간
int minute = now.getMinute();	// 현재시간에서 분만 뽑아낸다.
int minute = now.get(ChronoField.MINUTE_OF_HOUR);	// 위와 같다.
```

날짜와 시간에서 특정 필드의 값만을 얻을 때는 `get()`이나 `get`으로 시작하는 이름의 매서드를 이용한다. 그리고 아래와 같이 특정 날짜와 시간에서 지정된 단위의 값을 더하거나 뺄 때는 `plus()` 또는 `minus()`에 값과 함께 ChronoUnit을 사용한다.

```
LocalDate today = LocalDate.now();		// 오늘
LocalDate tomorrow = today.plus(1, ChoronoUnit.DAYS);	// 내일
LocalDate tomorrow = today.plusDays(1);	// 위와 동일
```

<br/>

## LocalDate와 LocalTime

LocalDate와 LocalTime은 java.time패키지의 가장 기본이 되는 클래스이며, 나머지 클래스들은 이들의 확장이므로 이 두 클래스만 잘 이해하면 나머지는 쉬워진다.  
객체를 생성하는 방법은 현재의 날짜와 시간을 LocalDate와 LocalTime으로 각각 반환하는 `now()와` 지정된 날짜와 시간으로  LocalDate와 LocalTime객체를 생성하는 `of()`가 있다.

```
LocalDate today = LocalDate.now();		// 오늘
LocatTime now = LocalTime.now();			// 현재 시간

LocalDate birthDate = LocalDate.of(1999, 12, 31);		// 1999년 12월 31일
LocalTime birthTime = LocalTime.of(23, 59, 59);		// 23시 59분 59초
```

### 1. 특정 필드의 값 가져오기 - get(), getXXX()

대부분의 필드는 int타입의 범위에 속하지만, 몇몇 필드는 int타입의 범위를 벗어날 수 있다.  
그럴 때 get()대신 `getLong()`을 사용해야 한다.

```
int get (TemporalField field)
int getLong(TemporalField field)
```

### 2. 필드의 값 변경하기 - with(), plus(), minus()  

날짜와 시간에서 특정 필드 값을 변경하려면 with로 시작하는 메서드를 사용하면 된다.

```
LocalDate with(TemporalField field, long newValue)

LocalDate withYear(int year)
LocalDate withMonth(int month)
LocalDate withDayOfMonth(int dayOfMonth)
LocalDate withDayOfYear(int dayOfYear)

LocalTime withHour(int hour)
LocalTime withMinute(int minute)
LocalTime withSecond(int second)
LocalTime withNano(int nanoOfSecond)
```

특정 필드에 값을 더하거나 뺄 때는 plus()와 minus() 메서드를 사용하면 된다.

```
LocalTime plus(TemporalAmount amountToAdd)
LocalTime plus(long amountToAdd, TemporalUnit unit)
LocalDate plus(TemporalAmount amountToAdd)
LocalDate plus(long amountToAdd, TemporalUnit unit)
```

plus()로 만든 메서드들도 있다.

```
LocalDate plusYears(long yearsToAdd)
LocalDate plusMonths(long monthsToAdd)
LocalDate plusDays(long daysToAdd)
LocalDate plusWeeks(long weeksToAdd)

LocalTime plusHours(long hoursToAdd)
LocalTime plusMinutes(long minutesToAdd)
LocalTime plusSeconds(long secondsToAdd)
LocalTime plusNanos(long nanosToAdd)
```

그리고 LocalTime의 `truncatedTo()`는 지정된 것보다 작은 단위의 필드를 0으로 만든다.

```
LocalTime time = LocalTime.of(12, 34, 56);		// 12시 34분 56초
time = time.truncatedTo(ChronoUnit.HOURS);		// 시(hour)보다 작은 단위를 0으로
System.out.println(time);							// 12:00
```

LocalTime과 달리 LocalDate에는 년, 월, 일이 0이 될 수 없기 때문에 `truncatedTo()`메서드가 없다.  

### 3. 날짜와 시간의 비교 - isAfter(), isBefore(), isEqual()

LocalDate와 LocalTime도 `compareTo()`가 적절히 오버라이딩되어 있어서, `compareTo()`메서드로 비교할 수 있다.  

```
int result = date1.compareTo(date2);	// 같으면 0, date1이 이전이면 -1, 이후면 1
```

보다 편리하게 비교할 수 있는 메서드들이 추가로 제공된다.

```
boolean isAfter (ChronoLocalDate other)
boolean isBefore (ChronoLocalDate other)
boolean isEqual (ChronoLoacalDate other)	// LocalDate에만 있음
```

equals() 메서드가 있는데도 isEqual()메서드를 제공하는 이유는 연표(chronology)가 다른 두 날짜를 비교하기 위해서이다.  
모든 필드가 일치해야하는 equals()메서드와 달리 isEquals()는 오직 날짜만 비교한다. 그래서 대부분의 경우 equals()와 isEqual()의 결과가 같다.

```
LocalDate kDate = LocalDate.of(1999, 12, 31);
JapaneseDate jDate = JapanesDate.of(1999, 12, 31);

System.out.println(kDate.equals(jDate));		// false YEAR_OF_ERA가 다름
System.our.println(kDate.isEqual(jDate));		// true
```

- [[예제 10-22](./NewTimeEx1.java)] 

<br/>

## Instant

Instant는 에포크 타임(EPOCH TIME, 1970-01-01 00:00:00 UTC)부터 경과된 시간을 나노초 단위로 표현한다.  
사람에겐 불편하지만, 사람이 사용하는 날짜와 시간은 여러 진법이 섞여있어서 계산하기 어려운 반면, 에포크 타임은 단일 진법으로만 다루기 때문에 계산하기 쉽다.  

Instant를 생성할 때는 `now()`와 `ofEpochSecond()` 메서드를 사용한다.  

```
Instant now = Instant.now();
Instant now2 = Instant.ofEpochSecond(now.getEpochSecond());
Instant now3 = Instant.ofEpochSecond(now.getEpochSecond(), now.getNano());
```

그리고 필드에 저장된 값을 가져올 때는 다음과 같이 한다.  

```
long epochSec = now.getEpochSecond();
long nano = now.getNano();
```

Instant는 시간을 초 단위와, 나노초 단위로 나누어 저장한다.  
오라클 데이터베이스의 타임스탬프(timestamp)처럼 밀리초 단위의 EPOCH TIME을 필요로 하는 경우를 위해 `toEpochMilli()`메서드가 정의되어있다.

```
long toEpochMilli()
```

Instant는 항상 UTC(+00:00)를 기준으로 하기 때문에, LocalTime과 차이가 있을 수 있다.  
예를 들어 한국은 시간대가 '+09:00'이므로 Instant와 LocalTime간에는 9시간의 차이가 있다. 시간대를 고려해야 하는 경우 `OffsetDateTime`을 사용하는 것이 좋을 수 있다.  

UTC는 'Coordinated Universal Time'의 약어로 '세계 협정시'이라고 하며, 1972년 1월 1일부터 시행된 국제 표준시이다.  

### 1. Instant와 Date간의 변환

Instant는 기존의 `java.util.Date`를 대체하기 위한 것이며, JDK1.8부터 Date에 Instant로 변환할 수 있는 새로운 메서드가 추가되었다.

```
static Date from(Instant instant)		// Instant -> Date
Instant toInstant()							// Date -> Instant
```

<br/>

## LocalDateTime과 ZonedDateTime

LocalDate와 LocalTime을 합쳐놓은 것이 LocalDateTime이고,  
LocateDateTime에 시간대(time zone)을 추가한 것이 ZonedDateTime이다.  

### 1. LocalDate와 LocalTime으로 LocalDateTime 만들기  

LocalDate와 localTime으로 하나의 LocalDateTime을 만들 수 있다.  

```
LocalDate date = LocalDate.of(2015, 12, 31);
LocalTime time = LocalTime.of(12, 34, 56);

LocalDateTime dt = LocalDateTime.of(date, time);
LocalDateTime dt2 = date.atTime(time);
LocalDateTime dt3 = time.atDate(date);
LocalDateTime dt4 = date.atTime(12, 34, 56);
LocalDateTime dt5 = time.atDate(LocalDate.of(2015, 12, 31));
LocalDateTime dt6 = date.atStartOfDay();	// date.atTime(0, 0, 0); 과 같음
```

물론 LocalDateTime에도 날짜와 시간을 직접 지정할 수 있는 다양한 버전의 `of()`와 `now()`가 정의되어 있다.

```
LocalDateTime dateTime = LocalDateTime.of(2015, 12, 31, 12, 34, 56);
LocalDateTime today = LocalDateTime.now();
```

### 2. LocalDateTime의 변환 

반대로 LocalDateTime을 LocalDate 또는 LocalTime으로 변환할 수 있다. 

```
LocalDateTime dt = LocalDateTime.of(2015, 12, 31, 12, 34, 56);
LocalDate date = dt.toLocalDate();
LocalTime time = dt.toLocalTime();
```

### 3. LocalDateTime으로 ZonedDateTime 만들기

LocalDateTime에 시간대(time-zone)을 추가하면, ZonedDateTime이 된다.  
기존에는 `TimeZone`클래스로 시간대를 다뤘지만, 새로운 시간 패키지에서는 `ZoneID`라는 클래스를 사용한다.  
ZoneId는 일광 절약시간(DST, Daylight Saving Time)을 자동적으로 처리해주므로 더 간편하다.  
LocalDateTime에 `atZone()`으로 시간대 정보를 추가하면 ZonedDateTime을 얻을 수 있다.

```
ZoneId zid = ZoneId.of("Asia/Seoul");
zonedDateTime zdt = dateTime.atZoned(zid);
System.out.println(zdt);	// 2022-11-12T17:47:50.451+09:00[Asia/Seoul]
```

LocalDate에 `atStartOfDay()`라는 메서드가 있는데, 이 메서드에 매개변수로 ZoneId를 지정해도 ZonedDateTime을 얻을 수 있다.

```
zonedDateTime zdt = LocalDate.now().atStartOfDay(zid);
System.out.println(zdt);	// 2022-11-12T00:00+09:00[Asia/Seoul]
```

현재 특정 시간대의 시간, 예를 들어 뉴욕을 알고 싶다면 다음과 같이 하면 된다.  
now()대신 of()를 사용하면 날짜와 시간을 지정할 수 있다.

```
zoneId nyId = ZoneId.of("America/New_York");
zonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);
```

### 4. ZoneOffset

UTC로부터 얼마만큼 떨어져 있는지를 ZoneOffSet으로 표현한다.  
서울은 '+9'이다. 즉, UTC시간보다 9시간(32400초=60*60*9초)이 바르다.

```
zoneOffset krOffset = zonedDateTime.now().getOffset();
// zoneOffset krOffset = zoneOffset.of("+9");
int krOffsetInSec = krOffset.get(ChronoField.OFFSET_SECONDS); // 32400초
```

### 5. OffsetDateTime

ZonedDateTime은 ZoneId로 구역을 표현하는데, ZoneId가 아닌 ZoneOffset을 사용한다.  
ZoneId는 일광절약시간처럼 시간대와 관련된 규칙들을 포함하고 있는데, ZoneOffset은 단지 시간의 차이로만 구분한다.  
컴퓨터에게 일광절약시간처럼 계절별로 시간을 더했다 뺐다하는 것은 위험하다. 아무런 변화 없이 일관된 시간체계를 유지하는 것이 더 안전한다.  
같은 지역 내의 컴퓨터 간 데이터를 주고받을 때, 전송시간을 표현하기에 LocalDateTime이면 충분하겠지만, 서로 다른 시간대에 존재하는 컴퓨터간 통신에는 `OffsetDateTime`이 필요하다.  

OffsetDateTime은 LocalDate와 LocalTime에 ZoneOffset을 더하거나, ZonedDateTime에 toOffsetDateTime()을 홈출해서 얻을 수도 있다.

```
ZonedDateTime zdt = ZonedDateTime.of(date, time, zid);
OffsetDateTime odt = OffsetDateTime.of(date, time, krOffset);

// ZonedDateTime -> OffsetDateTime
offsetDateTime odt = zdt.toOffsetDateTime();
```

### 6. ZonedDateTime의 변환

ZonedDateTime도 LocalDateTime처럼 날짜와 시간에 관련된 다른 클래스로 변환하는 메서드들을 가지고 있다.

```
LocalDate toLocalDate()
LocalTime toLocalTime()
LocalDateTime toLocalTime()
OffsetDateTime toOffsetDatTime()
long toEpochSecond()
Instant toInstant()
```

GregorianCalendar와 가장 유사한 것이 ZonedDateTime이다. 

```
// ZOnedDateTime -> GregorianCalendar
GregorianCalendar from(ZonedDateTime zdt)

// GregorianCalendar -> ZonedDateTime
ZonedDateTime toZonedDateTime()
```

- [[예제 10-23](./NewTimeEx2.java)]

<br>

## TemporalAdjusters

시간은 plus(), minus()메서드로 간단히 계산할 수 있지만, 지난 주 토요일이 며칠인지, 이번 달의 3번째 금요일은 며칠인지와 같은 날짜계산은 불편하다.  
그래서 **자주 쓰일만한 날짜 계산**들을 대신 해주는 메서드를 정의해놓은 것이 `TemporalAdjusters`클래스이다.  

```
LocalDate today = LocalDate.now();
LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
```

### 1. TemporalAdjuster 직접 구현하기

필요하면 자주 사용되는 날짜계산을 해주는 메서드를 직접 만들 수도 있다.  
LocalDate의 with()는 다음과 같이 정의되어있으며, TemporalAdjuster인터페이스를 구현한 클래스의 객체를 매개변수로 제공해야 한다.

```
LocalDate with(TemporalAdjuster adjuster)
```

TemporalAdjuister인터페이스는 다음과 같이 추상 메서드 하나만 정의되어 있으며, 이 메서드만 구현하면 된다.

```
@FunctionInterface
public interface TemporalAdjuster {
	Temporal adjustInto(Temporal temporal);
}
```

실제로 구현해야 하는 것은 `adjustIngo()`지만, 우리가 TemporalAdjuster와 같이 사용해야 하는 메서드는 `with()`이다.  
with()와 adjustInto() 중에서 어느 쪽을 사용해도 되지만, adjustInto()는 내부적으로만 사용할 의도로 작성된 것이기 때문에, with()를 사용하도록 하는 것이 좋다.  

날짜와 시간에 관련된 대부분의 클래스는 Temporal인터페이스를 구현하였으므로 adjustInto()의 매개변수가 될 수 있다.  
예를 들어, 특정 날짜부터 2일 후의 날짜를 계산하는 DayAfterTomorrow는 다음과 같이 작성할 수 있다.

```
class DayAfterTomorrow implements TemporalAdjuster {
	@Override
	public Temporal ajustInto(Temporal temporal) {
		return temporal.plus(2, ChronoUnit.DAYS);
	}
}
```

- [[예제 10-24](./NewTimeEx3.java)]

<br/>

## Period와 Duration

```
날짜 - 날짜 = Period
시간 - 시간 = Duration
```

### 1. between()
두 날짜 간 차이를 나타내는 Period는 `between()`메서드로 얻을 수 있다.  
date1이 date2보타 날짜상으로 이전이면 양수로, 이후면 음수로 Period에 저장된다.  

```
LocalDate date1 = LocalDate.of(2022, 1, 1);
LocalDate date2 = LocalDate.of(2022, 12, 31);

Period pe = Period.between(date1, date2);
```

그리고 시간차이를 구할 때는 Duration을 사용한다는 것을 제외하고는 Period와 똑같다.

```
LocalTime time1 = LocalTime.of(00, 00, 00);
LocalTime time2 = LocalTime.of(12, 34, 56);

Duration du = Duration.between(time1, time2);
```

Period, Duration에서 특정 필드의 값을 더을 때는 `get()`메서드를 사용한다.  

```
long year = pe.get(ChronoUnit.YEARS);
long month = pe.get(ChronoUnit.MONTHS);
long day = pe.get(ChronoUnit.DAYS);

long sec = du.get(ChronoUnit.SECONDS);
int nano = du.get(ChronoUnit.NANOS);
```

그런데, Period와 달리 Duration에는 `getHours()`, `getMinites()`같은 메서드가 없다.  

```
System.out.println(pe.getUnits());	// [Years, Months, Days]
System.out.println(du.getUnits());	// [Seconds, Nanos]
```

Duration에는 Chrono.SECONDS와 Chrono.NANOS밖에 사용할 수 없다.  
Duration을 LocalTime으로 변환한 다음에, LocalTime이 갖고 있는 get메서드를 사용하면 시간과 분을 구할 수 있다.

```
LocalTime tmpTime = LocalTime.of(0, 0).plusSeconds(du.getSeconds));

int hour = tmpTime.getHour();
int min = tmpTime.getMinute();
int sec = tmpTime.getSecond();
int nano = du.getNano();
```

### 2. between()과 until()

until()메서드는 between()과 거의 같은 일을 한다.  
`between()`은 **static메서드**이고, `until()`은 **인스턴스 메서드**라는 차이가 있다.  

```
// Period pe = Period.bteween(today, myBirthDay);
Period pe = today.until(myBirthDay);
long dday = today.until(myBirthDay, ChronoUnit.DAYS);
```

Period는 년월일을 분리해서 저장하기 때문에, D-day를 구하는 경우 두 개의 매개변수를 받는 until()을 사용하는 것이 낫다.

### 3. of(), with()

Period에는   `of()`, `ofYears()`, `ofMonths()`, `ofWeeks()`, `ofDays()`가 있고,  
Duration에는 `of()`, `ofDays()`, `ofHours()`, `ofMinutes()`, ofSeconds()` 등이 있다. 

```
Period pe = Period.of(2022, 12, 31);					// 2022년 12개월 31일
Duration du = Duration.of(60, ChronoUnit.SECONDS);	// 60초
```

특정 필드의 값을 변경하는 with()도 있다.

```
pe = pe.withYears(2020);		// 2022년에서 2020년으로 변경
du = du.withSeconds(120);		// 60초에서 120초로 변경
```

### 4. 사칙연산, 비교연산, 기타메서드

plus(), minus()외에 곱셈과 나눗셈을 위한 메서드도 있다.  

```
pe = pe.minusYears(1).multipliedBy(2);		// 1년을 빼고, 2배를 곱한다.
du = du.plusHours(1).dividedBy(60);			// 1시간을 더하고 60으로 나눈다.
```

Period에는 나눗셈을 위한 메서드가 없는데, Period는 날짜 기간을 표현하기 위한 것이므로 나눗셈을 위한 메서드가 별로 유용하지 않기 때문이다.  
그리고 음수인지 확인하는 `isNagative()`와 0인지 확인하는 `isZero()`가 있다.  
두 날짜 또는 시간을 비교할 때, 사용하면 어느 쪽이 앞인지 또는 같은지 알아낼 수 있다.

```
boolean sameDate = Period.between(date1, date2).isZero();
boolean isBefore = Duration.between(time1, time2).isNegative();
```

부호를 반대로 변경하는 `negate()`와 부호를 없애는 `abs()`가 있다.  
아래 양쪽의 코드는 동일하다. Period에는 abs()메서드가 없어서 아래와 같은 코드를 사용해야 한다.

```
du = du.abs();

if(du.isNegative())
	du = du.negate();
```

Perioddpsms `normalized()`라는 메서드가 있는데, 이 메서드는 월(month)의 값이 12를 넘지 않게, 즉 1년 13개월을 2년 1개월로 바꿔준다.  
일(day)의 길이는 일정하지 않으므로 그대로 놔둔다.

```
pe = Period.of(1, 13, 32).normalized();		// 1년 13개월 32일 -> 2년 1개월 32일
```

### 5. 다른 단위로 변환 - toTotalMonths(), toDays(), toHours(), toMinutes()

이름이 'to'로 시작하는 메서드들은 Period와 Duration을 다른 단위의 값으로 변환하는데 사용된다.  
LocalDate의 `toEpochDay()`라는 메서드는 Epoch Day인 '1970-01-01'부터 날짜를 세어서 반환하므로, 이 메서드를 이용하면 Period를 사용하지 않고도 두 날짜간의 일수를 편리하게 계산할 수 있다.  

```
LocalDate date1 = LocalDate.of(2022, 11, 12);
LocalDate date2 = LocalDate.of(2022, 11, 14);

long period = date2.toEpochDay() - date1.toEpochDay();	// 1
```

- [[예제 10-25](./NewTimeEx4.java)]

<br/>

## 파싱과 포맷

형식화(formatting)과 관련된 클래스들은 `java.time.format`패키지에 들어있는데, 이 중에서 `DateTimeFormatter`가 핵심이다.  
이 클래스에는 자주 쓰이는 다양한 형식들을 기본적으로 정의하고 있으며, 그 외의 형식이 필요하다면 직접 정의해서 사용할 수도 있다.  
  
날짜와 시간의 형식화는 `format()`이 사용되는데, 이 메서드는 DateTimeFormatter뿐만 아니라 LocalDate나 LocalTime같은 클래스에도 있다.  
같은 기능을 하니 상황에 따라 편한 쪽을 선택해서 사용하면 된다.

```
LocalDate date = LocalDate.of(2022, 01, 02);

String yyyymmdd = DateTimeFormatter.ISO_LOCAL_DATE.format(date);	// "2022-01-02"
String yyyymmdd = date.format(DateTimeFormatter.ISO_LOCAL_DATE);	// "2022-01-02"
```

### 1. 로케일에 종속된 형식화

DateTimeFormatter의 static메서드 `ofLocalizedDate()`, `ofLocalizedTime()`, `ofLocalized DateTime()`은 로케일(locale)에 종속적인 포맷터를 생성한다.

```
DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
String shortFormat = formatter.format(LocalDate.now());
```

### 2. 출력형식 직접 정의하기

DateTimeFormatter의 `ofPattern()`으로 원하는 출력형식을 직접 작성할 수도 있다.

```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
```

- [[예제 10-26](./DateFormatterEx1.java)]

### 3. 문자열을 날짜와 시간으로 파싱하기

문자열을 날짜 또는 시간으로 변환하려면 static메서드인 `parse()`를 사용하면 된다.  
날짜와 시간을 표현하는데 사용되는 클래스에는 이 메서드가 거의 다 포함되어 있다.  
parse()는 오버로딩된 메서드가 여러 개 있는데 그 중에서 이 2개가 자주 쓰인다.

```
static localDateTime parse(CharSequence text)
static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter)
```

DateTimeFormatter에 상수로 정의된 형식을 사용할 때는 다음과 같이 한다.

```
LocalDate date = LocalDate.parse("2022-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
```

자주 사용되는 기본적인 형식의 문자열은 ISO_LOCAL_DATE와 같은 형식화 상수를 사용하지 않고도 파싱이 가능하다.

```
LocalDate newDate = LocalDate.parse("2022-01-03");
LocalTime newTime = LocalTime.parse("12:30:40");
LocalDateTime newDateTime = LocalDateTime.parse("2022-03-01T23:20:12");
```

다음과 같이 ofPattern()을 이용해서 파싱을 할 수도 있다.

```
DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
LocalDateTime endOfYear = LocalDateTime.parse("2022-01-04 23:12:34", pattern);
```

- [[예제 10-27](./DateFormatterEx2.java)]