package lab1.bean;


public class CalculationRequest {
    public String strategy;
    public String initial_amount;
    public String interest_rate;
    public String startDate;
    public String endDate;

    public CalculationRequest(String startDate, String endDate, String initial_amount, String interest_rate, String strategy) {
        this.strategy = strategy;
        this.initial_amount = initial_amount;
        this.interest_rate = interest_rate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}