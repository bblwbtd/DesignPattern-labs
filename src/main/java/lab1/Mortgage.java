package lab1;


import lab1.bean.CalculationRequest;
import lab1.bean.Payment;

import java.util.ArrayList;
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

    public void setInitialLoan(double initialLoan) {
        this.initialLoan = initialLoan;
    }

    public double getCurrentLoan() {
        return currentLoan;
    }

    public void setCurrentLoan(double currentLoan) {
        this.currentLoan = currentLoan;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(String thisMonth) {
        this.thisMonth = thisMonth;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}