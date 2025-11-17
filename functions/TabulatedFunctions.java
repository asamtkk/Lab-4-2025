package functions;

import java.io.*;

public class TabulatedFunctions {
    //приватный конструктор - запрещает создание экземпляров класса
    private TabulatedFunctions() {
    }

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) {
        //проверка на количество точек
        if (pointsCount < 2) {
            throw new IllegalArgumentException("Количество точек должно быть не менее 2");
        }
        //проверка, находятся ли границы в области определения
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()) {
            throw new IllegalArgumentException("Границы табуляции выходят за область определения");
        }
        //проверка корректности границ
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой");
        }
        //создаем табулированную функцию
        ArrayTabulatedFunction tabulatedFunc = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        //заполняем значения y, вычисляя их из исходной функции
        for (int i = 0; i < pointsCount; i++) {
            double x = tabulatedFunc.getPointX(i);
            double y = function.getFunctionValue(x);
            tabulatedFunc.setPointY(i, y);
        }
        return tabulatedFunc;
    }

    //выводим табулированную функцию в байтовый поток
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        //создаем поток-обертку
        DataOutputStream dataOut = new DataOutputStream(out);
        //записываем количество точек функции
        dataOut.writeInt(function.getPointsCount());
        //последовательно записываем координаты всех точек функции
        for (int i = 0; i < function.getPointsCount(); i++) {
            dataOut.writeDouble(function.getPointX(i));
            dataOut.writeDouble(function.getPointY(i));
        }
        //сбрасываем буфер, обеспечивая запись всех данных в поток
        //не закрываем поток, так как он может использоваться вызывающим кодом дальше
        dataOut.flush();
    }

    //выводим табулированную функцию из байтового потока
    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        //создаем поток-обертку
        DataInputStream dataIn = new DataInputStream(in);
        //читаем количество точек функции
        int pointsCount = dataIn.readInt();
        //создаем массивы для хранения координат точек
        double[] xValues = new double[pointsCount];
        double[] yValues = new double[pointsCount];
        //последовательно читаем координаты всех точек из потока
        for (int i = 0; i < pointsCount; i++) {
            xValues[i] = dataIn.readDouble();
            yValues[i] = dataIn.readDouble();
        }
        //создаем массив объектов FunctionPoint из прочитанных координат
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(xValues[i], yValues[i]);
        }
        //создаем и возвращаем табулированную функцию
        return new ArrayTabulatedFunction(points);
    }

    //записываем табулированную функцию в символьный поток
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException{
        PrintWriter writer = new PrintWriter(out);

        //записываем количество точек
        writer.print(function.getPointsCount());
        writer.print(" ");
        //записываем координаты всех точек через пробел
        for(int i = 0; i < function.getPointsCount(); i++){
            writer.print(function.getPointX(i));
            writer.print(" ");
            writer.print(function.getPointY(i));
            writer.print(" ");
        }
        //не закрываем поток, а сбрасываем буфер, так как он может использоваться дальше
        writer.flush();
    }
    //читаем табулированную функцию из символьного потока
    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException{
        //StreamTokenizer для чтения чисел
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        //читаем количество точек
        tokenizer.nextToken(); //переходим к следующему токену (числу)
        int pointsCount = (int) tokenizer.nval; //читаем числовое значение
        //создаем массивы для координат
        double[] xValues = new double[pointsCount];
        double[] yValues = new double[pointsCount];
        //читаем координаты всех точек
        for(int i = 0; i < pointsCount; i++){
            //переходим к следуещему токену в потоке, который должен быть числом
            //не делаем проверки, так как считаем, что данные в потоке записаны корректно
            tokenizer.nextToken();
            //сохраняем числовое значение токена как x-координату текущей точки
            xValues[i] = tokenizer.nval;
            //аналогично с y-координатой
            tokenizer.nextToken();
            yValues[i] = tokenizer.nval;
        }
        //создаем функцию из массива точек
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        for(int i = 0; i < pointsCount; i++){
            points[i] = new FunctionPoint(xValues[i], yValues[i]);
        }
        return new ArrayTabulatedFunction(points);
    }
}
