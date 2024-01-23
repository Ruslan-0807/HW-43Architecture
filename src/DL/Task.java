
package DL;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Scanner;

public class Task {
    private String path;
    private File file;
    private String fragment;

    //    создать конструктор, который получает путь к файлу и присваивает значения для
    //    path ,   file
    public Task(String path) {
        this.path = path;
        this.file = new File(path);
        this.fragment = fragment;
    }
    //    создать приватный метод printError
    //    данный метод получает объект ошибки и выводит в терминал сообщение
    //    в формате "Error: <текст ошибки>"
    private void printError(Exception e){

        System.out.println("Error: " + e.getMessage());
    }
    //    создать метод read, который возвращает ArrayList
    //    со строками файла
    public ArrayList<String> read(String path){
        ArrayList<String> result = new ArrayList<>();
        try(Scanner scanner = new Scanner(this.file)){
            while (scanner.hasNextLine()){
                result.add(scanner.nextLine());
            }
        }catch (Exception e) {
            printError(e);
        }
        return result;
    }

    public void write(String string) {
        try(FileWriter writer = new FileWriter(this.file) ){
            writer.write(string);
        }catch (IOException e){
            printError(e);
        }
    }
    //    добавить метод write. который получает новую строку и записывает ее в файл
//    в новую строку:
    public void writeNewLine(String string) {
        try(FileWriter writer = new FileWriter(this.file, true) ){
            writer.append("\n" + string);
        }catch (IOException e){
            printError(e);
        }
    }
    //    создать метод remove который получает индекс строки в качестве аргумента
//    и удаляет ее.
//    шаги:
//    1) считать файл в ArrayList
//    2) удалить из него по индексу значение
//    3) сформировать новое содержимое файла (без удаленной строки)
//    комментарий: использовать для этого String.join
//    4) записать содержимое в файл:
    public void remove(int index){
        try{
            ArrayList<String> rows = read(this.path);
            rows.remove(index);
            String content = String.join("\n", rows);
            write(content);
        }catch (IndexOutOfBoundsException e){
            printError(e);
        }
    }
    // Реализация метода search Добавьте в класс Task метод search,
    // который получает фрагмент строки и возвращает индекс строки в файле,
    // где указанный фрагмент находится. Если фрагмент не найден, то метод возвращает -1.


        public int search(String substring) {

            try (Scanner scanner = new Scanner(new File(this.path))){
                int rowNum = 1;
                while (scanner.hasNextLine()) {
                    String newLine = scanner.nextLine();
                    if (newLine.contains(substring)) {
                        return rowNum;
                    }
                    rowNum++;
                }
                scanner.close();
                return -1;
            }catch (FileNotFoundException e){
                System.out.println("Файл не найден: " + e.getMessage());
            }
            return -1;

        }

    }





