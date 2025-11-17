package functions.meta;

import functions.Function;

public class Shift implements Function{
    //f(x) = func(x - shiftX) + shiftY
    private Function func;
    private double shiftX;
    private double shiftY;

    //конструктор сдвинутой функции
    public Shift(Function func, double shiftX, double shiftY){
        this.func = func;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }
    public double getLeftDomainBorder() {
        //возвращакм левую границу области определения
        return func.getLeftDomainBorder() + shiftX;
    }
    public double getRightDomainBorder() {
        //возвращаем правую границу области определения
        return func.getRightDomainBorder() + shiftX;
    }
    public double getFunctionValue(double x) {
        //проверка области определения
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        //вычисляем значение сдвинутой функции в точке x
        return func.getFunctionValue(x - shiftX) + shiftY;
    }
}
