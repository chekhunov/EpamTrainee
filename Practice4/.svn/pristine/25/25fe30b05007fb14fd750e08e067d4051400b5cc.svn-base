package ua.nure.chekhunov.practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public final class Part5 {

	private static final String FILE_NAME = "resources";

	private Part5() {
	}

	public void output() {
		Scanner sc = new Scanner(System.in, "cp1251");
		while (sc.hasNext()) {
			try {
				String[] arr = sc.nextLine().split(" ");
				if ("stop".equalsIgnoreCase(arr[0])) {
					break;
				}
				if (arr.length != 2) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale locale = new Locale(arr[1].toLowerCase(Locale.ENGLISH));
				ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
				System.out.println(rb.getString(arr[0]));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("No such values");
				continue;
			}
		}
		sc.close();
	}

	public static void main(String[] args) {
		Part5 part5 = new Part5();
		part5.output();
	}
}
