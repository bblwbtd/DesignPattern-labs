import lab1.AmortizedMortgageStrategy;
import lab1.Mortgage;
import lab1.bean.CalculationRequest;
import lab1.bean.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmortizedMortgageStrategyTest {

    AmortizedMortgageStrategy strategy = new AmortizedMortgageStrategy();

    @Test
    void testCalculate(){
        List<Payment> result = strategy.calculate(new Mortgage(new CalculationRequest("2010-5-1","2050-5-1","3000000","0.06","Amortized")));
        assertEquals(16506.41,result.get(0).getRedemption());
        assertEquals(16506.41,result.get(1).getRedemption());
        assertEquals(2998493.59,result.get(0).getRest());
        assertEquals(2996979.65,result.get(1).getRest());
        assertTrue(result.get(result.size() - 2).getRest() > 0);
        assertTrue(result.get(result.size() - 1).getRest() <= 0);
        assertEquals(480, result.size());
        assertEquals(1,result.get(result.size() - 2).getRemainMonth());
        assertEquals(0,result.get(result.size() - 1).getRemainMonth());
    }
}
