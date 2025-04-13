package presentation;


import business.service.loginService;
import business.validator.ValidateData;

import java.util.Scanner;

public class loginUI {
    private final loginService service;

    public loginUI() {
        this.service = new loginService();
    }

    public static boolean displayLogin(Scanner sc) {
        loginUI loginUi = new loginUI();

        String username = ValidateData.validateString("nhap tk ",sc, 1, 20);

        String password = ValidateData.validateString("nhap mk ",sc, 1, 20);
        if (loginUi.service.login(username, password)) {
            System.out.println("dang nhap thanh cong");
            return true;
        }
        return false;
    }
}

