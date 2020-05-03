package lab1;

import lab1.bean.CalculationRequest;
import lab1.bean.CalculationResult;

public class CalculationProvider extends AbstractCalculationProvider {
    CalculationResult performCalculation(CalculationRequest request) {
//        Mortgage mortgage = new Mortgage(request);

        Mortgage mortgage = new Mortgage(request);
        return new CalculationResult(mortgage.calculate());
    }
}