import business.validator.ValidateData;
import presentation.departmentUI;
import presentation.employeeUI;
import presentation.loginUI;
import presentation.statistictUI;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            if (loginUI.displayLogin(sc)) {
                boolean isExit = false  ;
                do {
                    System.out.println("***************APPLICATION MENU**************");
                    System.out.println("1. quan ly phong ban");
                    System.out.println("2. quan ly nhan vien");
                    System.out.println("3. thong ke ");
                    System.out.println("4. dang xuat ");

                    int choice = ValidateData.validateInteger("nhap lua chon", sc);
                    switch (choice) {
                        case 1:
                            departmentUI.displayDepartmentMenu(sc);
                            break;
                        case 2:
                            employeeUI.displayEmployeeMenu(sc);
                            break;
                        case 3:
                            statistictUI.printMenu(sc);
                            break;
                        case 4:
                          isExit=true;
                                    break;

                    }
                } while (!isExit);
            } else {
                System.out.println("sai tk hoac mk");
            }
        } while (true);

    }
}