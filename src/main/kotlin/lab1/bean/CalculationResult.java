package main.kotlin.lab1.bean;


import main.kotlin.lab1.Payment;

import java.util.List;

public class CalculationResult {
    public List<Payment> result;

    public CalculationResult(List<Payment> result) {
        this.result = result;
    }
}
