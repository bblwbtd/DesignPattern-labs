package main.kotlin.lab1;

import main.kotlin.lab1.bean.CalculationRequest;

import java.util.List;


public class main {
    public static void main(String[] args) {
//        Mortgage mortgage = new Mortgage("2020/4/1", "2050/4/1", 1000000, 0.06D, (Strategy)(new AmortizedMortgageStrategy()));
//        Mortgage mortgage = new Mortgage("2020/4/1", "2050/4/1", 1000000, 0.06D, "Amortized");

        CalculationRequest cr = new CalculationRequest("2020/4/1", "2050/4/1", "1000000", "0.06D", "Linear");
        CalculationProvider cp = new CalculationProvider();
        ;
        for(Payment p:cp.performCalculation(cr).result){
            System.out.print("当期：" + p.getRedemption() + " ");
            System.out.print("利息：" + p.getInterest() + " ");
            System.out.println("剩余" + p.getRemainMonth() + "月 ");

        }

    }

}
