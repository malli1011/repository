package java8streams.lambdas;

import java.util.Objects;
import java.util.function.Function;

//the output of first function will be the input to next function
public class Chaining {
    public static void main(String[] args) {
        Consumer<String> c1 = c -> System.out.println(c);
        Consumer<String> c2 = c -> System.out.println(c);

        Consumer<String> c3 = s -> {
            c1.accept(s);
            c2.accept(s);
        };
        c3.accept("Hello");
        Consumer<String> c4 = c1.thenAccept(c2).thenAccept(c3);
        c4.accept("BasicsStrong");

        Function<Integer, Integer> f1 = s -> s + 2;
        Function<Integer, Integer> f2 = s -> s * 2;
        //f3 will perform f1 and then f2. output of f1 will be input to f2. this is function chain.
        Function<Integer, Integer> f3 = f1.andThen(f2);
        System.out.println(f3.apply(10));
    }


}


interface Consumer<T> {
    void accept(T t);

    default Consumer<T> thenAccept(Consumer<T> next) {
        Objects.requireNonNull(next);
        return (T t) -> {
            this.accept(t);
            next.accept(t);
        };
    }
}