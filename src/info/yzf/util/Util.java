package info.yzf.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Util {
	
	private static final String str = "0123456789";
	private static final Random random = new Random();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final Timer timer = new Timer();
	
	public static String generateString(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; ++ i) {
			sb.append(str.charAt(random.nextInt(str.length())));
		}
		return sb.toString();
	}
	
	public static String formatTime(Timestamp time) {
		return sdf.format(time);
	}
	
	public static String formatDay(Timestamp time) {
		return sdf2.format(time);
	}
	
	private static boolean isDateValid(String date) throws Exception {
		return sdf.format(sdf.parseObject(date)).equals(date);
	}
	
	public static Timestamp convertToTimestamp(String date) throws Exception {
		if (! isDateValid(date)) {
			throw new Exception();
		}
		return new Timestamp(sdf.parse(date).getTime());
	}
	
	public static void runTask(TimerTask task, long delay, long period) {
		timer.scheduleAtFixedRate(task, delay, period);
	}
}
