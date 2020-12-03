package java8streams.lambdas;

// Restructures a multi parameter function into multiple functions having single parameter each.
public class Currying {
    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> fun1 = u -> v -> u + v;
        Function<Integer, Integer> fun2 = fun1.apply(1);
        //here we passed the first parameter once and then used the same for two calls. first parameter is set only once.
        Integer sum1 = fun2.apply(3);
        Integer sum2 = fun2.apply(6);
        System.out.println("Sum1 :" + sum1);
        System.out.println("Sum2 :" + sum2);


        Function<Integer, Function<Integer, Function<Integer, Integer>>> fun3 = u -> v -> w -> u + v + w;
        var fun4 = fun3.apply(5);
        var fun5 = fun4.apply(10);
        Integer sum3 = fun5.apply(6);
        Integer sum4 = fun5.apply(4);

        System.out.println("Sum3 :" + sum3);
        System.out.println("Sum4 :" + sum4);
    }
}
