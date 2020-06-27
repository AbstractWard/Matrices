package org.abstractward.processor;

import java.util.Scanner;

public class Menu {
	protected Scanner scanner;

	{
		scanner = new Scanner(System.in);
	}

	protected Matrix getMatrix() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter size of matrix: ");
		Matrix m = new Matrix(getInt(), getInt());

		System.out.println("Enter matrix elements: ");
		populateMatrix(m);
		return m;
	}

	protected void populateMatrix(Matrix m) {
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				m.setElement(i, j, getDouble());
			}
		}
	}

	protected void displayResultMatrix(Matrix m) {
		System.out.println("\nThe result is:");
		System.out.println(m.toString());
	}

	protected double getDouble() {
		return this.scanner.nextDouble();
	}

	protected int getInt() {
		return this.scanner.nextInt();
	}
}
