package lab1;

import lab1.bean.Payment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LinearMortgageStrategy implements Strategy {
    private double monthRate;
    public List<Payment> calculate(Mortgage mortgage) throws ParseException {
        this.monthRate = mortgage.getRate()/12;

        List<Payment> paymentList = new ArrayList<Payment>(mortgage.getMonth());
        int remainMonth = mortgage.getMonth();
        String thisMonth = mortgage.getStartDate();
        double currentLoan = mortgage.getCurrentLoan();
        double everyMonthPayBack = mortgage.getInitialLoan()/mortgage.getMonth();

        for(int i=0;i<mortgage.getMonth();i++) {
            double interest = currentLoan * monthRate;
            double redemption = interest + everyMonthPayBack;

            currentLoan = currentLoan - everyMonthPayBack;
            remainMonth = remainMonth - 1;
            paymentList.add(i,new Payment((double) Math.round(redemption * 100) / 100,
                    (double) Math.round(currentLoan * 100) / 100,
                    (double) Math.round(interest * 100) / 100,remainMonth,
                    thisMonth));

            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(thisMonth));
            c.add(Calendar.MONTH, +1);
            thisMonth = df.format(c.getTime());

        }
        return paymentList;
    }
}
