
import java.io.*;
import java.util.*;

public class FileManager {

    public static void  load(String filename) {
        ArrayList<Book> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Book b = new Book(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        Integer.parseInt(data[4].trim()),
                        data[5].trim()
                );
                Table.books.add(b);
                Table.data.add(b);
            }
        } catch (Exception e) {
            System.out.println("error in  reading file");
        }
    }

    public static void save(String filename, ArrayList<Book> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("BookId, Title, Author, Category, Year, ISBN");

            for (Book b : list) {
                pw.println(b);
            }
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
    }
}