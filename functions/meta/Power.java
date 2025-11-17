package functions.meta;

import functions.Function;

public class Power implements Function {
    //f(x) = [base(x)]^power
    private Function base;
    private double power;

    //конструктор функции степени
    public Power(Function base, double power){
        this.base = base;
        this.power = power;
    }

    public double getLeftDomainBorder(){
        //возвращаем левую границу области определения функции
        return base.getLeftDomainBorder();
    }


    public double getRightDomainBorder(){
        //возвращаем правую границу области определения функции
        return base.getRightDomainBorder();
    }

    public double getFunctionValue(double x){
        //проверка что x находится в области определения
        if(x < getLeftDomainBorder() || x > getRightDomainBorder()){
            return Double.NaN;
        }
        return Math.pow(base.getFunctionValue(x), power);
    }
}
