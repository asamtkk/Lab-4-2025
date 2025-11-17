package functions;

// исключение, выбрасываемое при попытке добавления или изменения точки функции несоответствующим образом
// используется когда операция с точкой нарушает целостность данных функции
public class InappropriateFunctionPointException extends Exception{

    // конструктор по умолчанию
    // создает исключение без сообщения об ошибке
    public InappropriateFunctionPointException(){
        super();
    }

    // конструктор с сообщением об ошибке
    // создает исключение с указанным сообщением, которое описывает причину ошибки
    public InappropriateFunctionPointException(String message){
        super(message);
    }
}
