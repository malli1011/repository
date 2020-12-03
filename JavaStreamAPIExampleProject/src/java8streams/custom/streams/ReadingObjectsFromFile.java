package java8streams.custom.streams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ReadingObjectsFromFile {
    public static void main(String[] args) {
        Path path = Paths.get("D:/Malli/Books.txt");
        try (Stream<String> lines = Files.lines(path)) {
            Spliterator<String> baseSpliterator = lines.spliterator();
            Spliterator<Book> spliterator = new BookSpliterator(baseSpliterator);

            final Stream<Book> stream = StreamSupport.stream(spliterator, false);
            stream.forEach(System.out::println);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
