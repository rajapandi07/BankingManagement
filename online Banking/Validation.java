public class Validation {

	public boolean nameValidation(String value) {
		for (int i = 0; i < value.length(); i++) {
			char letter = value.charAt(i);
			if (!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z') || (letter == ' '))) {
				return false;
			}
		}
		return true;
	}

	public boolean phoneNoValidation(long phoneNo) {
		if (!(phoneNo > 6000000000l && phoneNo <= 9999999999l)) {
			return false;
		}
		return true;
	}

	public boolean validatePassword(String val) {
		if (hasUpperCase(val) && hasLowerCase(val) && hasDigits(val) && hasSpecialCharacter(val)) {
			return true;
		}
		return false;
	}

	public boolean hasUpperCase(String val) {
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) >= 'A' && val.charAt(i) <= 'Z') {
				return true;
			}
		}
		return false;
	}

	public boolean hasLowerCase(String val) {
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) >= 'a' && val.charAt(i) <= 'z') {
				return true;
			}
		}
		return false;
	}

	public boolean hasDigits(String val) {
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) >= 48 && val.charAt(i) <= 57) {
				return true;
			}
		}
		return false;
	}

	public boolean hasSpecialCharacter(String val) {
		String special = "!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
		for (int i = 0; i < val.length(); i++) {
			String s = Character.toString(val.charAt(i));
			if (special.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public boolean vaildAadhar(long aadhar) {
		if (aadhar >= 200000000000l && aadhar <= 999999999999l) {
			return true;
		}
		return false;
	}

	public boolean isValid(String accountNumber) {
		for (int i = 0; i < accountNumber.length(); i++) {
			if (!(accountNumber.charAt(i) >= 48 && accountNumber.charAt(i) <= 57)) {
				return false;
			}
		}
		return true;
	}

}
