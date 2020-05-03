package lab1;

import lab1.bean.Payment;

import java.util.List;

public interface Strategy {
    List<Payment> calculate(Mortgage mortgage);
}
