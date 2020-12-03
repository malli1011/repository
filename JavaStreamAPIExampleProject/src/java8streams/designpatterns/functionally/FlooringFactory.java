package java8streams.designpatterns.functionally;

import java8streams.designpatterns.functionally.model.ConcreteFlooring;
import java8streams.designpatterns.functionally.model.CorkFlooring;
import java8streams.designpatterns.functionally.model.Flooring;
import java8streams.designpatterns.functionally.model.WoodenFlooring;

import java.util.function.Supplier;

public class FlooringFactory {

    public static Flooring getFlooring(int minTemp, int maxTemp) {

        Supplier<Flooring> flooring;
        //Flooring flooring;
        if (minTemp <= 5 && maxTemp <= 20) {
            flooring = WoodenFlooring::new;
            //flooring = new WoodenFlooring();
        } else if (minTemp <= 5 && maxTemp >= 45) {
            flooring = CorkFlooring::new;
        } else {
            flooring = ConcreteFlooring::new;
        }
        return flooring.get();
    }

  void createObject(Supplier<Object> supplier){
        Object obj = supplier.get();
  }


}
