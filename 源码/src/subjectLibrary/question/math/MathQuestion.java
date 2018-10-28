package subjectLibrary.question.math;

import subjectLibrary.question.Question;

public abstract class MathQuestion implements Question {
    //  boundaryType可用值
    public static final int noBoundaryType     = 0;                                                                     //  无边界限制
    public static final int leftBoundaryType   = 1;                                                                     //  左边界限制
    public static final int rightBoundaryType  = 2;                                                                     //  右边界限制

    public abstract String calculate(String question);                                                                  //  计算函数
    public abstract void initBoundary(String type, int boundaryType, String number);                                    //  绑定边界函数
}
