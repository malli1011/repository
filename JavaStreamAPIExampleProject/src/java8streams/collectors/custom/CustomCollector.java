package java8streams.collectors.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class CustomCollector {
    public static void main(String[] args) {
        final List<Integer> integers = List.of(91, 92, 3, 40, 54, 16, 7, 8, 39, 100, 11, 12, 133, 14, 15);

        final Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(ArrayList::new, //supplier
                (list, e) -> list.add(e), // accumulator (BiConsumer)
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }, //Combiner (BiFunction)
                Collector.Characteristics.IDENTITY_FINISH
        );

        final List<Integer> collect = integers.stream().map(i -> i * i).collect(toList);
        collect.forEach(System.out::println);

        //Collector.of(supplier,accumulator,combiner,finisher,characteristics)
        final Collector<Integer, List<Integer>, List<Integer>> sortedListCollectors = Collector.of(ArrayList::new, //supplier
                (list, e) -> list.add(e), // accumulator (BiConsumer)
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }, //Combiner (BiFunction)
                (list) -> {
                    Collections.sort(list);
                    return list;
                },//Finisher
                Collector.Characteristics.UNORDERED);

        integers.stream().collect(sortedListCollectors).stream().forEach(System.out::println);

    }

}
