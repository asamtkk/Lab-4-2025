package functions.basic;

import functions.Function;

public class Log implements Function {
    //основание логарифма
    private double base;

    //конструктор логарифма
    public Log(double base){
        //проверка на валидность
        if (base <= 0 || base == 1){
            throw new IllegalArgumentException("Основание логарифма должно быть > 0 и не равным 1");
        }
        this.base = base;
    }

    //вычисляем значение логарифма в точке x
    public double getFunctionValue(double x) {
        if (x <= 0) {
            return Double.NaN;
        }
        //используем формулу замены основания
        return Math.log(x) / Math.log(base);
    }

    //возвращаем левую границу области определения
    public double getLeftDomainBorder() {
        //открытый интервал от 0
        return 0;
    }

    //возвращаем правую границу области определения
    public double getRightDomainBorder() {
        //интервал до +бесконечности
        return Double.POSITIVE_INFINITY;
    }
}
