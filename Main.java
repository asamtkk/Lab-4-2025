import functions.*;
import functions.basic.*;
import functions.meta.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        System.out.println("1. ТЕСТИРВОАНИЕ SIN И COS:");
        System.out.println("==========================");
        Sin sin = new Sin();
        Cos cos = new Cos();
        System.out.println("Значения sin(x) и cos(x) на [0, π] с шагом 0.1:");
        for (double x = 0; x <= Math.PI; x += 0.1) {
            System.out.printf("x=%.1f: sin=%.3f, cos=%.3f%n", x, sin.getFunctionValue(x), cos.getFunctionValue(x));
        }
        System.out.println();

        // 2. табулирование sin и cos, сравнение с оригиналами
        System.out.println("2. ТАБУЛИРОВАНИЕ SIN И COS:");
        System.out.println("============================");
        TabulatedFunction tabulatedSin = TabulatedFunctions.tabulate(sin, 0, Math.PI, 10);
        TabulatedFunction tabulatedCos = TabulatedFunctions.tabulate(cos, 0, Math.PI, 10);
        System.out.println("Сравнение оригинальных и табулированных функций:");
        for (double x = 0; x <= Math.PI; x += 0.1) {
            double originalSin = sin.getFunctionValue(x);
            double tabSin = tabulatedSin.getFunctionValue(x);
            double originalCos = cos.getFunctionValue(x);
            double tabCos = tabulatedCos.getFunctionValue(x);
            System.out.printf("x=%.1f: sin(ориг)=%.3f, sin(табл)=%.3f, cos(ориг)=%.3f, cos(табл)=%.3f%n",
                    x, originalSin, tabSin, originalCos, tabCos);
        }
        System.out.println();

        // 3. сумма квадратов табулированных sin и cos
        System.out.println("3. СУММА КВАДРАТОВ ТАБУЛИРОВАННЫХ SIN И COS:");
        System.out.println("============================================");
        Function sinSquared = Functions.power(tabulatedSin, 2);
        Function cosSquared = Functions.power(tabulatedCos, 2);
        Function sumOfSquares = Functions.sum(sinSquared, cosSquared);
        System.out.println("Значения sin²(x) + cos²(x) на [0, π] с шагом 0.1:");
        for (double x = 0; x <= Math.PI; x += 0.1) {
            double result = sumOfSquares.getFunctionValue(x);
            System.out.printf("x=%.1f: sin²+cos²=%.3f%n", x, result);
        }
        System.out.println("\nИсследование влияния количества точек:");
        for (int pointsCount : new int[]{5, 10, 20}) {
            TabulatedFunction sinTab = TabulatedFunctions.tabulate(new Sin(), 0, Math.PI, pointsCount);
            TabulatedFunction cosTab = TabulatedFunctions.tabulate(new Cos(), 0, Math.PI, pointsCount);
            Function sum = Functions.sum(Functions.power(sinTab, 2), Functions.power(cosTab, 2));
            System.out.printf("Точек: %d, значение в π/2: %.6f%n", pointsCount, sum.getFunctionValue(Math.PI/2));
        }
        System.out.println();

        // 4. работа с файлами - экспонента
        System.out.println("4. РАБОТА С ФАЙЛАМИ - ЭКСПОНЕНТА:");
        System.out.println("================================");
        try {
            TabulatedFunction expFunction = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);
            FileWriter writer = new FileWriter("exp_function.txt");
            TabulatedFunctions.writeTabulatedFunction(expFunction, writer);
            writer.close();
            FileReader reader = new FileReader("exp_function.txt");
            TabulatedFunction readExpFunction = TabulatedFunctions.readTabulatedFunction(reader);
            reader.close();
            System.out.println("Сравнение оригинальной и прочитанной экспоненты:");
            for (double x = 0; x <= 10; x += 1) {
                double original = expFunction.getFunctionValue(x);
                double read = readExpFunction.getFunctionValue(x);
                System.out.printf("x=%.0f: оригинал=%.3f, прочитано=%.3f%n", x, original, read);
            }
        } catch (IOException e) {
            System.out.println("Ошибка работы с файлом: " + e.getMessage());
        }
        System.out.println();

        // 5. работа с файлами - логарифм
        System.out.println("5. РАБОТА С ФАЙЛАМИ - ЛОГАРИФМ:");
        System.out.println("==============================");
        try {
            TabulatedFunction logFunction = TabulatedFunctions.tabulate(new Log(Math.E), 0.1, 10, 11);
            FileOutputStream fos = new FileOutputStream("log_function.dat");
            TabulatedFunctions.outputTabulatedFunction(logFunction, fos);
            fos.close();
            FileInputStream fis = new FileInputStream("log_function.dat");
            TabulatedFunction readLogFunction = TabulatedFunctions.inputTabulatedFunction(fis);
            fis.close();
            System.out.println("Сравнение оригинального и прочитанного логарифма:");
            for (double x = 0.1; x <= 10; x += 1) {
                double original = logFunction.getFunctionValue(x);
                double read = readLogFunction.getFunctionValue(x);
                System.out.printf("x=%.1f: оригинал=%.3f, прочитано=%.3f%n", x, original, read);
            }
        } catch (IOException e) {
            System.out.println("Ошибка работы с файлом: " + e.getMessage());
        }
        // сереализация с Externalizable
        System.out.println("9. СЕРИАЛИЗАЦИЯ С EXTERNALIZABLE:");
        System.out.println("==================================");

        try {
            //создаем табулированный аналог логарифма по натуральному основанию, взятого от экспоненты
            TabulatedFunction expFunc = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);
            Function compFunc = Functions.composition(new Log(Math.E), expFunc);
            TabulatedFunction tabulatedComp = TabulatedFunctions.tabulate(compFunc, 0, 10, 11);

            //сериализуем объект в файл
            FileOutputStream fileOut = new FileOutputStream("composition_externalizable.dat");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(tabulatedComp);
            objOut.close();
            //десериализуем из файла
            FileInputStream fileIn = new FileInputStream("composition_externalizable.dat");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            TabulatedFunction readFunc = (TabulatedFunction) objIn.readObject();
            objIn.close();

            //сравниваем исходную и считанную функцию
            System.out.println("Сравнение исходной и десериализованной функции:");
            for (double x = 0; x <= 10; x += 1) {
                double original = tabulatedComp.getFunctionValue(x);
                double read = readFunc.getFunctionValue(x);
                System.out.printf("x=%.0f: исходная=%.3f, прочитанная=%.3f%n", x, original, read);
            }

            //изучаем содержимое файлов (анализ размера)
            File file = new File("composition_externalizable.dat");
            System.out.printf("Размер файла с Externalizable: %d байт%n", file.length());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при работе с сериализацией: " + e.getMessage());
        }
    }
}

