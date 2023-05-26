# Chapter15. 입출력 I/O

<br/>

# 1. 자바에서의 입출력

## 입출력이란?

I/O란 **컴퓨터 내부 또는 외부의 장치와 프로그램간의 데이터를 주고받는 것**을 말한다.

<br/>

## 스트림(stream)

자바에서 입출력을 수행하려면, 두 대상을 연결하고 데이터를 전송할 수 있는 무언가가 필요한데 이것을 스트림(stream)이라고 정의했다.  

> **스트림이란 데이터를 운반하는데 사용되는 연결통로이다.**  

스트림은 **단방향통신**만 가능하기 때문에 하나의 스트림으로 입출력을 동시에 처리할 수 없다.  
그래서 입력과 출력을 동시에 수행하려면 **입력스트림(input stream)**과 출력을 위한 **출력스트림(output stream)**, 2개의 스트림이 필요하다.  
스트림은 **먼저 보낸 데이터를 먼저 받게 되어 있으며**, 중간에 건너뜀 없이 **연속적**으로 데이터를 주고받는다. 즉 큐(queue)와 같은 **FIFO(First In First Out)**구조로 되어있는 것이다.

<br/>

## 바이트기반 스트림 - InputStream, OutputStream

스트림은 **바이트단위**로 데이터를 전송하며, 입출력 대상에 따라 다음과 같은 입출력스트림이 있다.  

**FileInputStream - FileOutputStream** : 파일  
**ByteArrayInputStream - ByteArrayOutputStream** : 메모리(byte 배열)  
**PipedInputStream - PipedOutputStream** : 프로세스(프로세스간의 통신)  
**AudioInputStream - AudioOutputStream** : 오디오장치  

이들은 모두 InputStream 또는 OutputStream의 자손들이며, 각각 읽고 쓰는데 필요한 추상메서드를 구현해 놓은 것이다.  

<br/>

## 보조 스트림

보조스트림은 스트림의 기능을 보완하기 위해 제공된 것으로, 실제 데이터를 주고받는 스트림이 아니기 때문에 **데이터를 입출력할 수 있는 기능은 없지만, 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있다.**  
그래서 보조스트림만으로는 입출력을 처리할 수 없고, **스트림을 먼저 생성한 다음에 이를 이용해서 보조스트림을 생성해야 한다.**  

```
// 1. 기반스트림을 생성한다.
FileInputStream fis = new FileInputStream("test.txt");

// 2. 기반스트림을 이용해서 보조스트림을 생성한다.
BufferedInputStream bis = new BufferedInputStream(fis);

// 3. 보조스트림은 BufferdInputStream으로부터 데이터를 읽는다.
bis.read();
```

여기서 실제 입력 기능은 FileInputStream이 수행하고, 보조스트림인 BufferedInputStream은 버퍼만을 제공한다.  
버퍼를 사용한 입출력과 사용하지 않은 입출력간의 **성능 차이**는 상당하기 때문에 대부분의 경우 버퍼를 이용한 보조스트림을 사용한다.

<br/>

## 문자기반 스트림 - Reader, Writer

Java에서는 한 문자를 의미하는 char형이 2byte이기 때문에 바이트기반의 스트림으로 처리하는 데는 어려움이 있어서 문자기반의 스트림이 제공된다.  

**InputStream -----> Reader**  
**OutputStream ----> Writer**  

<br/><br/>

# 2. 바이트기반 스트림 

<br/>

## InputStream과 OutputStream

InputStream과 OutputStream은 모든 바이트기반 스트림의 조상이다.  

프로그램이 종료될 때, 사용하고 닫지 않은 스트림을 JVM이 자동적으로 닫아주기는 하지만, 스트림을 사용해서 모든 작업을 마치고 난 후에는 `close()`를 호출해서 반드시 닫아주어야 한다. 그러나 `ByteArrayInputStream`과 같이 메모리를 사용하는 스트림과 `System.in`, `System.out`과 같은 표준 입출력 스트림은 닫아주지 않아도 된다.  

<br/>

## ByteArrayInputStream과 ByteArrayOutputStream

ByteArrayInputstream/ByteArrayOutputStream은 **메모리**, 즉 바이트배열에 데이터를 입출력 하는데 사용되는 스트림이다.  
주로 다른 곳에 입출력하기 전에 데이터를 임시로 바이트배열에 담아서 변환 등의 작업을 하는데 사용된다.  

- ByteArrayInputStream/ByteArrayOutputStream을 이용해서 바이트배열 데이터를 복사한다. [[예제 15-1]](./IOEx1.java)
- byte배열을 사용해서 한 번에 배열의 크기만큼 읽고 쓸 수 있다. [[예제 15-2]](./IOEx2.java)
- write(byte[] b) : 배열 b에 저장된 모든 내용을 출력소스에 쓴다. [[예제 15-3]](./IOEx3.java)
- write(byte[] b, int off, int len) : 배열 b에 저장된 내용 중 off번째부터 len개 만큼만 출력소스에 쓴다. [[예제 15-4]](./IOEx4.java)

<br/>

## FileInputStream과 FileOutputStream

FileInputStream/FileOutputStream 은 **파일에 입출력을 하기 위한 스트림**이다.  

- 커맨드라인으로부터 입력받은 파일의 내용을 읽어서 그대로 화면에 출력한다. [[예제 15-5]](./FileViewer.java)
- 파일의 내용을 읽고(read) 출력(write)하여 그대로 복사한다. [[예제 15-6]](./FileCopy.java)

<br/><br/>

# 3. 바이트기반의 보조스트림

<br/>

## FilterInputStream과 FileterOutputStream

FilterInputStream/FileterOutputStream은 InputStream/OutputStream의 자손이면서 **모든 보조스트림의 조상**이다.  
보조스트림은 자체적으로 입출력을 수행할 수 없기 때문에 **기반스트림을 필요**로 한다.

```
protected FilterInputStream(InputStream in) // 인스턴스 생성 불가. 상속을 통해 오버라이딩 해야함
public FilterOutputStream(OutputStream out)
```

FilterInputStream/FileterOutputStream의 모든 메서드는 단순히 기반스트림의 메서들을 그대로 호출할 뿐이고, 자체적으로는 아무 것도 하지 않는다. 따라서 FilterInputStream/FileterOutputStream 상속을 통해 메서드를 오버라이딩 해야한다.

<br/>

## BufferedInputStream과 BufferdOutputStream

BufferedInputStream/BufferdOutputStream은 **스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림**이다.  
한 바이트씩 입출력하는 것 보다는 버퍼(바이트배열)를 이용해서 한 번에 여러 바이트를 입출력하는 것이 빠르기 때문에 대부분의 입출력 작업에 사용된다.  

- 보조스트림을 사용한 경우 기반스트림의 close()나 flush()를 호출할 필요 없이 보조스트림의 close()를 호출하기만 하면 된다. [[예제 15-7]](./BufferedOutputStreamEx1.java)

<br/>

## DataInputStream과 DataOutputStream






