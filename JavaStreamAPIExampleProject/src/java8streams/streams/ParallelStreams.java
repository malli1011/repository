package java8streams.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreams {

    public static void main(String[] args) {

        long startTime;
        long endTime;

        List<Employee> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new Employee("John", 20000));
            list.add(new Employee("Rohn", 3000));
            list.add(new Employee("Tom", 15000));
            list.add(new Employee("Bheem", 8000));
            list.add(new Employee("Shiva", 200));
            list.add(new Employee("Krishna", 50000));
        }


        startTime = System.currentTimeMillis();
        System.out.println("Performing Sequentially: " + list.stream()
                .filter(e -> e.getSalary() > 1000)
                .count());

        endTime = System.currentTimeMillis();

        System.out.println("Time taken with Sequential : " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("Performing parallely: " + list.parallelStream()
                .filter(e -> e.getSalary() > 1000)
                .count());

        endTime = System.currentTimeMillis();

        System.out.println("Time taken with parallel : " + (endTime - startTime));

        List<Integer> list2 = List.of(1, 2, 4, 5, 6, 7, 9);
        // the below can not be performed in parallel as skip and limit are stateful operations.
		/*
		List<Integer> collect = list2
				.parallelStream()
				.skip(2)  //stateful intermediate operation
				.limit(5) //stateful intermediate operation
				.collect(Collectors.toList());

		 */

        /*
         * Fork-Join pool is used by all parallel stream operations. Takes all available processors by default.
         *
         * */

        System.out.println(Runtime.getRuntime().availableProcessors());

        //set the number of processors for fork-join pool
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        //Create your own fork-join pool
        ForkJoinPool pool = new ForkJoinPool(2);
        try {
            Long count = pool.submit(() -> list.parallelStream().filter(emp -> emp.getSalary() > 10000).count()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //if the operation is Computational Intensive then Number of threads <= number of cores
		//if the operation is IO intensive then hen Number of threads >= number of cores


    }
}
