package mvctw.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.github.javafaker.Faker;

public class RandomData {

	private static final Faker faker = new Faker(Locale.TAIWAN);
	public static Random r = new Random();
	private static TreeMap<String, Integer> snoMap = new TreeMap<>();
	private static char currentLetter = 'A';

	public static void main(String[] args) throws ParseException { // 創建 Faker 實例

		String sno = FakeSno();
		System.out.println(sno);

	}

	public static String FakeName() {

		return faker.name().fullName();

	}

	public static Date FakeBd() {
		Date fakeDate = faker.date().birthday();
		return fakeDate;
	}

	public static String FakeEmail() {
		Faker fakers = new Faker(Locale.ENGLISH);
		return fakers.internet().safeEmailAddress();

	}

	public static Integer FakeSex() {

		boolean isMale = faker.random().nextBoolean();
		Integer gender = isMale ? 1 : 2;
		return gender;

	}

	public static String FakePwd() {
		String randomNumber = "123456";
		return randomNumber;
	}

	 public static String FakeSno() {
			String sno = null; 

			for (char letter = 'A'; letter <= 'Z'; letter++) {
				for (int number = 1; number <= 99; number++) {
					sno = String.format("%c%02d", letter, number);
				}
			}
			return sno;

		
		}

	public static String generateEmail(String sno) {
		String emailFormat = "PHCTWSTUDENT%s@GMAIL.COM";
		return String.format(emailFormat, sno);
	}

}
