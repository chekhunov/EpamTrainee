package ua.nure.chekhunov.practice4;

import java.io.File;

/**
 * Show working {@link Parser}.
 *
 * @author chekhunov.
 */
public class Part4 {
	
	private String fileName;
	
	Part4(String filename) {
		setFileName(filename);
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output() {
		Parser parser = new Parser(new File(getFileName()));
		for (String str : parser) {
			System.out.println(str);
		}

	}

	public static void main(String[] args) {
		new Part4("part4.txt").output();
	}
}