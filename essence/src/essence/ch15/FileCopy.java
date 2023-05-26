package essence.ch15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일의 내용을 읽고(read) 출력(write)함으로써 그대로 복사한다. 
 */
class FileCopy {

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			
			int data = fis.read();
			
			while(data != -1) {
				fos.write(data);
			}
			
			fis.close();
			fos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
