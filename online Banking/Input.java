import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {

	Validation validate = new Validation();
	DateValidation date = new DateValidation();
	Scanner scan = new Scanner(System.in);

	public String getString(String val) {
		System.out.print(val);
		String value = scan.nextLine();
		return value;
	}

	public String getName(String val) {
		String value = getString(val);
		if (!(validate.nameValidation(value))) {
			System.out.println("The given input is not valid.Please re-enter");
			return getName(val);
		}
		return value;
	}

	public int getInt(String val) {
		System.out.print(val);
		int value = 0;
		try {
			value = scan.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("!! The given input type is Wrong.Enter input in Numbers..!!");
			scan.nextLine();
			return getInt(val);
		}
		return value;
	}

	public double getDouble(String val) {
		System.out.print(val);
		double value = 0;
		try {
			value = scan.nextDouble();
		} catch (InputMismatchException ex) {
			System.out.println("!! The given input type is Wrong.Enter input in Numbers..!!");
			scan.nextLine();
			return getInt(val);
		}
		return value;
	}

	public long getLong(String val) {
		System.out.print(val);
		long value = 0;
		try {
			value = scan.nextLong();
		} catch (InputMismatchException ex) {
			System.out.println("!! The given input type is Wrong.Enter input in Numbers..!!");
			scan.nextLine();
			return getLong(val);
		}
		return value;
	}

	public long getPhoneNo(String val) {
		long phoneNo = getLong(val);
		if (!(validate.phoneNoValidation(phoneNo))) {
			System.out.println("!! The given Number is not valid.re-enter the number");
			return getPhoneNo(val);
		}
		return phoneNo;
	}

	public String getDate(String val) {
		System.out.print(val);
		String value = scan.next();
		if (!(date.isValidDate(value))) {
			System.out.println("!! The given date is invalid.Enter Correct date..!!");
			return getDate(val);
		}
		return value;
	}

	public String getPassword(String val) {
		String value = getString(val);
		if (!validate.validatePassword(value)) {
			System.out.println(
					"!! Incorrect password !! \n password must have eight characters with lower,upper,digits & special characters");
			return getPassword(val);
		}
		return value;
	}

	public int getRating(String val) {
		int rating = getInt(val);
		if (!(rating <= 5 && rating >= 0)) {
			System.out.println("your rating is out of limit\nplease give rating between 0 and 5");
			return getRating(val);
		}
		return rating;
	}

	public long getAadharNo(String message) {
		long aadhar = getLong(message);
		if (!(validate.vaildAadhar(aadhar))) {
			System.out.println("Invalid Aadhar Id. !! Please Re Enter !! ");
			return getAadharNo(message);
		}
		return aadhar;
	}

	public String getAccountNumber(String message) {
		String accountNumber = getString(message);
		if (!(validate.isValid(accountNumber))) {
			System.out.println("!! Enter a valid Account number !!");
			return getAccountNumber(message);
		}
		return accountNumber;
	}

	public String getPinNumber(String message) {
		String pin = getString(message);
		if (pin.length() > 6) {
			System.out.println("!!...Enter only 6 digit pin...!!");
			return getPinNumber(message);
		}
		if (!(validate.isValid(pin))) {
			System.out.println("!! Enter a valid Pin number !!");
			return getPinNumber(message);
		}
		return pin;
	}
}
