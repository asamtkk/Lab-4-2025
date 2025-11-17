package functions;

// исключение выхода за границы набора точек при обращении к ним по номеру
// используется когда пытаемся получить доступ к точке по несуществующему индексу
public class FunctionPointIndexOutOfBoundsException extends IndexOutOfBoundsException {

    // конструктор по умолчанию
    // создает исключение без сообщения об ошибке
    public FunctionPointIndexOutOfBoundsException(){
        super();
    }

    // конструктор с сообщением об ошибке
    // создает исключение с указанным сообщением, которое описывает причину ошибки
    public FunctionPointIndexOutOfBoundsException(String message){
        super(message);
    }
}
