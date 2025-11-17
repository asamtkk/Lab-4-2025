package functions;

public interface Function {
    //получаем левую границу области определения функции
    public double getLeftDomainBorder();
    //получаем правую границу области определения функции
    public double getRightDomainBorder();
    //вычисляем значение функции в заданной точке
    public double getFunctionValue(double x);
}
