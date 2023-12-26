package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.model.User;

@Getter
@Setter
public class AppData {

    private static AppData instance;
    private User user;

    private AppData() {
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }
}
