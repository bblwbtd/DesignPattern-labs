import lab1.LinearMortgageStrategy;
import lab1.Mortgage;
import lab1.bean.CalculationRequest;
import lab1.bean.Payment;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinearMortgageStrategyTest {
    LinearMortgageStrategy strategy = new LinearMortgageStrategy();

    @Test
    void testCalculate(){
        List<Payment> result = strategy.calculate(new Mortgage(new CalculationRequest("2000-5-1","2050-5-1","3000000","0.06","Linear")));
        assertEquals(20000.0,result.get(0).getRedemption());
        assertEquals(19975.0,result.get(1).getRedemption());
        assertEquals(2995000,result.get(0).getRest());
        assertEquals(2990000,result.get(1).getRest());
        assertTrue(result.get(result.size() - 2).getRest() > 0);
        assertTrue(result.get(result.size() - 1).getRest() <= 0);
        assertEquals(600, result.size());
        assertEquals(1,result.get(result.size() - 2).getRemainMonth());
        assertEquals(0,result.get(result.size() - 1).getRemainMonth());
    }
}
