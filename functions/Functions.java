package functions;

import functions.meta.*;

public class Functions {
    //приватный конструктор - запрещает создание экземпляров класса
    private Functions(){
    }
    //функция сдвига
    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }
    //функция масштабирования
    public static Function scale(Function f, double scaleX, double scaleY){
        return new Scale(f, scaleX, scaleY);
    }
    //функция возведения в степень
    public static Function power(Function f, double power){
        return new Power(f, power);
    }
    //функция суммы
    public static Function sum(Function f1, Function f2){
        return new Sum(f1, f2);
    }
    //функция произведения
    public static Function mult(Function f1, Function f2){
        return new Mult(f1, f2);
    }
    //композиция функций
    public static Function composition(Function f1, Function f2){
        return new Composition(f1, f2);
    }
}

