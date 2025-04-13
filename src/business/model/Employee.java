package business.model;

import business.validator.ValidateData;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Gender sex;
    private EEmployee status;
    private Department department;
    private LocalDate bod;
    private double salary;

    public void inputData(Scanner sc) {
        this.name = ValidateData.validateString("nhap ten nhan vien",sc, 10, 100);
        this.email=ValidateData.validateEmail("nhap email", sc);
        this.phone=ValidateData.validatePhone("nhap so dien thoai", sc);
        this.sex =ValidateData.validateEnum("nhap gioi tinh",sc,Gender.class);
        this.salary=ValidateData.validateDouble("nhap luong", sc,0, 10000000);
        this.bod=ValidateData.validateDate("nhap ngay sinh", sc);
        this.status=ValidateData.validateEnum("nhap trang thai", sc, EEmployee.class);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public EEmployee getStatus() {
        return status;
    }

    public void setStatus(EEmployee status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getBod() {
        return bod;
    }

    public void setBod(LocalDate bod) {
        this.bod = bod;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", department=" + department +
                ", bod=" + bod +
                ", salary=" + salary +
                '}';
    }

    public Employee() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee employee = new Employee();
        employee.setBod(Date.valueOf("2000-01-01").toLocalDate());
        System.out.println(employee.getBod());
    }
}
