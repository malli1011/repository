package java8streams.custom.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class SplitaratorCharacteristics {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);

        Stream<Integer> stream = list.stream();
        Spliterator<Integer> spliterator = stream.spliterator();
        int bits = spliterator.characteristics();
        System.out.println(Integer.bitCount(bits));

        //OR
        System.out.println(Integer.bitCount(bits | 0X000010));

        //AND
        System.out.println(Integer.bitCount(bits & 0X000010));

        System.out.println(spliterator.hasCharacteristics(0X000010));


    }
}
