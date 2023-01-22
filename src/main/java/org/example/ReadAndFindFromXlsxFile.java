package org.example;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class ReadAndFindFromXlsxFile {
    private static ReadAndFindFromXlsxFile readAndFindFromFile = new ReadAndFindFromXlsxFile();
    private static String filePath;

    public static void setFilePath(String filePath) {
        ReadAndFindFromXlsxFile.filePath = filePath;
    }

    public static void main(String[] args) throws IOException {
        readAndFindFromFile.findFromFile(readFromConsole(), readFromFile());
    }

    private static String getFileExtension(String filePath) {
        int index = filePath.indexOf('.');
        return index == -1 ? null : filePath.substring(index);
    }

    private static ArrayList<String> readFromFile() throws IOException {
        ArrayList<String> fileList = new ArrayList<>();
        XSSFWorkbook xssfWorkbook;
        HSSFWorkbook hssfWorkbook;
        setFilePath("src\\Resources\\name_java.xlsx");
        if (Objects.equals(getFileExtension(filePath), ".xlsx")) {
            try (FileInputStream xssfFileInputStream = new FileInputStream(new File(filePath))) {
                xssfWorkbook = new XSSFWorkbook(xssfFileInputStream);
            }
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            for (Row row : xssfSheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    fileList.add(cell.getStringCellValue());
                }
            }
            return fileList;
        } else if (Objects.equals(getFileExtension(filePath), ".xls")) {
            try (FileInputStream hssfFileInputStream = new FileInputStream(new File(filePath))) {
                hssfWorkbook = new HSSFWorkbook(hssfFileInputStream);
            }
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            for (Row row : hssfSheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    fileList.add(cell.getStringCellValue());
                }
            }
            return fileList;
        } else {
            System.out.println("Передан неверный тип файла");
        }
        return null;
    }

    private static String[] readFromConsole() {
        String[] s = new String[3];
        System.out.println("Введите в консоль три искомые значения");
        Scanner consoleInput = new Scanner(System.in);
        for (int i = 0; i <= 2; i++) {
            s[i] = consoleInput.nextLine();
        }
        System.out.println("Вы ввели следущие значения");
        for (String p : s) {
            System.out.println(p);
        }

        return s;
    }

    private void findFromFile(String[] s, ArrayList<String> fileList) {
        for (int i = 0; i <= 2; i++) {
            if (s[i].strip().equals("")) {
                System.out.println("Поиск пустого " + (i + 1) + "-го значения не дает результатов");
            } else {
                boolean flag = false;
                for (String text : fileList) {
                    if (text.toLowerCase().contains(s[i].strip().toLowerCase())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Номер " + s[i] + " не найден");
                } else {
                    System.out.println("Номер " + s[i] + " найден");
                }
            }
        }
    }
}
