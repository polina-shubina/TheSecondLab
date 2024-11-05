import java.io.*;
import java.util.*;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите имя файла для чтения: ");
        String fileName = scan.nextLine();
        java.io.File file = new java.io.File(fileName);

        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        HashSet<Character> dictionary = new HashSet<>();
        if (file.exists()) {
            try {
                FileInputStream f = new FileInputStream(file);
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (char c : line.toCharArray()) {
                            if (Character.isLetter(c) && (c >= 'a' && c <= 'z')) {
                                if (dictionary.contains(c)){
                                    count.put(Character.toLowerCase(c), count.get(c)+1);
                                }
                                else{
                                    dictionary.add(Character.toLowerCase(c));
                                    count.put(Character.toLowerCase(c), 1);
                                }
                            } else if (Character.isLetter(c) && (c >= 'A' && c <= 'Z')) {
                                if (dictionary.contains(c)){
                                    count.put(Character.toUpperCase(c), count.get(c)+1);
                                }
                                else{
                                    dictionary.add(Character.toUpperCase(c));
                                    count.put(Character.toUpperCase(c), 1);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Возникла ошибка");
                    //return;
                } finally {
                    f.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print("Введите имя файла для записи: ");
        String fileName2 = scan.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {
            for (char key : count.keySet()) {
                writer.write(key + "-" + count.get(key));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка");
        } finally {
            scan.close();
        }
    }
}



