import Window.MainFrame;
import Window.Panel.LoginPanel;
import Window.Panel.RegisterPanel;
import Window.Panel.UIPanel;
import subjectLibrary.SubjectLibrary;
import subjectLibrary.question.Question;
import subjectLibrary.question.math.Factorial;
import subjectLibrary.question.math.FourAirthmetic;
import subjectLibrary.question.math.MathQuestion;

import java.util.Scanner;

public class Main_Window {
    public static void main(String[] args) {
        SubjectLibrary test = new SubjectLibrary(5);               //  构造题库
        test.registerQuestion(FourAirthmetic.questionType);                     //  注册题目类型
        test.registerQuestion(Factorial.questionType);
        test.initLib();                                                         //  生成题目

        for (Question question : test.getLib()) {                               //  获取题目
            System.out.println("题目类型:"+question.getQuestionType());
            System.out.println("题目内容:"+question.getQuestion());
            System.out.println("题目答案:"+question.getAnswer());
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.addPanel(new LoginPanel(mainFrame),"LoginPanel");
        mainFrame.addPanel(new UIPanel(mainFrame),"UIPanel");
        mainFrame.addPanel(new RegisterPanel(mainFrame), "RegisterPanel");
    }
}
