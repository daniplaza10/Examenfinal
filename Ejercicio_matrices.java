package Matrices;

public class Ejercicio_matrices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatricesCalculator {

    private static final String INPUT_FILE_A = "matrizA.txt";
    private static final String INPUT_FILE_C = "matrizC.txt";
    private static final String OUTPUT_FILE_B = "matrizB.txt";

    public static void main(String[] args) {
        try {
            double[][] matrixA = readMatrixFromFile(INPUT_FILE_A);
            double[][] matrixC = readMatrixFromFile(INPUT_FILE_C);

            double[][] matrixB = calculateMatrixB(matrixA, matrixC);

            writeMatrixToFile(matrixB, OUTPUT_FILE_B);
            System.out.println("Matriz B calculada y guardada en " + OUTPUT_FILE_B);
        } catch (IOException e) {
            System.err.println("Error al procesar los archivos: " + e.getMessage());
        }
    }

    private static double[][] readMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // Leemos la primera línea para obtener el tamaño de la matriz
        String sizeLine = reader.readLine();
        String[] sizeParts = sizeLine.split(" ");
        int rows = Integer.parseInt(sizeParts[0]);
        int cols = Integer.parseInt(sizeParts[1]);

        double[][] matrix = new double[rows][cols];

        String line;
        int rowIndex = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            for (int i = 0; i < parts.length; i++) {
                matrix[rowIndex][i] = Double.parseDouble(parts[i]);
            }
            rowIndex++;
        }

        reader.close();
        return matrix;
    }

    private static void writeMatrixToFile(double[][] matrix, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write(matrix.length + " " + matrix[0].length);
        writer.newLine();

        for (double[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                writer.write(row[i] + " ");
            }
            writer.newLine();
        }

        writer.close();
    }

    private static double[][] calculateMatrixB(double[][] matrixA, double[][] matrixC) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;

        double[][] matrixB = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixB[i][j] = matrixC[i][j] - matrixA[i][j];
            }
        }

        return matrixB;
    }
}

