package essence.ch15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 커맨드라인으로부터 입력받은 파일의 내용을 읽어서 그대로 화면에 출력한다.
 */
class FileViewer {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(args[0]);
		int data = 0;
		
		data = fis.read();
		while(data != -1) {
			/*
			 * read()의 반환값이 int형(4byte)이지만, 더 이상 입력값이 없음을 알리는 -1을 제외하고는 0~255(1byte)범위의 정수값이기 때문에, char형(2byte)로 변환해도 손실 값은 없다.
			 */
			char c = (char)data;
			System.out.println(c);
		}
	}

}
