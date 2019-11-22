package ua.nure.chekhunov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part2 {
	
	/**
     * Default constructor.
     */
    private Part2() {
        throw new UnsupportedOperationException("Subtask02");
    }

	/**
	 * @param input
	 * @return
	 */
	public static String convert(final String input) {
		String min = getMin(input);
		String max = getMax(input);
		return min + System.lineSeparator() + max;
	}

	/**
	 * @param input
	 * @return
	 */
	public static String getMin(String input) {
		StringBuilder sb = new StringBuilder();
		int count = Integer.MAX_VALUE;
		sb.append("Min: ");
		Pattern p = Pattern.compile("[a-zA-Z\\p{InCyrillic}]+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			Pattern p2 = Pattern.compile(m.group());
			Matcher m2 = p2.matcher(sb.toString().substring(5));
			if (count > m.group().length()) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("Min: ");
				count = m.group().length();
				sb2.append(m.group());
				sb = sb2;
			} else if (count == m.group().length() && !(m2.find())) {
				sb.append(", " + m.group());
			}
		}
		return sb.toString();
	}

	/**
	 * @param input
	 * @return
	 */
	public static String getMax(String input) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		sb.append("Max: ");
		Pattern p = Pattern.compile("[a-zA-Z\\p{InCyrillic}]+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			Pattern p2 = Pattern.compile(m.group());
			Matcher m2 = p2.matcher(sb.toString().substring(5));
			if (count < m.group().length()) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("Max: ");
				count = m.group().length();
				sb2.append(m.group());
				sb = sb2;
			} else if (count == m.group().length() && !(m2.find())) {
				sb.append(", " + m.group());

			}
		}
		return sb.toString();
	}
}