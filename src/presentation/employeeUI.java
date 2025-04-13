package presentation;

import business.model.Department;
import business.model.EEmployee;
import business.model.Employee;
import business.model.Gender;
import business.service.department.departmentSERVICE;
import business.service.department.departmentSERVICEImp;
import business.service.employee.employeeSERVICE;
import business.service.employee.employeeSERVICEImp;
import business.validator.ValidateData;

import java.util.List;
import java.util.Scanner;

public class employeeUI {
    private employeeSERVICE employeeService;
    private departmentSERVICE departmentService = new departmentSERVICEImp();
    public employeeUI() {
        employeeService = new employeeSERVICEImp();
    }
    public static void displayEmployeeMenu(Scanner sc) {
        employeeUI employeeUI = new employeeUI();
        do {
            System.out.println("1. danh sach nhan vien");
            System.out.println("2. them moi nv (chi them vao phong ban dang hoat dong )");
            System.out.println("3. cap nhat nv ");
            System.out.println("4. xoa nv(cap nhat lai thanh INACTIVE)");
            System.out.println("5. tim kiem nv theo ten va khoang tuoi");

            System.out.println("6. sap xep nhan vien theo luong giam dan");
            System.out.println("7. quay lai");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    employeeUI.displayEmployees(sc);
                    break;
                case 2:
                    employeeUI.insertEmployee(sc);
                    break;
                case 3:
                    employeeUI.updateEmployee(sc);
                    break;
                case 4:
                    employeeUI.deleteEmployee(sc);
                    break;
                case 5:
                        employeeUI.findEmployeeByName(sc);
                    break  ;
                case 6:
                    employeeUI.sortEmployee();
                    break;
                    case 7:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }
    public  void displayEmployees(Scanner sc ) {
        int current_page = 1;
        int total_page = employeeService.getTotalPage();
        do{
            System.out.println(current_page +"/" +employeeService.getTotalPage());
            List<Employee> list = employeeService.getDataPag(current_page);
            for (Employee de : list) {
                System.out.println(de);
            }
            System.out.println("1. trang sau | 2. trang truoc | 3. quay lai");
            int choice = Integer.parseInt(sc.nextLine());
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
    public void insertEmployee(Scanner sc) {
        Department department = departmentService.findById(employeeService.choiceDepartment(sc));
        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.inputData(sc);
        if (employeeService.insert(employee)) {
            System.out.println("them moi thanh cong");
        } else {
            System.out.println("them moi khong thanh cong");
        }


    }
    public void updateEmployee(Scanner sc) {

        Employee employee = employeeService.findById(ValidateData.validateInteger("nhap id nhan vien", sc));
        if (employee == null) {
            System.out.println("khong tim thay nhan vien");
            return;
        }else {
            System.out.println(employee);
            System.out.println("nhap id phong ban");
            employee.setDepartment(departmentService.findById(employeeService.choiceDepartment(sc)));
            employee.inputData(sc);
            if (employeeService.update(employee)) {
                System.out.println("sua thanh cong");
            } else {
                System.out.println("sua that bai");
            }
        }



    }
    public void deleteEmployee(Scanner sc) {
        Employee employee = employeeService.findById(ValidateData.validateInteger("nhap id nhan vien", sc));
        if (employee == null) {
            System.out.println("khong tim thay nhan vien");
            return;
        } else {

            employee.setStatus(EEmployee.inactive);
            if (employeeService.update(employee)) {
                System.out.println("xoa thanh cong");
            } else {
                System.out.println("xoa that bai");
            }
        }
    }
    public void findEmployeeByName(Scanner sc) {
        String name = ValidateData.validateString("nhap ten nhan vien", sc, 0, 100);
        int ageFrom = ValidateData.validateInteger("nhap tuoi tu", sc);
        int ageTo = ValidateData.validateInteger("nhap tuoi den", sc);
        List<Employee> list = employeeService.findEmployeeByNameAndAge(name, ageFrom, ageTo);
        if (list == null) {
            System.out.println("khong tim thay nhan vien");
            return;
        }
        for(Employee e : list){
            System.out.println(e);
        }
    }
    public void sortEmployee() {
       List<Employee> list = employeeService.sortBySalary();
        if (list == null) {
            System.out.println("khong tim thay nhan vien");
            return;
        }
        for(Employee e : list){
            System.out.println(e);
        }

    }
}
