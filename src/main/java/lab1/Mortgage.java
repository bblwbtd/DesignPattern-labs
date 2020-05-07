package lab1;


import lab1.bean.CalculationRequest;
import lab1.bean.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class Mortgage {
    private double initialLoan;
    private double currentLoan;
    private int month;
    private double rate;
    private String thisMonth;
    private List<Payment> paymentList;
    Strategy strategy;
    private String startDate;
    private String endDate;

    //    public Mortgage(String startDate, String endDate, int initialLoan, double rate, String strategy) {
    public Mortgage(CalculationRequest request) throws ParseException {
        super();
        this.startDate = request.startDate;
        this.endDate = request.endDate;
        this.initialLoan = Double.parseDouble(request.initial_amount);
        this.currentLoan = Double.parseDouble(request.initial_amount);
        this.rate = Double.parseDouble(request.interest_rate);
//        this.month = (Integer.parseInt(endDate.substring(0, 4)) - Integer.parseInt(startDate.substring(0, 4))) * 12;
        this.month = calculateMonth(startDate,endDate);
        this.paymentList = new ArrayList<Payment>(this.month);
        if (request.strategy.equals("Amortized")) {
            strategy = new AmortizedMortgageStrategy();
        } else {
            strategy = new LinearMortgageStrategy();
        }
    }

    public List<Payment> calculate() throws ParseException {
        return strategy.calculate(this);
    }

    int calculateMonth(String startDate,String endDate) throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(startDate));
        c2.setTime(sdf.parse(endDate));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
        return month + result + 1 ;


    }

    public double getInitialLoan() {
        return initialLoan;
    }


    public double getCurrentLoan() {
        return currentLoan;
    }


    public int getMonth() {
        return month;
    }

    public double getRate() {
        return rate;
    }

    public String getThisMonth() {
        return thisMonth;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}