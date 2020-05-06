package lab1.bean;

public class Payment {
    private double redemption;
    private double interest;
    private String date;
    private int remainMonth;
    private double rest;

    public Payment(double redemption,double rest, double interest, int remainMonth) {
        this.redemption = redemption;
        this.rest = rest;
        this.interest = interest;
        this.remainMonth = remainMonth;
    }

    public double getRest() {
        return rest;
    }

    public double getRedemption() {
        return this.redemption;
    }

    public double getInterest() {
        return this.interest;
    }

    public int getRemainMonth() {
        return remainMonth;
    }

    public String getDate() {
        return this.date;
    }

}
