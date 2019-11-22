package ua.nure.chekhunov.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part61 {

	private String fileName;
	private String[] input;

	Part61() {
	}

	public static void main(String[] args) {
		new Part61().console("--input", "part6.txt", "--task", "frequency");
	}

	private boolean console(String input, String fileName, String task, String operation) {
		if (!("--input".equals(input) || "-i".equals(input))) {
			System.err.println("Wrong operation");
			return false;
		}
		if (!("--task".equals(task) || "-t".equals(task))) {
			System.err.println("Wrong task");
			return false;
		}

		this.fileName = fileName;
		initialize();

		if (operation == "frequency") {
			getResultFrequecy();
		}
		return true;
	}

	private String getInput() {
		StringBuilder sb = new StringBuilder();
		try (Scanner file = new Scanner(new File(fileName), "CP1251")) {
			while (file.hasNext()) {
				sb.append(file.next()).append(" ");
			}
		} catch (FileNotFoundException e) {
			System.err.println(String.format("File: %s not found", fileName));
		}
		return sb.toString();
	}

	public void initialize() {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(getInput());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		input = sb.toString().split(" ");
	}

	private void getResultFrequecy() {
		class CountWithPlace {
			private int count = 1;
			private final int place;

			public CountWithPlace(int place) {
				this.place = place;
			}

			public int getCount() {
				return count;
			}

			public CountWithPlace setCount(int count) {
				this.count = count;
				return this;
			}

		}
		final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
		for (int place = 0; place < input.length; place++) {
			String w = input[place];
			CountWithPlace countWithPlace = wordCounts.get(w);
			if (countWithPlace == null) {
				wordCounts.put(w, new CountWithPlace(place));
			} else {
				countWithPlace.setCount(countWithPlace.getCount() + 1);
			}
		}
		TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				CountWithPlace countWithPlaceA = wordCounts.get(a);
				CountWithPlace countWithPlaceB = wordCounts.get(b);
				int count = countWithPlaceB.count - countWithPlaceA.count;
				if (count == 0) {
					return countWithPlaceA.place - countWithPlaceB.place;
				} else {
					return count;
				}
			}
		});
		sortedWords.putAll(wordCounts);

		TreeSet<String> firstStrings = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		int i = 0;
		for (String s : sortedWords.keySet()) {
			if (i == 3) {
				break;
			}
			i++;
			firstStrings.add(s);
		}
		for (String s : firstStrings) {
			System.out.println(s + " ==> " + sortedWords.get(s).getCount());
		}
	}
}
