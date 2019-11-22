package ua.nure.chekhunov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chekhunov
 *
 */
class Parser implements Iterable<String> {

	/**
	 * encoding "Cp1251".
	 */
	private String encoding = "Cp1251";

	private Matcher matcher;

	private static final String REGEXP = "\\p{javaUpperCase}.*?\\.";

	public Parser() {
	}

	public Parser(File file) {

		StringBuilder sb = new StringBuilder();
		try {
			final Scanner s = new Scanner(file, getEncoding());
			while (s.hasNextLine()) {
				sb.append(s.nextLine()).append(" ");
				sb.delete(sb.length() - 1, sb.length());
				Pattern p = Pattern.compile(REGEXP);
				setMatcher(p.matcher(sb));
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println(file + " not found");
		}
	}

	public final String getEncoding() {
		return encoding;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public final void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	@Override
	public Iterator<String> iterator() {
		return new ParserIterator(getMatcher());
	}

	private static class ParserIterator implements Iterator<String> {

		private Matcher matcher;

		public ParserIterator(Matcher matcher) {
			setMatcher(matcher);
		}

		public Matcher getMatcher() {
			return this.matcher;
		}

		public final void setMatcher(Matcher matcher) {
			this.matcher = matcher;
		}

		@Override
		public boolean hasNext() {
			return getMatcher().find();
		}

		@Override
		public String next() {
			try {
				return getMatcher().group();
	        } catch (NoSuchElementException e) {
	        	throw new NoSuchElementException();
	        }
	    }
	   


		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
