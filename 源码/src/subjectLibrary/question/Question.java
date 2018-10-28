package subjectLibrary.question;

public interface Question {
    public abstract void setQuestion(String question);                                                                  //  创建新题

    public String getQuestion();                                                                                        //  获取题目内容

    public void setAnswer(String answer);                                                                               //  设置题目答案

    public String getAnswer();                                                                                          //  获取题目答案

    public abstract String getQuestionType();                                                                           //  获得题目类型
}
