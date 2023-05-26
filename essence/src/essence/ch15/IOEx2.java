package essence.ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * byte배열을 사용해서 한 번에 배열의 크기만큼 읽고 쓸 수 있다.
 */
class IOEx2 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		byte[] tmp = new byte[10];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		input.read(tmp, 0, tmp.length); 	// 읽어온 데이터를 배열 tmp에 담는다.
		output.write(tmp, 5, 5); 			// tmp[5]부터 5개의 데이터를 write한다.
		
		outSrc = output.toByteArray();
		
		System.out.println("input Source : " + Arrays.toString(inSrc));
		System.out.println("tmp Source : " + Arrays.toString(tmp));
		System.out.println("output Source : " + Arrays.toString(outSrc));
	}

}
