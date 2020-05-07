package lab1;

import lab1.bean.Payment;

import java.text.ParseException;
import java.util.List;

public interface Strategy {
    List<Payment> calculate(Mortgage mortgage) throws ParseException;
}
