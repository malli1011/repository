package java8streams.higerorderfuntions;

public class HigherOrderFunction {
    public static void main(String... args) {
        IFactory<Integer> factory = createFactory(() -> Math.random() * 100, r -> r.intValue());
        Integer integer = factory.create();
        System.out.println("Integer = " + integer);
    }

    public static <T, R> IFactory<R> createFactory(IProducer<T> producer, IConfigurator<T, R> configurator) {

        return () -> {
            T product = producer.produce();
            return configurator.configure(product);
        };
    }
}
