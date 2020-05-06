import lab1.Mortgage;
import lab1.bean.CalculationRequest;
import lab1.bean.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageTest {

    private final Mortgage mortgage = new Mortgage(new CalculationRequest("2020-5-1","2050-5-1","3000000","0.06","Amortized"));

    @Test
    void testCalculate(){
        List<Payment> result = mortgage.calculate();
        assertEquals(360, result.size());
        assertEquals(0, result.get(result.size() - 1).getRemainMonth());
    }
}
