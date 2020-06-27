package org.abstractward.processor;

public class TransposeMenu extends Menu {
	public TransposeMenu() {
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
}
