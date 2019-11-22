package ua.nure.chekhunov.practice6.part4;

public class Part4 {

	public static void main(String[] args) {

		Range range = new Range(3, 10);
		for (Integer el : range) {
			System.out.printf("%d ", el);
		}
		System.out.println();

		range = new Range(3, 10, true);
		for (Integer el : range) {
			System.out.printf("%d ", el);
		}
		System.out.println();

	}
}
