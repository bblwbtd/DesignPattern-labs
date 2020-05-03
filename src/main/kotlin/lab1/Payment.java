package main.kotlin.lab1;

public class Payment {
    private double redemption;
    private double interest;
    private String date;
    private int remainMonth;

    public Payment(double redemption, double interest, int remainMonth) {
        this.redemption = redemption;
        this.interest = interest;
        this.remainMonth = remainMonth;
    }


    public double getRedemption() {
        return this.redemption;
    }

    public void setRedemption(double var1) {
        this.redemption = var1;
    }

    public double getInterest() {
        return this.interest;
    }

    public void setInterest(double var1) {
        this.interest = var1;
    }

    public int getRemainMonth() {
        return remainMonth;
    }

    public void setRemainMonth(int remainMonth) {
        this.remainMonth = remainMonth;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String var1) {
        this.date = var1;
    }

}
