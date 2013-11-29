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
		Timestamp time = Util.getDayAfterMonths("2013-10-31 17:55:10", 1);
		System.out.print(Util.formatTime(time));
	}
}
