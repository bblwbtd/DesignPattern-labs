package lab1.bean;

public class MonthlyDetail {
    public double pay_back;
    public double rest;
    public double interest;
    public int remaining_month;

    public MonthlyDetail(double pay_back, double rest, double interest, int remaining_month) {
        this.pay_back = pay_back;
        this.rest = rest;
        this.interest = interest;
        this.remaining_month = remaining_month;
    }
}
