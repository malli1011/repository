package java8streams.lambdas;

//A closure is a function that refers to free variables in its lexical context.
public class Closure {
    public static void main(String[] args) {
        int val = 10;
        Task lambda = () -> {
            System.out.println(val);
            System.out.println("Task Completed");
        };
        // here the variable val is defined inside main method, but we are passing that value to printValue method through a lambda.
        // here the lambda will hold the value of variable val inside the printValue function.
        printValue(lambda);
    }

    private static void printValue(Task task){
        task.doTask();
    }
}

@FunctionalInterface
interface Task {
    void doTask();
}
