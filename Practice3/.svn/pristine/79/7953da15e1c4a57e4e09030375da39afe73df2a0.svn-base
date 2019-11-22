package ua.nure.chekhunov.practice3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Demo {

	/**
	 * Encoding.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * Path to file for subtask01.
	 */
	private static final String PATH_PART1 = "part1.txt";

	/**
	 * Path to file for subtask02.
	 */
	private static final String PATH_PART2 = "part2.txt";

	/**
	 * Path to file for subtask03.
	 */
	private static final String PATH_PART3 = "part3.txt";

	/**
	 * Reading data of file.
	 *
	 * @param fileName - name file.
	 * @return result read file.
	 * @throws IOException - problem file.
	 */
	public static String getInput(final String fileName) throws IOException {
		final StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(new File(fileName), ENCODING)) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
		}
		final String result = sb.toString().trim();
		System.out.println("File(" + fileName + "):" + System.lineSeparator() + result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		String result;

		// part1
		try {
			result = getInput(PATH_PART1);
			if (!result.isEmpty()) {
				System.out.println(Part1.convert1(result) + System.lineSeparator());
				System.out.println(Part1.convert2(result) + System.lineSeparator());
				System.out.println(Part1.convert3(result) + System.lineSeparator());
				System.out.println(Part1.convert4(result) + System.lineSeparator());
			}
		} catch (final IOException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println();
		// part2
		try {
			result = getInput(PATH_PART2);
			if (!result.isEmpty()) {
				System.out.println("\nResult:\n" + Part2.convert(result));
			}
		} catch (final IOException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println();

		// part3

		try {
			result = getInput(PATH_PART3);
			if (!result.isEmpty()) {
				System.out.println("Result:\n" + Part3.convert(result));
			}
		} catch (final IOException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println();
		// part4
		System.out.println(Part4.hash("adf", "MD5"));
		System.out.println(Part4.hash("asdf", "SHA-256"));
		Part5.main(args);
		Part6.main(args);

	}
}
