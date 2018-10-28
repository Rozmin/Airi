package subjectLibrary.question.math;

import javax.management.DynamicMBean;
import java.util.Random;

//  阶乘
public final class Factorial extends MathQuestion{
    public static final String questionType = "Factorial";                                                             //  题目类型
    private String question;                                                                                            //  题目内容
    private String answer;                                                                                              //  题目答案
    private boolean numberBoundary = true;                                                                              //  存在数值边界？
    private int numberMax = 20;                                                                                         //  题目数值最大值

    public Factorial() {                                                                                                //  默认构造
        this.initQuestion();
    }

    public Factorial(String question) {                                                                                 //  根据参数设置题目内容
        this.setQuestion(question);
    }

    public Factorial(String question, boolean numberBoundary) {                                                         //  根据题目内容和边界构造
        this.setNumberBoundary(numberBoundary);
        this.setQuestion(question);
    }

    public void initQuestion() {                                                                                        //  初始化题目
        Random random = new Random();
        long number;
        if (this.allowNumberBoundary()) {
            number = random.nextInt(numberMax) + 1;
        } else {
            number = random.nextInt() + 1;
        }
        String question = number + "!";
        this.setQuestion(question);
    }

    public boolean allowNumberBoundary() {                                                                              //  获得是否允许边界
        return numberBoundary;
    }

    public void setNumberBoundary(boolean numberBoundary) {                                                             //  设置是否允许边界
        this.numberBoundary = numberBoundary;
    }

    @Override
    public String getQuestion() {                                                                                       //  获取题目内容
        return this.question;
    }

    @Override
    public void setAnswer(String answer) {                                                                              //  设置答案
        if (answer == null || answer.equals("") ) {
            this.answer = this.calculate(this.getQuestion());
        } else {
            this.answer = this.calculate(this.getQuestion());
        }
    }

    @Override
    public String getAnswer() {                                                                                         //  获取答案
        return this.answer;
    }

    @Override
    public void setQuestion(String question) {                                                                          //  设置题目内容
        if (question == null || question.equals("") ) {
            this.initQuestion();
        } else{ //  此处未做容错
            this.question = question;
        }
        this.setAnswer(null);
    }

    @Override
    public String calculate(String question) {                                                                          //  计算题目答案
        if(question.charAt(question.length()-1) == '!') {
            String questionTemp = question.substring(0, question.length() - 1);                                         //  将question去掉尾部“！”

            int number = Integer.parseInt(questionTemp);
            if  (number <= 0) {
                throw new IllegalArgumentException("不能输入小于或等于0的数的阶乘");
            }

            long answer = 1;
            for (int i = 2; i <= number; i++) {
                answer *= i;
            }
            return answer+"";
        }
        return null;
    }

    @Override
    public void initBoundary(String type, int boundaryType, String number) {                                            //  初始化边界设定
        if (type == "QuestionNumber") {                                                                               //  如果绑定的为问题数值的边界
            if (boundaryType == MathQuestion.rightBoundaryType) {
                int max =  Integer.parseInt(number);
                if (max > 0 && max <= 15) {
                    this.numberMax = max;
                }
            }
        }
    }

    @Override
    public String getQuestionType() {                                                                                   //  获取题目类型
        return questionType;
    }
}
