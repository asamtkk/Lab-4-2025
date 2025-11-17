package functions.meta;

import functions.Function;

public class Sum implements Function{
    private Function func1;
    private Function func2;

    //конструктор функции суммы
    public Sum(Function func1, Function func2){
        this.func1 = func1;
        this.func2 = func2;
    }

    public double getLeftDomainBorder() {
        //возвращаем пересечение левыъ границ
        return Math.max(func1.getLeftDomainBorder(), func2.getLeftDomainBorder());
    }

    public double getRightDomainBorder() {
        //возвращаем пересечение правых границ
        return Math.min(func1.getRightDomainBorder(), func2.getRightDomainBorder());
    }

    public double getFunctionValue(double x) {
        //проверка что x находится в области определения
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()){
            return Double.NaN;
        }
        //вычисляем суммы функций
        return func1.getFunctionValue(x) + func2.getFunctionValue(x);
    }
}
