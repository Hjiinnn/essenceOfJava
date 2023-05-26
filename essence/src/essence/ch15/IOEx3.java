package essence.ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

class IOEx3 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		System.out.println("Input Source : " + Arrays.toString(inSrc));
		
		try {
			while(input.available() > 0) {	// available() : 블락킹(blocking)없이 스트림으로부터 읽어 올 수 있는 데이터의 크기 반환
				System.out.println(input.available());
				input.read(temp);			// read() : 배열의 크기만큼 읽어서 배열을 채우고, 읽어 온 데이터의 수를 반환한다. (반환 값은 항상 배열의 크기보다 작거나 같다.)
				output.write(temp);			// write() : 배열에 저장된 모든 내용을 출력소스에 쓴다.
				System.out.println("temp : " + Arrays.toString(temp));
				
				outSrc = output.toByteArray();	// toByteArray() : byteArray로 반환한다.
				printArrays(temp, outSrc);
			} 
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static void printArrays(byte[] temp, byte[] outSrc) {
		System.out.println("temp : " + Arrays.toString(temp));
		System.out.println("Output Source : " + Arrays.toString(outSrc));
	}

}
