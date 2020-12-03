package java8streams.lambdas;

import java.util.Optional;
import java.util.function.Function;

public class MethodRefExample {
    /*
    * ClassName::staticMethodName  //Math::random
    * ClassName::instanceMethodName //List.of("malli","arjun").stream().map(String::length).max();//"malli".length()
    * object::instanceMethodName//List.of("malli","arjun").stream().map(myObj::findLength).max();//myObj.findLength("malli")
    * ClassName::new // constructor reference
    * */
    public static void main(String ... args){
        // Function<Runnable, Thread> threadGenerator = runnable -> new Thread(runnable);
        Function<Runnable, Thread> threadGenerator = Thread::new;
        Thread thread = threadGenerator.apply(() -> System.out.println("Task 1 Execution"));
        thread.start();
        threadGenerator.apply(() -> System.out.println("Task 2 Execution")).start();

        Optional<String> string = Optional.of("string");
        

    }
}
