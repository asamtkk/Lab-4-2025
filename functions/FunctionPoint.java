package functions;

public class FunctionPoint {
    // приватные поля для храннения координат точки
    private double x;
    private double y;

    //конструктор с параметрами - создает точку с заданными координатами
    public FunctionPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    // конструктор копирования
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    // конструктор по умолчанию
    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    // геттер для получения координаты X точки
    public double getX() {
        return x;
    }

    // сеттер для установки координаты X точки
    public void setX(double x) {
        this.x = x;
    }

    // геттер для получения координаты Y точки
    public double getY() {
        return y;
    }

    // сеттер для установки координаты Y точки
    public void setY(double y) {
        this.y = y;
    }
}