package java8streams.lambdas;

//inverse of chaining. the output of last function will be the input to the previous function.
public class Composition {
    public static void main(String[] args) {
        Function<Square, Integer> f1 = s -> s.getArea();
        Function<Integer, Double> f2 = area -> Math.sqrt(area);
        //here the output of f1 is the input to f2;
        Function<Square, Double> getSide = f2.compose(f1);

        Square s = new Square();
        s.setArea(100);
        System.out.println(getSide.apply(s));
    }
}

@FunctionalInterface
interface Function<T, R> {
    R apply(T t);

    default <V> Function<V, R> compose(Function<V, T> before) {
        return (V v) -> apply(before.apply(v));
    }
}


class Square {
    private int area;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
