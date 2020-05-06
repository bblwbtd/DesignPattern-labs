package lab1;

import lab1.bean.Payment;

import java.util.ArrayList;
import java.util.List;

public class LinearMortgageStrategy implements Strategy {
    private double monthRate;
    public List<Payment> calculate(Mortgage mortgage) {
        this.monthRate = mortgage.getRate()/12;

        List<Payment> paymentList = new ArrayList<Payment>(mortgage.getMonth());
        int remainMonth = mortgage.getMonth();
        double currentLoan = mortgage.getCurrentLoan();
        double everyMonthPayBack = (double) Math.round(mortgage.getInitialLoan()/mortgage.getMonth()* 100) / 100;

        for(int i=0;i<mortgage.getMonth();i++) {
            double interest = (double) Math.round(currentLoan * monthRate * 100) / 100;
            double redemption = (double) Math.round((interest + everyMonthPayBack) * 100) / 100;
            currentLoan = currentLoan - everyMonthPayBack;
            remainMonth = remainMonth - 1;
            paymentList.add(i,new Payment(redemption, currentLoan,interest,remainMonth));

        }
        return paymentList;
    }
}
