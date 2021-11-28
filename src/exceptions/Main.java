package exceptions;

import java.io.FileNotFoundException;

public class Main {
    public static void file() throws CustomException {
        System.out.println("Выбрасываем CustomException из file()");
        throw new CustomException();
    }
    public static void catalogue() throws CustomException {
        System.out.println("Выбрасываем CustomException из catalogue()");
        throw new CustomException("Originated in catalogue()");
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileWorker fileworker = new FileWorker();

        // читаем каталог с файлами
        fileworker.fw();
        // создадим новый файл
        fileworker.fileNew();
        // скопируем файл свойств
        fileworker.copyFile();
        // читаем файл резервной копии
        try {
            fileworker.readFile();
        } catch (Exception e) {
            System.err.println("getMessage():" + e.getMessage());
            System.err.println("getLocalizedMessage():" + e.getLocalizedMessage());
            System.err.println("toString():" + e);
            System.err.println("printStackTrace():");
            e.printStackTrace(System.out);
        }
        // читаем выборочно данные из файла свойств
        fileworker.readProperties();
        try {
            file();
        } catch(CustomException e) {
            e.printStackTrace(System.out);
        }
        try {
            catalogue();
        } catch(CustomException e) {
            e.printStackTrace(System.out);
        }
    }
}
