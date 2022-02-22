package etc.bda.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static void main(String[] args) {
		Date d = new Date(2021, 9, 2);
//		System.out.println(compareDatesFromToday(d));
		System.out.println(showDateDifference(new Date(2021, 9, 1), new Date(2021, 9, 2)));
	}

	public static String showDateDifference(Date date, Date toCompare_date) {
		String result = "";
		// SimpleDateFormat converts the
		// string format to date object
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// Try Block
		try {

			// parse method is used to parse
			// the text from a string to
			// produce the date
			Date d1 = sdf.parse(sdf.format(date));
			Date d2 = sdf.parse(sdf.format(toCompare_date));

			// Calucalte time difference
			// in milliseconds
			long difference_In_Time = d2.getTime() - d1.getTime();

			// Calucalte time difference in
			// seconds, minutes, hours, years,
			// and days
			long difference_In_Seconds = (difference_In_Time / 1000) % 60;

			long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

			long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

			long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

			// Print the date difference in
			// years, in days, in hours, in
			// minutes, and in seconds

			result = (difference_In_Years != 0)
					? ((difference_In_Years < 0) ? (difference_In_Years * -1) : difference_In_Years) + " year(s) "
					: "";
			result += (difference_In_Days != 0)
					? ((difference_In_Days < 0) ? (difference_In_Days * -1) : difference_In_Days) + " day(s) "
					: "";
			result += (difference_In_Hours != 0)
					? ((difference_In_Hours < 0) ? (difference_In_Hours * -1) : difference_In_Hours) + " hour(s) "
					: "";
			result += (difference_In_Minutes != 0)
					? ((difference_In_Minutes < 0) ? (difference_In_Minutes * -1) : difference_In_Minutes)
							+ " minute(s) "
					: "";
			result += (difference_In_Seconds != 0)
					? ((difference_In_Seconds < 0) ? (difference_In_Seconds * -1) : difference_In_Seconds)
							+ " second(s) "
					: "";
			result += (difference_In_Time > 0) ? "ago" : "after";
		}

		// Catch the Exception
		catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	public static int compareDatesFromToday(Date since) {
		Date now = new Date();

		LocalDateTime dateToCompare = LocalDateTime.of(since.getYear(), since.getMonth(), since.getDay(),
				since.getHours(), since.getMinutes());
		int diference = now.compareTo(since);
		return diference;
	}

	public static String getDateString(DateTimePatterns pattern) {

		String result = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern.getPattern());
		result = format.format(new Date());

		return result;
	}

	public static String getDateString(DateTimePatterns pattern, Date date) {

		String result = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern.getPattern());
		result = format.format(date);

		return result;
	}

	public static Date getDate(DateTimePatterns pattern) {
		Date result = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern.getPattern());
			String dateString = format.format(new Date());
			result = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
		}

		return result;
	}

	public static Date getDate(DateTimePatterns pattern, Date date) {
		Date result = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern.getPattern());
			String dateString = format.format(date);
			result = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
		}

		return result;
	}

	public static Date getDateFromBaseDay(int baseDay) {
		return getDateFromBaseDay(baseDay, new Date());
	}

	public static Date getDateFromBaseDay(int baseDay, Date fromDate) {
		GregorianCalendar gc = new GregorianCalendar(fromDate.getYear() + 1900, fromDate.getMonth(),
				fromDate.getDate());
		gc.set(Calendar.DAY_OF_MONTH, baseDay);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		if (fromDate.equals(gc.getTime())) {
			return fromDate;
		}
		if (fromDate.before(gc.getTime())) {
			gc.add(Calendar.MONTH, -1);
		}
		return gc.getTime();
	}

	public static Date getFirstMomentOfDate(String date) {
		return getFirstMomentOfDate(convertToDate(date));
	}

	public static Date getFirstMomentOfDate(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getLastMomentOfDate(String date) {
		return getLastMomentOfDate(convertToDate(date));
	}

	public static Date getLastMomentOfDate(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date convertToDate(String param) {
		String[] patterns = { "dd/MM/yyyy'T'HH:mm:ss.SSSZ", "dd/MM/yyyy HH:mm:ss.SSSZ", "dd/MM/yyyy'T'HH:mm:ss.SSS",
				"dd/MM/yyyy HH:mm:ss.SSS", "dd/MM/yyyy'T'HH:mm:ssZ", "dd/MM/yyyy HH:mm:ssZ", "dd/MM/yyyy'T'HH:mm:ss",
				"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy'T'HH:mm", "dd/MM/yyyy HH:mm", "dd/MM/yyyy",
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS",
				"yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "" };
		for (String pattern : patterns) {
			try {
				Date i = new SimpleDateFormat(pattern).parse(param);
				return i;
			} catch (Throwable error) {
			}
		}
		return null;
	}

	public static Date getFirstDayFromMonth(String monthYear) {
		monthYear = monthYear.replace("/", "-");
		int year = Integer.parseInt(monthYear.split("-")[1]);
		int month = Integer.parseInt(monthYear.split("-")[0]);

		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);

		return gc.getTime();
	}

	public static Date getLastDayFromMonth(String monthYear) {
		monthYear = monthYear.replace("/", "-");
		int year = Integer.parseInt(monthYear.split("-")[1]);
		int month = Integer.parseInt(monthYear.split("-")[0]);

		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_MONTH, gc.getMaximum(Calendar.DAY_OF_MONTH));
		return gc.getTime();
	}

	public static Date getDateTimeWithMinutes(int minutos) {
		GregorianCalendar gc = new GregorianCalendar();

		gc.add(Calendar.MINUTE, minutos);

		return gc.getTime();
	}

}
