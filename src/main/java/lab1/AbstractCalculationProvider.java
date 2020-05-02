package lab1;

import lab1.bean.CalculationRequest;
import lab1.bean.CalculationResult;

public abstract class AbstractCalculationProvider {

    private static AbstractCalculationProvider provider;

    protected AbstractCalculationProvider() {
    }

    public static AbstractCalculationProvider getInstance() {
        if (provider == null) {
            provider = new CalculationProvider();
        }
        return provider;
    }

    public abstract CalculationResult performCalculation(CalculationRequest request);

}