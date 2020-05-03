package main.kotlin.lab1;

import main.kotlin.lab1.bean.CalculationRequest;
import main.kotlin.lab1.bean.CalculationResult;

public class CalculationProvider extends AbstractCalculationProvider {
    CalculationResult performCalculation(CalculationRequest request) {
//        Mortgage mortgage = new Mortgage(request);

        Mortgage mortgage = new Mortgage(request);
        return new CalculationResult(mortgage.calculate());
    }
}