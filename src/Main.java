package org.abstractward.processor;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		mainLoop:
		while (true) {
			System.out.println("1. Matrix addition");
			System.out.println("2. Scalar multiplication");
			System.out.println("3. Matrix dot product");
			System.out.println("4. Matrix transposition");
			System.out.println("5. Matrix determinant");
			System.out.println("6. Matrix inverse");
			System.out.println("0. Exit");
			System.out.print("\nYour choice: ");
			switch (scanner.nextInt()) {
				case 1:
				addMatrix();
				break;

				case 2:
				multScalar();
				break;

				case 3:
				dot();
				break;

				case 4:
				transpose();
				break;

				case 5:
				determinant();
				break;

				case 6:
				inverse();
				break;

				case 0:
				break mainLoop;

				default:
				System.out.println("Invalid input.");
				break;
			}
		}
	}

	public static Matrix getMatrix() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter size of matrix: ");
		Matrix m = new Matrix(scanner.nextInt(), scanner.nextInt());

		System.out.println("Enter matrix elements: ");
		populateMatrix(m);
		return m;
	}

	public static void populateMatrix(Matrix m) {
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				m.setElement(i, j, scanner.nextDouble());
			}
		}
	}

	public static void displayResultMatrix(Matrix m) {
		System.out.println("\nThe result is:");
		System.out.println(m.toString());
	}

	public static void addMatrix() {
		Matrix m1 = getMatrix();
		Matrix m2 = getMatrix();

		displayResultMatrix(Matrix.addMatrix(m1, m2));
	}

	public static void multScalar() {
		Matrix m = getMatrix();

		System.out.print("Enter scalar value: ");
		double s = new Scanner(System.in).nextDouble();

		displayResultMatrix(Matrix.multScalar(m, s));
	}

	public static void dot() {
		Matrix m1 = getMatrix();
		Matrix m2 = getMatrix();

		displayResultMatrix(Matrix.dot(m1, m2));
	}

	public static void transpose() {
		Scanner scanner = new Scanner(System.in);
		transposeLoop:
		while (true) {
			System.out.println("\n1. Main diagonal");
			System.out.println("2. Anti diagonal");
			System.out.println("3. Vertical line");
			System.out.println("4. Horizontal line");
			System.out.print("\nYour choice: ");

			switch (scanner.nextInt()) {
				case 1:
				displayResultMatrix(Matrix.transposeMain(getMatrix()));
				break transposeLoop;

				case 2:
				displayResultMatrix(Matrix.transposeAnti(getMatrix()));
				break transposeLoop;

				case 3:
				displayResultMatrix(Matrix.transposeVertical(getMatrix()));
				break transposeLoop;

				case 4:
				displayResultMatrix(Matrix.transposeHorizontal(getMatrix()));
				break transposeLoop;

				default:
				System.out.println("Invalid input");
				break;
			}
		}
	}

	public static void determinant() {
		Matrix m = getMatrix();
		System.out.println("The result is:");
		System.out.println(Matrix.determinant(m));
	}

	public static void inverse() {
		Matrix m = getMatrix();
		displayResultMatrix(Matrix.inverse(m));
	}
}
