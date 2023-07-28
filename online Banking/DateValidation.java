import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

public class DateValidation {

	public String getCurrentDate() {
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dt.format(ft);

		return date;
	}

	public int getDays(String day1, String day2) {
		int days = 0;
		LocalDate dateBefore = LocalDate.parse(day1);
		LocalDate dateAfter = LocalDate.parse(day2);
		days = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);
		return days;
	}

	public int getYears(String day1, String day2) {
		int days = 0;
		LocalDate dateBefore = LocalDate.parse(day1);
		LocalDate dateAfter = LocalDate.parse(day2);
		days = (int) ChronoUnit.YEARS.between(dateBefore, dateAfter);
		return days;
	}

	public boolean isValidDate(String dateStr) {
		try {
			LocalDate.parse(dateStr);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
