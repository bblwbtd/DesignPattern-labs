package main.kotlin.lab1;

import java.util.List;

public interface Strategy {
    public List<Payment> calculate(Mortgage mortgage);
}
