import lab1.Mortgage;
import lab1.bean.CalculationRequest;
import lab1.bean.Payment;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageTest {

    private final Mortgage mortgage = new Mortgage(new CalculationRequest("2020-05","2050-04","3000000","0.06","Amortized"));

    public MortgageTest() throws ParseException {
    }

    @Test
    void testCalculate() throws ParseException {
        List<Payment> result = mortgage.calculate();
        assertEquals(360, result.size());
        assertEquals(0, result.get(result.size() - 1).getRemainMonth());
    }
}
