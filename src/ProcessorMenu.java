package org.abstractward.processor;

public class ProcessorMenu extends Menu {
	public ProcessorMenu() {
		processorLoop:
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
				new TransposeMenu();
				break;

				case 5:
				determinant();
				break;

				case 6:
				inverse();
				break;

				case 0:
				break processorLoop;

				default:
				System.out.println("Invalid input.");
				break;
			}
		}
	}

	private void addMatrix() {
		Matrix m1 = getMatrix();
		Matrix m2 = getMatrix();

		displayResultMatrix(Matrix.addMatrix(m1, m2));
	}

	private void multScalar() {
		Matrix m = getMatrix();

		System.out.print("Enter scalar value: ");
		double s = getDouble();

		displayResultMatrix(Matrix.multScalar(m, s));
	}

	private void dot() {
		Matrix m1 = getMatrix();
		Matrix m2 = getMatrix();

		displayResultMatrix(Matrix.dot(m1, m2));
	}

	private void determinant() {
		Matrix m = getMatrix();
		System.out.println("The result is:");
		System.out.println(Matrix.determinant(m));
	}

	private void inverse() {
		Matrix m = getMatrix();
		displayResultMatrix(Matrix.inverse(m));
	}
}
