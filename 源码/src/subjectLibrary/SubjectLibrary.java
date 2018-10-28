package subjectLibrary;

import subjectLibrary.question.Question;
import subjectLibrary.question.math.Factorial;
import subjectLibrary.question.math.FourAirthmetic;

import java.util.*;

//  题库类
public class SubjectLibrary {
    private String name = null;                                                                                         //  题库名称
    private List<Question> lib = new ArrayList<>();                                                                     //  题库
    private Set<String> questionType = new HashSet<String>();                                                                //  题目类型
    private boolean repetition = false;                                                                                 //  允许重复？
    private int questionNum = 5;                                                                                        //  题目数目

    public SubjectLibrary(int questionNum) {                                                                            //  根据题目数目构造
        this.setQuestionNum(questionNum);
    }

    public SubjectLibrary(String name, int questionNum) {                                                               //  根据题库名和数目构造
        this.setName(name);
        this.setQuestionNum(questionNum);
    }

    public SubjectLibrary() {                                                                                           //  无参默认构造

    }

    public void initLib() {                                                                                             //  初始化题库/生成题目
        Question question;
        lib.clear();
        for (int i = 0; i < getQuestionNum(); i++) {
            question = produceQuestion();
            if(!this.allowRepetition()) {
                if(isRepetitive(question)) {                                                                            //  题目是否重复
                    i--;
                    continue;
                }
            }
            lib.add(question);
        }
    }

    public String getName() {                                                                                           //  获取题库名称
        return name;
    }

    public void setName(String name) {                                                                                  //  设置题库名
        this.name = name;
    }

    public List<Question> getLib() {                                                                                    //  返回题库题目集
        return lib;
    }

    public boolean allowRepetition() {                                                                                  //  允许重复？
        return repetition;
    }

    public void setRepetition(boolean repetition) {                                                                     //  设置允许重复
        this.repetition = repetition;
    }

    public int getQuestionNum() {                                                                                       //  获得题目数目
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {                                                                       //  设置题目数目
        if (questionNum > 0) {
            this.questionNum = questionNum;
            return;
        }
        throw new IllegalArgumentException("题目数目不能小于0:" + questionNum);
    }

    public void registerQuestion(String Type) {                                                                         //  注册题目类型
        this.questionType.add(Type);
    }

    public Set<String> getQuestionType() {                                                                              //  获取题库现有题目类型
        return questionType;
    }

    private Question produceQuestion() {                                                                                //  生产题目
        Question question;
        int num = questionType.size();
        String[] type = new String[num];
        this.getQuestionType().toArray(type);

        Random random = new Random();
        int index = random.nextInt(num);

        switch (type[index]) {
            case "Factorial":
                question = new Factorial();
                break;
            case "FourAirthmetic":
                question = new FourAirthmetic();
                break;
            default:
                throw new IllegalArgumentException("暂未支持该类型题目:" + type[index]);
        }
        return question;
    }

    private boolean isRepetitive(Question question) {                                                                   //  判断题目是否与题库题重复
        boolean repeat = false;

        for (Question libQuestion : lib) {
            if (libQuestion.getQuestion().equals(question.getQuestion()) == true) {
                repeat = true;
                break;
            }
        }

        if (lib.isEmpty() || !repeat ) {
            return false;
        }
        return true;
    }
}
