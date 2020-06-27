package org.abstractward.processor;

import java.util.Arrays;

public class Matrix {
	private int rows;
	private int columns;
	private double[][] matrix;

	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.matrix = new double[this.rows][this.columns];
	}

	public static Matrix addMatrix(Matrix m1, Matrix m2) {
		if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
			System.out.println("ERROR");
			return new Matrix(0, 0);
		}

		Matrix m3 = new Matrix(m1.getRows(), m1.getColumns());

		for (int i = 0; i < m3.getRows(); ++i) {
			for (int j = 0; j < m3.getColumns(); ++j) {
				m3.setElement(i, j, m1.getElement(i, j) + m2.getElement(i, j));
			}
		}

		return m3;
	}

	public static Matrix multScalar(Matrix m1, double s) {
		Matrix m2 = new Matrix(m1.getRows(), m1.getColumns());

		for (int i = 0; i < m2.getRows(); ++i) {
			for (int j = 0; j < m2.getColumns(); ++j) {
				m2.setElement(i, j, m1.getElement(i, j) * s);
			}
		}
		return m2;
	}

	public static Matrix dot(Matrix m1, Matrix m2) {
		if (m1.getColumns() != m2.getRows()) {
			System.out.println("ERROR");
			return new Matrix(0, 0);
		}

		Matrix m2t = Matrix.transposeMain(m2);
		Matrix m3 = new Matrix(m1.getRows(), m2.getColumns());

		for (int i = 0; i < m1.getRows(); ++i) {
			for (int j = 0; j <  m2t.getRows(); ++j) {
				double sum = 0;
				for (int k = 0; k < m2t.getColumns(); ++k) {
					sum += m1.getElement(i, k) * m2t.getElement(j, k);
				}
				m3.setElement(i, j, sum);
			}
		}

		return m3;
	}

	public static Matrix transposeMain(Matrix m1) {
		Matrix m2 = new Matrix(m1.getColumns(), m1.getRows());

		for (int i = 0; i < m2.getRows(); ++i) {
			for (int j = 0; j < m2.getColumns(); ++j) {
				m2.setElement(i, j, m1.getElement(j, i));
			}
		}

		return m2;
	}

	public static Matrix transposeAnti(Matrix m1) {
		Matrix m2 = new Matrix(m1.getColumns(), m1.getRows());

		for (int i = 0; i < m1.getRows(); ++i) {
			for (int j = 0; j < m1.getColumns(); ++j) {
				m2.setElement(j, i, m1.getElement(m1.getRows() - i - 1, m1.getColumns() - j - 1));
			}
		}

		return m2;
	}

	public static Matrix transposeHorizontal(Matrix m1) {
		Matrix m2 = new Matrix(m1.getRows(), m1.getColumns());

		for (int i = 0; i < m1.getRows(); ++i) {
			for (int j = 0; j < m1.getColumns(); ++j) {
				m2.setElement(i, j, m1.getElement(m1.getRows() - i - 1, j));
			}
		}

		return m2;
	}

	public static Matrix transposeVertical(Matrix m1) {
		Matrix m2 = new Matrix(m1.getRows(), m1.getColumns());

		for (int i = 0; i < m1.getRows(); ++i) {
			for (int j = 0; j < m1.getColumns(); ++j) {
				m2.setElement(i, j, m1.getElement(i, m1.getColumns() - j - 1));
			}
		}

		return m2;
	}

	public static Matrix minor(Matrix m1, int rowRemoved, int columnRemoved) {
		Matrix m2 = new Matrix(m1.getRows() - 1, m1.getColumns() - 1);

		int m2RowPosition = 0;
		for (int i = 0; i < m1.getRows(); ++i) {
			int m2ColumnPosition = 0;
			if (i == rowRemoved) {
				continue;
			}
			for (int j = 0; j < m1.getColumns(); ++j) {
				if (j == columnRemoved) {
					continue;
				}
				m2.setElement(m2RowPosition, m2ColumnPosition, m1.getElement(i, j));
				++m2ColumnPosition;
			}
			++m2RowPosition;
		}

		return m2;
	}

	public static double determinant(Matrix m) {
		if (m.getRows() == 2) {
			return m.getElement(0, 0) * m.getElement(1, 1) - m.getElement(0, 1) * m.getElement(1, 0);
		}

		double sum = 0;
		for (int i = 0; i < m.getColumns(); ++i) {
			sum += Matrix.determinant(Matrix.minor(m, 0, i)) * m.getElement(0, i) * Math.pow(-1, i);
		}

		return sum;
	}

	public static double cofactor(Matrix m, int rowRemoved, int columnRemoved) {
		return Matrix.determinant(Matrix.minor(m, rowRemoved, columnRemoved)) * Math.pow(-1, rowRemoved + columnRemoved);
	}

	public static Matrix inverse(Matrix m1) {
		Matrix m2 = new Matrix(m1.getRows(), m1.getColumns());
		for (int i = 0; i < m1.getRows(); ++i) {
			for (int j = 0; j < m1.getColumns(); ++j) {
				m2.setElement(i, j, Matrix.cofactor(m1, i, j));
			}
		}
		return Matrix.multScalar(Matrix.transposeMain(m2), 1.0 / (Matrix.determinant(m1)));
	}

	public String toString() {
		StringBuilder matrixString = new StringBuilder();

		for (double[] row: this.matrix) {
			for (double column : row) {
				matrixString.append(String.format("% .2f ", column == -0.0 ? 0 : column));
			}
			matrixString.append("\n");
		}

		return matrixString.toString();
	}

	public void setElement(int row, int column, double element) {
		this.matrix[row][column] = element;
	}

	public double getElement(int row, int column) {
		return this.matrix[row][column];
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}
}
