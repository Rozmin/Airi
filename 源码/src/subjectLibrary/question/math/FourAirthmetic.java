package subjectLibrary.question.math;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

//  待完成
public final class FourAirthmetic extends MathQuestion{
    public static final String questionType = "FourAirthmetic";                                                       //  题目类型
    private String question;                                                                                            //  题目内容
    private String answer;                                                                                              //  题目答案
    private int numberBoundary;                                                                                         //  题目数值边界?
    private int answerBoundary = noBoundaryType;                                                                        //  答案数值边界
    private int numberNum = 4;                                                                                          //  运算数字个数
    private double answerMax, answerMin = 0;                                                                            //  答案边界
    private int numberMax = 100, numberMin = -100;                                                                      //  题目数值限制边界
    public FourAirthmetic() {
        initQuestion();
    }
    @Override public String getQuestion() {                                                                                       //  获取题目内容
        return this.question;
    }
    @Override public void setAnswer(String answer) {                                                                              //  设置题目答案
        if (answer == null || answer.equals("") ) {
            this.answer = this.calculate(this.getQuestion());
        } else {
            this.answer = this.calculate(this.getQuestion());
        }
    }
    @Override public String getAnswer() {                                                                                         //  获取题目答案
        return this.answer;
    }
    @Override public void setQuestion(String question) {                                                                          //  设置题目内容
        if (question == null || question.equals("") ) {
            this.initQuestion();
        } else{ //  此处未做容错
            this.question = question;
        }
        this.setAnswer(null);
    }
    @Override public String calculate(String question) {                                                                          //  计算函数
        Object answer = null;
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            answer = scriptEngine.eval(question);
        }
        catch (ScriptException e) {
            e.printStackTrace();
        }

        String answerS;                                                                                                 //  对结果的处理
        double answerD = Double.parseDouble(answer+"");
        if ((int)answerD != answerD) {
            answerS = String.format("%.2f", answerD);
        } else {
            answerS = (int)answerD+"";
        }
        return answerS;
    }
    @Override public void initBoundary(String type, int boundaryType, String number) {                                            //  初始化边界
        switch (type) {
            case "numberSize":                                                                                          //  限制题目数值边界
                this.setNumberSize(boundaryType, number);
                break;
            case "answerSize":                                                                                          //  限制答案边界
                this.setAnswerSize(boundaryType, number);
                break;
            case "numberSize answerSize":
                this.setNumberSize(boundaryType, number);
                this.setAnswerSize(boundaryType, number);
                break;
        }
    }
    @Override public String getQuestionType() {                                                                                   //  获取题目类型
        return questionType;
    }
    public int allowNumberBoundary() {
        return numberBoundary;
    }
    public int allowAnswerBoundary() {                                                                                  //  允许答案边界
        return answerBoundary;
    }
    public void setNumberBoundary(int numberBoundary) {                                                                 //  设置题目是否绑定边界
        this.numberBoundary = numberBoundary;
    }
    public void setAnswerBoundary(int answerBoundary) {                                                                 //  设置答案边界
        this.answerBoundary = answerBoundary;
    }
    public void setNumberSize(int boundaryType,String numberSize) {                                                     //  设置题目边界
        String[] numberSizeSlip = numberSize.split(" ");
        switch (boundaryType) {
            case leftBoundaryType:                                                                                      //  左边界
                this.numberMin = Integer.parseInt(numberSizeSlip[0]);
                break;
            case rightBoundaryType:                                                                                     //  右边界
                this.numberMax = Integer.parseInt(numberSizeSlip[0]);
                break;
            case leftBoundaryType | rightBoundaryType:                                                                  //  左右边界
                if (Double.parseDouble(numberSizeSlip[0]) > Double.parseDouble(numberSizeSlip[1])) {
                    throw new IllegalArgumentException("左边界不能大于右边界");
                }
                this.numberMin = Integer.parseInt(numberSizeSlip[0]);
                this.numberMax = Integer.parseInt(numberSizeSlip[1]);
                break;
            default:
                this.setNumberBoundary(boundaryType);                                                                   //  无边界
        }
        this.setNumberBoundary(boundaryType);                                                                           //  设置数值有绑定
    }
    public double getNumberMax() {                                                                                      //  获取题目限制最大值
        return numberMax;
    }
    public double getNumberMin() {                                                                                      //  获取题目限制最小值
        return numberMin;
    }
    public double getAnswerMax() {                                                                                      //  获取答案限制最大值
        return answerMax;
    }
    public double getAnswerMin() {                                                                                      //  获取答案限制最小值
        return answerMin;
    }
    public void setAnswerSize(int boundaryType,String numberSize) {                                                     //  获得答案最大值
        String[] numberSizeSlip = numberSize.split(" ");
        switch (boundaryType) {
            case leftBoundaryType:                                                                                      //  左边界
                this.answerMin = Double.parseDouble(numberSizeSlip[0]);
                break;
            case rightBoundaryType:                                                                                     //  右边界
                this.answerMax = Double.parseDouble(numberSizeSlip[0]);
                break;
            case leftBoundaryType | rightBoundaryType:                                                                  //  左右边界
                if (Double.parseDouble(numberSizeSlip[0]) > Double.parseDouble(numberSizeSlip[1])) {
                    throw new IllegalArgumentException("左边界不能大于右边界");
                }
                this.answerMin = Double.parseDouble(numberSizeSlip[0]);
                this.answerMax = Double.parseDouble(numberSizeSlip[1]);
                break;
            default:
                this.setAnswerBoundary(boundaryType);                                                                   //  无边界
        }
        this.setAnswerBoundary(boundaryType);                                                                           //  设置数值有绑定
    }
    public int getNumberNum() {
        return numberNum;
    }
    public void setNumberNum(int numberNum) {
        this.numberNum = numberNum;
    }
    //  待优化
    private void initQuestion() {                                                                                       //  出题
        Random random = new Random();
        int[] number = new int[getNumberNum()];
        int[] operation = new int[getNumberNum()-1];
        String equation;
        boolean isOK;
        double answer;

        do {
            isOK = true;
            equation = "";
            number = random.ints((long) numberNum, numberMin, numberMax).toArray();
            operation = random.ints((long) numberNum - 1, 1,
                5).toArray();

            int i;
            int leftBracket = 0;                                                                                        //  左括号
            for (i = 0; i < getNumberNum() - 1; i++) {
                boolean nowLeftBraccket = false;                                                                        //  当前添加左括号？
                if (leftBracket == 0) {
                    if (random.nextInt(2) == 1 ) {
                        equation += "(";
                        leftBracket = 1;
                        nowLeftBraccket = true;
                    }
                }

                if (number[i] >= 0) {                                                                                   //  数值正负号加括号处理
                    equation += number[i] + "";
                }
                else {
                    equation += "(" + number[i] + ")";
                }

                if (leftBracket==1 && !nowLeftBraccket) {                                                                  //  有左括号且不在当前数值
                    if (random.nextInt(2) == 1 ) {
                        equation += ")";
                        leftBracket = 2;
                    }
                }

                if (Operation.operationToString(operation[i]) == "/") {
                    if (number[i + 1] == 0) {
                        number[i + 1] = 1;
                    }
                }
                equation += Operation.operationToString(operation[i]);
            }
            if (number[i] >= 0) {
                equation += number[i] + "";
            }
            else {
                equation += "(" + number[i] + ")";
            }
            if (leftBracket == 1) {
                equation += ")";
            }
            answer = Double.parseDouble(this.calculate(equation));

            switch (allowAnswerBoundary()) {
                case leftBoundaryType:
                    if (answer < answerMin) {
                        isOK = false;
                    }
                    break;
                case rightBoundaryType:
                    if (answer > answerMax) {
                        isOK = false;
                    }
                    break;
                case leftBoundaryType | rightBoundaryType:
                    if (answer < answerMin || answer > answerMax) {
                        isOK = false;
                    }
                    break;
                default:
                    break;
            }
        } while(!isOK);

        this.question = equation;
        this.setAnswer(answer+"");
    }
}
