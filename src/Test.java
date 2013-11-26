import info.yzf.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;



public class Test {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.print(Util.formatDay(time));
	}
}
