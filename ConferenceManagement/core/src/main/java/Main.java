import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

public class Main {
    public static void main(String[] args) {

        System.out.println("hello");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config"
                );

        Console console = context.getBean(Console.class);

        System.out.println("bye");
    }
}
