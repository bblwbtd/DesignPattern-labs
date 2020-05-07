package lab1.bean;

public class Payment {
    private double redemption;
    private double interest;
    private String date;
    private int remainMonth;
    private String thisMonth;
    private double rest;



    public Payment(double redemption, double rest, double interest, int remainMonth, String thisMonth) {
        this.redemption = redemption;
        this.rest = rest;
        this.interest = interest;
        this.thisMonth = thisMonth;
        this.remainMonth = remainMonth;
    }

    public double getRest() {
        return rest;
    }

    public String getThisMonth() {
        return thisMonth;
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
