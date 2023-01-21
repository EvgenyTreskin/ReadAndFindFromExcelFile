package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReadAndFindFromXlsxFile
{
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

    private static String ReadFromConsole() {
        Scanner consoleInput = new Scanner(System.in);
        return consoleInput.nextLine();
    }

    private void FindFromFile(String s, ArrayList<String> fileList) throws IOException {
        // не проверил эту логику поскольку не запустился проект
        if (ReadFromConsole().equals("") || ReadFromConsole().equals(" ")) {
            System.out.println("поиск не дает результатов");
        } else if (!ReadFromConsole().equals("") && !ReadFromConsole().equals(" ")) {
            for (String text : fileList) {
                if (text.toLowerCase().contains(ReadFromConsole().toLowerCase())) {
                    System.out.println("номер найден");
                }
            }
        } else {
            System.out.println("номер не найден");
        }
    }
}
