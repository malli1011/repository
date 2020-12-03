package java8streams.designpatterns.functionally;

import java8streams.designpatterns.functionally.model.Burger;
import java8streams.designpatterns.functionally.model.BurgerShop;

public class DecoratorPattern {

    public static void main(String[] args) {

        //new BuilderDecorator(builder -> builder.withRevenue(),builder -> builder.withRevenueError()).apply();
       // new BuilderDecorator(builder -> builder.withAccocuntType(),builder -> builder.withTypeError()).apply();

        Burger myOrder = new BurgerShop(burger -> burger.addCheese())
                .use(
                        new BurgerShop(burger -> burger.addVeggies())
                                .use(
                                        new Burger()
                                )
                );

        System.out.println("I get " + myOrder);

    }

}
