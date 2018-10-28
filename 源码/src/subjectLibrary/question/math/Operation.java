package subjectLibrary.question.math;

//  运算符类
public class Operation {
    public static final int equalitySign = 0, plus = 1, subtract = 2, multiplication = 3, division = 4;                 //  =， +， - ， *， /

    public static  String operationToString(int operation) {
        switch (operation) {
            case plus:
                return "+";
            case subtract:
                return "-";
            case multiplication:
                return "*";
            case division:
                return "/";
            case equalitySign:
                return "=";
        }
        return null;
    }
}
