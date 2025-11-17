package functions.basic;

import functions.Function;

public abstract class TrigonometricFunction implements Function {

    //возвращаем левую границу области определения тригонометрических функций
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    //возвращаем правую границу области определения тригонометрических функций
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    //абстрактный метод для вычисления значения тригонометрической функции
    public abstract double getFunctionValue(double x);
}
