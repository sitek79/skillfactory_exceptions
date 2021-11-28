package exceptions;

import java.io.*;
import java.util.Properties;

public class FileWorker {
    String srcDir = "src/exceptions/";
    String appProp = "application.properties";
    String appPropBackup = "application.properties.bak";

    public void fw() {
        // создаем объект File для каталога
        File dir1 = new File(srcDir);
        // создаем объекты для файлов, которые находятся в каталоге
        //File file1 = new File(dir1, appProp);
        //File file2 = new File(dir1, appPropBackup);
        // если объект представляет каталог
        if (dir1.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            try {
                for (File item : dir1.listFiles()) {
                    if (item.isDirectory()) {
                        System.out.println(item.getName() + "  \t каталог");
                    } else {
                        System.out.println(item.getName() + "\t файл");
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка при получении объектов каталога");
            }
        }
    }

    public void fileNew() {
        // создадим новый файл
        try {
            File newFile = new File(srcDir, appPropBackup);
            boolean created = newFile.createNewFile();
            if (created)
                System.out.println("Файл был успешно создан");
        } catch (IOException ex) {
            System.err.println("Ошибка ввода-вывода " + ex.getMessage());
        }
    }

    public void copyFile() {
        File fin = new File(srcDir, appProp);
        File fos = new File(srcDir, appPropBackup);

        try (FileInputStream finStream = new FileInputStream(fin);
             FileOutputStream foStream = new FileOutputStream(fos)) {
            byte[] buffer = new byte[finStream.available()];
            // считываем буфер
            finStream.read(buffer, 0, buffer.length);
            // записываем из буфера в файл
            foStream.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (Error error) {
            System.err.println("Все упало" + error.getMessage());
        }
    }

    public void readFile() {
        FileInputStream inputStream = null;
        try {
            File file = new File(srcDir, appProp);
            inputStream = new FileInputStream(file);
            // используем inputStream для чтения файла
            inputStream.read();
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("Ошибка закрытия потока чтения файла ...");
                }
            }
        }
    }

    // обращение к файлу application.properties
    public void readProperties() {
        //типа Hashtable для удобной работы с данными
        Properties prop = new Properties();
        try {
            File file = new File(srcDir, appProp);
            FileInputStream inputStream = new FileInputStream(file);
            prop.load(inputStream);

            String version = prop.getProperty("version");
            String name = prop.getProperty("name");
            String date = prop.getProperty("date");
            String clientID = prop.getProperty("clientID");

            //печатаем полученные данные в консоль
            System.out.println(
                    "version: " + version + "\nname: " + name + "\ndate: " + date + "\nclientID: " + clientID);
        } catch (IOException e) {
            System.err.println("Ошибка чтения свойства в файле " + appProp);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Во входных данных недопустимый символ Юникода.");
        } catch (ClassCastException e) {
            System.err.println("Объект свойств содержит какие-либо ключи или значения, которые не являются строками.");
        } catch (Exception e) {
            System.err.println("Перехвачено то что пропустили все другие перехватчики исключений");
        }
    }
}
