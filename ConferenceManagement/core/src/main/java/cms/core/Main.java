package cms.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import cms.core.ui.Console;
//import ui.Console;

public class Main {
    public static void main(String[] args) {

        System.out.println("hello");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "cms/core/config"
                );

        Console console = context.getBean(Console.class);
        console.run();

        System.out.println("bye");
    }
}
