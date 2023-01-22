package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReadAndFindFromXlsxFile {
    public static void main(String[] args) throws IOException {
        ReadAndFindFromXlsxFile readAndFindFromFile = new ReadAndFindFromXlsxFile();
        readAndFindFromFile.FindFromFile(ReadFromConsole(), ReadFromFile());
    }

    private static ArrayList<String> ReadFromFile() throws IOException {
        ArrayList<String> fileList = new ArrayList<>();
        XSSFWorkbook workbook;
        // тут вопрос насчет пути к файлу
        try (FileInputStream fileInputStream = new FileInputStream(new File("src\\Resources/name_java.xlsx"))) {
            workbook = new XSSFWorkbook(fileInputStream);
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                fileList.add(cell.getStringCellValue());
            }
        }
        return fileList;
    }

    private static String[] ReadFromConsole() {
        String[] s = new String[3];
        System.out.println("Введите в консоль три искомые значения");
        Scanner consoleInput = new Scanner(System.in);
        for (int i = 0; i <= 2; i++) {
            s[i] = consoleInput.nextLine();
        }
        for (String p: s){
            System.out.println(p);
        }

        return s;
    }

    private void FindFromFile(String[] s, ArrayList<String> fileList) throws IOException {
        // не проверил эту логику поскольку не запустился проект
        for (int i = 0; i <= 2; i++) {
            if (s[i].equals("") || s[i].equals(" ")) {
                System.out.println("поиск значения под номером " + (i + 1) + " не дает результатов");
            } else {
                int counter = 0;
                for (String text : fileList) {
                    if (text.toLowerCase().contains(s[i].toLowerCase())) {
                        System.out.println("номер " + s[i] + " найден");
                        counter++;
                    }
                }
                if (counter == 0) {
                    System.out.println("номер " + s[i] + " не найден");
                } else {
                    System.out.println("номер " + s[i] + " найден " + counter + " раз");
                }
            }
        }
    }
}
