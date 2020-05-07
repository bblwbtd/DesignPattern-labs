package lab1;


import lab1.bean.CalculationRequest;
import lab1.bean.Payment;

import java.util.ArrayList;
import java.util.List;

public final class Mortgage {
    private final double initialLoan;
    private final double currentLoan;
    private final int month;
    private final double rate;
    private String thisMonth;
    private final List<Payment> paymentList;
    Strategy strategy;
    private final String startDate;
    private final String endDate;

    //    public Mortgage(String startDate, String endDate, int initialLoan, double rate, String strategy) {
    public Mortgage(CalculationRequest request) {
        super();
        this.startDate = request.start_date;
        this.endDate = request.end_date;
        this.initialLoan = Double.parseDouble(request.initial_amount);
        this.currentLoan = Double.parseDouble(request.initial_amount);
        this.rate = Double.parseDouble(request.interest_rate);
        this.month = (Integer.parseInt(endDate.substring(0, 4)) - Integer.parseInt(startDate.substring(0, 4))) * 12;
        this.paymentList = new ArrayList<Payment>(this.month);
        if (request.strategy.equals("Amortized")) {
            strategy = new AmortizedMortgageStrategy();
        } else {
            strategy = new LinearMortgageStrategy();
        }
    }

    public List<Payment> calculate() {
        return strategy.calculate(this);
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