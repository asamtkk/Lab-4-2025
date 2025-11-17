package functions.basic;

import functions.Function;

public class Exp implements Function {
    public double getLeftDomainBorder() {
        //возвращаем левую границу области определения
        return Double.NEGATIVE_INFINITY;
    }
    public double getRightDomainBorder() {
        // возвращаем правую границу области определения
        return Double.POSITIVE_INFINITY;
    }
    public double getFunctionValue(double x) {
        // вычисляем значение экспоненты в точке x
        return Math.exp(x);
    }
}
