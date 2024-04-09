package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SLAUTest {

    @Test
    public void testSLAU() throws Exception {
        String inputFile1 = "input_matrices.txt"; // Файл с матрицами
        String inputFile2 = "input_solutions.txt"; // Файл с правильными решениями
        String outputFile = "output_solutions.txt"; // Файл для записи результатов

        // Создаем содержимое файлов
        createInputFiles(inputFile1, inputFile2);

        // Вызываем метод для решения СЛАУ
        SLAU.resh(inputFile1, inputFile2, outputFile);

        // Проверяем, что содержимое файла с решениями совпадает с ожидаемым
        assertOutputFile(outputFile);
    }

    // Метод для создания файлов с данными
    private void createInputFiles(String inputFile1, String inputFile2) throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(inputFile1));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(inputFile2));

        // Первая матрица
        writer1.write("2\n"); // Количество матриц в файле
        writer1.write("2 1 3\n"); // Первая матрица
        writer1.write("1 3 2\n"); // Вторая матрица
        writer1.close();

        // Правильные решения
        writer2.write("1 1\n"); // Решение для первой матрицы
        writer2.write("2 2\n"); // Решение для второй матрицы
        writer2.close();
    }

    // Метод для проверки содержимого выходного файла с решениями
    private void assertOutputFile(String outputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));

        // Проверяем решение для первой матрицы
        String line = reader.readLine();
        assertEquals("Matrix 1 solution: 1.00000 1.00000 ", line);

        // Проверяем решение для второй матрицы
        line = reader.readLine();
        assertEquals("Matrix 2 solution: 2.00000 2.00000 ", line);

        reader.close();
    }
}