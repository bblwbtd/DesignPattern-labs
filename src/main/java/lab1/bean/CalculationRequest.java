package lab1.bean;

public class CalculationRequest {
    public String strategy;
    public String initial_amount;
    public String interest_rate;

    public CalculationRequest(String strategy, String initial_amount, String interest_rate) {
        this.strategy = strategy;
        this.initial_amount = initial_amount;
        this.interest_rate = interest_rate;
    }
}
