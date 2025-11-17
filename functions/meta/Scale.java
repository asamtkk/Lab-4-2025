package functions.meta;

import functions.Function;

public class Scale implements  Function {
    //f(x) = scaleY * func(x / scaleX)
    private Function func;
    private double scaleX;
    private double scaleY;

    //конструктор масштабированной функции
    public Scale(Function func, double scaleX, double scaleY) {
        this.func = func;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public double getLeftDomainBorder() {
        //возвращаем левую границу области определения
        return func.getLeftDomainBorder() * scaleX;
    }

    //возвращаем правую границу области определения
    public double getRightDomainBorder() {
        return func.getRightDomainBorder() * scaleX;
    }

    public double getFunctionValue(double x) {
        //проверяем область определения данной функции
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        //вычисляем значение масштабированной функции в точке x
        return scaleY * func.getFunctionValue(x / scaleX);
    }
}
