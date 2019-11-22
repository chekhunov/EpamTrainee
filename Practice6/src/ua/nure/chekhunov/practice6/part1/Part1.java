package ua.nure.chekhunov.practice6.part1;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.util.Iterator;
import java.util.Scanner;

public final class Part1 {

	private Part1() {
	}

	public static void main(String[] args) {
		
		wordCounter(new StringBufferInputStream(				
				"asd\n" +
				"43\n" +
				"asdf\n" +
                "asd\n" +
                "43\n" +
                "434\n" +
                "asdf\n" +
                "kasdf\n" +
                "asdf\n" +
                "stop\n" +
                "asdf\n" +
                "stop"), System.out);
	}
	
	public static void wordCounter(InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		WordContainer wordContainer = new WordContainer(new Word.CompareByWord());
		while (scanner.hasNext()) {
			String word = scanner.next();

			
			if ("stop".equals(word)) {
				break;
			}
			
			wordContainer.add(new Word(word));
			
		}

		Iterator iterator = wordContainer.frequencyIterator();
		while (iterator.hasNext()) {
			Word w = (Word) iterator.next();
			
			out.printf("%s : %s%n", w.getValue(), w.getFrequency());
		}
	}

}
