package lab1;

import lab1.bean.CalculationRequest;
import lab1.bean.CalculationResult;

import java.text.ParseException;

public class CalculationProvider extends AbstractCalculationProvider {
    public CalculationResult performCalculation(CalculationRequest request) throws ParseException {
//        Mortgage mortgage = new Mortgage(request);

        Mortgage mortgage = new Mortgage(request);
        return new CalculationResult(mortgage.calculate());
    }
}