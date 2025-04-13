package presentation;

import business.service.statistict.statisticSERVICE;
import business.service.statistict.statisticSERVICEImp;
import business.validator.ValidateData;

import java.util.Map;
import java.util.Scanner;

public class statistictUI {
    private statisticSERVICE statisticService;
    public statistictUI() {
        statisticService = new statisticSERVICEImp();
    }
    public static void printMenu(Scanner sc) {
        statistictUI statistictUI = new statistictUI();
        do {
            System.out.println("1. thong ke so luong nhan vien tung phong ban ");
            System.out.println("2. tong so luong nhan vien cua toan he thong ");
            System.out.println("3. phong ban co nhieu nhan vien nhat ");
            System.out.println("4. phong ban co luong cao nhat ");
            System.out.println("5. quay lai");
            int choice = ValidateData.validateInteger("chon chuc nang", sc);
            switch (choice) {
                case 1:
                    statistictUI.printStatisticByDepartment();
                    break;
                case 2:
                    statistictUI.printTotalEmployee();
                    break;
                case 3:
                    statistictUI.printDepartmentWithMostEmployee();
                    break;
                case 4:
                    statistictUI.printDepartmentWithHighestSalary();
                    break;

                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
        }

        public void printStatisticByDepartment() {
            System.out.println("thong ke so luong nhan vien tung phong ban ");
           Map<String,Integer> map =  statisticService.compileEmployeeEachDepartment();
            if(map.isEmpty()){{
                System.out.println("khong co phong ban nao");
        }
            }else {

                for (Map.Entry<String,Integer> entry : map.entrySet()) {
                    System.out.printf("phong ban %s co %10d nhan vien \n", entry.getKey(), entry.getValue());
                }
            }
        }

        public void printTotalEmployee() {
            System.out.println("tong so luong nhan vien cua toan he thong ");
            int total = statisticService.countEmployee();
            System.out.println(total);
        }

        public void printDepartmentWithMostEmployee() {
            System.out.println("phong ban co nhieu nhan vien nhat ");
            Map<String,Integer> map = statisticService.departmentWithMostEmployee();
            if(map.isEmpty()){
                System.out.println("khong co phong ban nao");
            }else {
                for (Map.Entry<String,Integer> entry : map.entrySet()) {
                    System.out.printf("phong ban %s co %10d nhan vien \n", entry.getKey(), entry.getValue());
                }
            }
        }
        public void printDepartmentWithHighestSalary() {
            System.out.println("phong ban co luong cao nhat ");
            Map<String,Integer> map = statisticService.departmentWithhighestSalary();
            if(map.isEmpty()){
                System.out.println("khong co phong ban nao");
            }else {
                for (Map.Entry<String,Integer> entry : map.entrySet()) {
                    System.out.printf("phong ban %s co %10d dong \n", entry.getKey(), entry.getValue());
                }
            }
        }
    }







