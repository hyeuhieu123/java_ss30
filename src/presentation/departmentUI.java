package presentation;


import business.model.Department;
import business.service.department.departmentSERVICE;
import business.service.department.departmentSERVICEImp;
import business.validator.ValidateData;

import java.util.List;
import java.util.Scanner;

public class departmentUI {
    private final departmentSERVICE departmentService;

    public departmentUI() {
        this.departmentService = new departmentSERVICEImp();
    }
    public static void displayDepartmentMenu(Scanner sc) {
        departmentUI departmentUI = new departmentUI();
        do {
            System.out.println("1. danh sach phong ban");
            System.out.println("2. them moi phong ban");
            System.out.println("3. sua phong ban");
            System.out.println("4. xoa phong ban");
            System.out.println("5. tim phong ban");
            System.out.println("6. quay lai");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    departmentUI.displayDepartments(sc);
                    break;
                case 2:
                    departmentUI.insertDepartment(sc);
                    break;
                case 3:
                    departmentUI.updateDepartment(sc);
                    break;
                case 4:
                    departmentUI.deleteDepartment(sc);
                    break;
                case 5:
                    departmentUI.finDepartmentByName(sc);
                    break  ;
                case 6:
                    return;

                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }
    public void displayDepartments(Scanner sc ) {
        int current_page = 1;
        int total_page = departmentService.getTotalPage();
        do{
            System.out.println(current_page +"/" +departmentService.getTotalPage());
            List<Department> list = departmentService.getDataPag(current_page);
            for (Department de : list) {
                System.out.println(de);
            }
            System.out.println("1. trang sau | 2. trang truoc | 3. quay lai");
            int choice = ValidateData.validateInteger("nhap lua chon", sc);
            switch (choice){
                case 1:
                    if (current_page < total_page) current_page++;
                    break;
                case 2:
                    if (current_page > 1) current_page--;
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        }while (true);
    }

    public void insertDepartment(Scanner sc) {
        Department department = new Department();
        department.inputData(sc);
        if (departmentService.insert(department)) {
            System.out.println("them moi thanh cong");
        } else {
            System.out.println("them moi that bai");
        }
    }
    public void updateDepartment(Scanner sc){
        System.out.println("nhap id phong ban can sua");
        Department department = departmentService.findById(Integer.parseInt(sc.nextLine()));
        if (department != null) {
            System.out.println(department);
            department.inputData(sc);
            if (departmentService.update(department)) {
                System.out.println("sua thanh cong");
            } else {
                System.out.println("sua that bai");
            }
        } else {
            System.out.println("khong tim thay phong ban");
        }


    }
    public void deleteDepartment(Scanner sc) {
        System.out.println("nhap id phong ban can xoa");

        Department department = departmentService.findById(departmentService.selectActiveDepartment(sc));
        if (department != null) {
            if( departmentService.delete(department)) {
                System.out.println("xoa thanh cong");
            } else {
                System.out.println("xoa that bai");
            }
        } else {
            System.out.println("khong tim thay phong ban");
        }
    }
    public void finDepartmentByName(Scanner sc) {

        String name = ValidateData.validateString("nhap ten phong ban", sc, 0, 100);
        List<Department> list = departmentService.findDepartmentByName(name);

            for (Department de : list) {
                System.out.println(de);
            }

    }
}
