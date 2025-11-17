package functions.meta;

import functions.Function;

public class Composition implements Function{
    //f(x) = f1(f2(x))
    private Function func1;
    private Function func2;

    //конструктор функции композиции
    public Composition(Function func1, Function func2){
        this.func1 = func1;
        this.func2 = func2;
    }
    public double getLeftDomainBorder(){
        //возвращаем левую границу области определения
        return func2.getLeftDomainBorder();
    }
    public double getRightDomainBorder(){
        //возвращаем правую границу области определения
        return func2.getRightDomainBorder();
    }
    public double getFunctionValue(double x){
        //проверка области определения функции
        if(x < getLeftDomainBorder() || x> getRightDomainBorder()){
            return Double.NaN;
        }
        // вычисляем композицию функций
        return func1.getFunctionValue(func2.getFunctionValue(x));
    }
}
