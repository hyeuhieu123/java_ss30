package business.model;

import business.validator.ValidateData;

import java.util.Scanner;

public class Department {
    private  int id;
    private String name;
    private String des;
    private EDepartment status;

    public Department() {
    }

    public void inputData(Scanner sc ){
//      this.id =   ValidateData.validateInteger("nhap id phong ban", sc);
       this.name=  ValidateData.validateString("nhap ten phong ban", sc,10,100);
       this.des= ValidateData.validateString("nhap mo ta", sc,0,250);
        this.status= ValidateData.validateEnum("nhap trang thai", sc, EDepartment.class);
//        System.out.println(this.toString());
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public EDepartment getStatus() {
        return status;
    }

    public void setStatus(EDepartment status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", status='" + status + '\'' +
                '}';
    }



}
