package java8streams.designpatterns.functionally;

import java.util.function.Consumer;

public class MyArrayList {

    Object[] elements;

    public MyArrayList(Object[] elements) {
        this.elements = elements;
    }


    public void forEach(Consumer<Object> action) {

        for(int i = 0 ; i < elements.length ; i++) {
            action.accept(elements[i]);
        }

    }

    public static void main(String[] args) {

        MyArrayList list = new MyArrayList(new Object[] {1,3,5,67,8});

        list.forEach(System.out::println);

    }
}
