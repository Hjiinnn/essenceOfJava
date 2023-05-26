package essence.ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * ByteArrayInputStream/ByteArrayOutputStream  을 이용해서 바이트배열 데이터를 복사한다.
 * read()와 write()를 사용하기 때문에 한 번에 1byte만 읽고 쓰므로 작업 효율이 떨어진다.
 * 
 * 바이트배열은 사용하는 자원이 메모리 밖에 없으므로 가비지컬렉터에 의해 자동적으로 자원을 반환하기 때문에 close()를 이용해서 스트림을 닫지 않아도 된다.
 */
class IOEx1 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		
		/*
		 *	read() : 1byte를 읽어 온다.(0~255) 더 이상 읽어 올 데이터가 없으면 -1을 반환한다.
		 *	write() : 주어진 값을 출력소스에 쓴다. 
		 */
		while((data = input.read()) != -1) {
			output.write(data);
		}
		
		outSrc = output.toByteArray();
		
		System.out.println("Input Source : " + Arrays.toString(inSrc));
		System.out.println("Output Source : " + Arrays.toString(outSrc));
	}

}
