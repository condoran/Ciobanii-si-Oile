package ui;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.List;

@Component
public class Console {
    @Autowired
    private UserService userService;

    public void run(){
        userService.save(new User("timo", "cal", "Copaci", "Natura", "NuAre", "NiciAsta", false));

        List<User> users = userService.getAll();
        users.forEach(System.out::println);
    }
}
