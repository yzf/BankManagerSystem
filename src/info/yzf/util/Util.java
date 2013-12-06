package info.yzf.util;

import info.yzf.database.model.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Util {
	
	private Util() {}
	
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
	
	public static String formatTime(Date time) {
		return sdf.format(time);
	}
	
	public static String formatDay(Timestamp time) {
		return sdf2.format(time);
	}
	
	private static boolean isDateValid(String date) throws Exception {
		return sdf.format(sdf.parseObject(date)).equals(date);
	}
	
	private static Timestamp convertToTimestamp(String date) throws Exception {
		try {
			if (! isDateValid(date)) {
				throw new Exception(Message.DateFormat);
			}
			return new Timestamp(sdf.parse(date).getTime());
		} catch (Exception e) {
			throw new Exception(Message.TimeFormat);
		}
	}
	
	public static Timestamp getToday(String date) throws Exception {
		return convertToTimestamp(date);
	}
	
	public static Timestamp getDayAfterDays(String date, int day) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(getToday(date));
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertToTimestamp(formatTime(c.getTime()));
	}
	
	public static Timestamp getDayBeforeDays(String date, int day) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(getToday(date));
		c.add(Calendar.DAY_OF_MONTH, -day);
		return convertToTimestamp(formatTime(c.getTime()));
	}
	
	public static Timestamp getDayAfterMonths(String date, int month) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(getToday(date));
		c.add(Calendar.MONTH, month);
		return convertToTimestamp(formatTime(c.getTime()));
	}
	
	public static Timestamp getDayAfterYears(String date, int year) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(getToday(date));
		c.add(Calendar.YEAR, year);
		return convertToTimestamp(formatTime(c.getTime()));
	}
	
	public static void runTask(TimerTask task, long delay, long period) {
		timer.scheduleAtFixedRate(task, delay, period);
	}
	
	public static Map<String, Integer> countLogType(Vector<Log> log) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Log l : log) {
			String key = l.getType();
			if (map.get(key) == null) {
				map.put(key, 0);
			}
			map.put(key, map.get(key) + 1);
		}
		return map;
	}
	
	public static Timestamp getEnd(String date, int type, int cnt) throws Exception {
		if (type == 0) {
			return getDayAfterDays(date, 1);
		}
		if (type == 1) {
			return getDayAfterMonths(date, 1);
		}
		if (type == 2) {
			return getDayAfterMonths(date, 3 * cnt);
		}
		if (type == 3) {
			return getDayAfterYears(date, 1);
		}
		throw new Exception(Message.UnknownType);
	}
	
	public static String formatEndTime(Timestamp end) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(end);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return sdf2.format(c.getTime());
	}
}
