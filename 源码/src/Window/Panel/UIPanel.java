package Window.Panel;

import Window.MainFrame;
import Window.ImageLabel;
import subjectLibrary.SubjectLibrary;
import subjectLibrary.question.Question;
import subjectLibrary.question.math.Factorial;
import subjectLibrary.question.math.FourAirthmetic;

import javax.security.auth.Subject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static java.lang.Thread.sleep;

public class UIPanel extends JPanel implements Runnable{
    private SubjectLibrary subjectLibrary;
    JButton startButton;
    private MainFrame fatherWindow;                                                                                     //  父窗口
    private UIPanel uiPanel = this;
    private JPanel commandPanel;                                                                                        //  控制栏
    private JPanel outPanel;                                                                                            //  输出栏
    private JPanel titlePanel;                                                                                          //  标题栏
    private JPanel menuPanel;                                                                                           //  菜单栏
    private JLabel timeLabel;                                                                                           //  计时标签
    private JLabel secondLabel;                                                                                         //  秒标签
    private JLabel scoreLabel;                                                                                          //  分数标签
    private boolean factorial = false;                                                                                  //  阶乘？
    private boolean fourAirthmetic = false;                                                                             //  四则运算
    private int num = 0;                                                                                                //  题目数目
    private int rightNum;
    private Thread timeThread;                                                                                          //  时间线程
    private JTextField[] answerText;

    public UIPanel(MainFrame mainFrame) {                                                                               //  无参数构造函数
        this.setFatherWindow(mainFrame);
        this.initUIPanel();                                                                                             //  设置UIPanel
    }

    public MainFrame getFatherWindow() {                                                                                //  获取父窗口
        return fatherWindow;
    }

    public void setFatherWindow(MainFrame fatherWindow) {                                                               //  设置父窗口
        this.fatherWindow = fatherWindow;
    }

    private void initUIPanel() {                                                                                        //  初始化当前面板
        this.setLayout(null);
        this.setBounds(0,0,1000,800);
        this.initCommandPanel();
        this.initOutPanel();
        this.initTitlePanel();
        this.initMenuPanel();
    }

    private void initMenuPanel() {                                                                                      //  初始化菜单栏
        menuPanel = new JPanel(null);
        menuPanel.setBounds(50,170,650,30);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.red));

        this.initMenuControl();
        this.add(menuPanel);
    }

    private void initMenuControl() {                                                                                    //  初始化菜单栏控件
        int stepX = 100;
        int starX = 0, starY = 0;
        int width = 80, height = 30;

        JLabel qustionNum = new JLabel("数目:"+ num);
        qustionNum.setBounds(starX+50, starY, 80, height);
        qustionNum.setFont(new Font("楷体", Font.BOLD, 18));
        menuPanel.add(qustionNum);

        ImageLabel reduceNumButton = new ImageLabel();
        reduceNumButton.setImageUrl("image/reduceBlack.png");
        reduceNumButton.setBounds(starX+10, starY+2, 26, 26);
//        reduceNumButton.setBorder(BorderFactory.createLineBorder(Color.red));
        reduceNumButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (num > 0) {
                    num--;
                    qustionNum.setText("数目:" + num);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        menuPanel.add(reduceNumButton);

        ImageLabel addButton = new ImageLabel();
        addButton.setImageUrl("image/addBlack.png");
        addButton.setBounds(starX+120, starY+2, 26, 26);
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (num < 5) {
                    num++;
                    qustionNum.setText("数目:" + num);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
//        addButton.setBorder(BorderFactory.createLineBorder(Color.red));
        menuPanel.add(addButton);

        JToggleButton factorialButton = new JToggleButton("阶乘");
        factorialButton.setBounds(starX+stepX*2, starY, width, height);
        factorialButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (factorial) {
                    factorial = false;
                } else {
                    factorial = true;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        menuPanel.add(factorialButton);

        JToggleButton fourAirthmeticButton = new JToggleButton("四则");
        fourAirthmeticButton.setBounds(starX+stepX*3, starY, width, height);
        fourAirthmeticButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (fourAirthmetic) {
                    fourAirthmetic = false;
                } else {
                    fourAirthmetic = true;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        menuPanel.add(fourAirthmeticButton);
    }

    private void initCommandPanel() {                                                                                   //  初始化控制台
        commandPanel = new JPanel(null);
        commandPanel.setBounds(750,50,180,650);
        commandPanel.setBorder(BorderFactory.createLineBorder(Color.red));

        this.initCommandControl();

        this.add(commandPanel);
    }

    private void initCommandControl() {                                                                                 //  初始化控件
        int step = 80;
        int startX = 30, startY = 40;
        int width = 120, height = 50;

        //  设置按钮
        JButton settingButton = new JButton("设置");
        settingButton.setBounds(startX, startY, width, height);
        settingButton.setFont(new Font("楷体", Font.BOLD, 18));
        commandPanel.add(settingButton);

        //  开始
        startButton = new JButton("开始");
        startButton.setBounds(startX, startY+step, width, height);
        startButton.setFont(new Font("楷体", Font.BOLD, 18));
        startButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (startButton.getText() == "开始") {
                    startButton.setText("提交");
                    initLib();
                    PrintQuestion();
                    timeThread = new Thread(uiPanel);
                    timeThread.start();
                } else {
                    startButton.setText("开始");
                    timeThread.stop();
                    submit();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        commandPanel.add(startButton);

       /* //  背景色
        JButton backgroundColor = new JButton("背景色");
        backgroundColor.setBounds(startX, startY+step*2, width, height);
        backgroundColor.setFont(new Font("楷体", Font.BOLD, 18));
        commandPanel.add(backgroundColor);*/
    }

    private void initOutPanel() {                                                                                       //  初始化输出面板
        outPanel = new JPanel(null);
        outPanel.setBounds(50,200,650,500);
        outPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.add(outPanel);
    }

    private void initTitlePanel() {                                                                                     //  初始化标题面板
        titlePanel = new JPanel(null);
        titlePanel.setBounds(50,50,650,100);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.initTitleControl();
        this.add(titlePanel);
    }

    private void initTitleControl() {                                                                                   //  标题控件
        timeLabel = new JLabel("00:00.");
        timeLabel.setBounds(480,25,140,50);
        timeLabel.setFont(new Font("楷体", Font.BOLD, 40));
        titlePanel.add(timeLabel);

        secondLabel = new JLabel("00");
        secondLabel.setBounds(600,49,30,20);
        secondLabel.setFont(new Font("楷体", Font.BOLD, 20));
        secondLabel.setForeground(Color.red);
        titlePanel.add(secondLabel);

        scoreLabel = new JLabel("分数:");
        scoreLabel.setBounds(20,0,100,100);
        scoreLabel.setFont(new Font("楷体", Font.BOLD, 20));
        scoreLabel.setForeground(Color.red);
        titlePanel.add(scoreLabel);
    }

    @Override
    public void run() {
        int second = 0;
        int minute = 0;
        int hour = 0;

        while(true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            second++;
            if (second == 60) {
                second = 0;
                minute++;
            }
            if (minute == 60) {
                minute = 0;
                hour++;
            }
            if (minute == 2) {
                JOptionPane.showMessageDialog(fatherWindow, "答题时间结束");
                startButton.setText("开始");
                submit();
                timeThread.stop();
            }
            secondLabel.setText(String.format("%02d", second));
            timeLabel.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute)+".");
        }
    }

    private void initLib() {
        subjectLibrary = new SubjectLibrary(this.num);

        if (factorial) {
            subjectLibrary.registerQuestion(Factorial.questionType);
        }
        if (fourAirthmetic) {
            subjectLibrary.registerQuestion(FourAirthmetic.questionType);
        }

        subjectLibrary.initLib();
    }

    private void PrintQuestion() {
        outPanel.removeAll();
        JLabel[] question = new JLabel[this.num];
        List<Question> libQuestion = subjectLibrary.getLib();
        answerText = new JTextField[this.num];

        int i = 0;
        int stepY = 50;
        for (Question quest : libQuestion) {
            question[i] = new JLabel("第" + (i+1) + "题:" + quest.getQuestion());
            question[i].setBounds(10,10+stepY*i,300,30);
            question[i].setFont(new Font("楷体", Font.BOLD, 16));
            outPanel.add(question[i]);

            answerText[i] = new JTextField();
            answerText[i].setBounds(320,10+stepY*i,150,30);
            outPanel.add(answerText[i]);
            i++;
        }
        outPanel.updateUI();
    }

    private void submit() {                                                                                      //
        List<Question> libQuestion = subjectLibrary.getLib();

        int i = 0;
        for (Question quest : libQuestion) {
            if (quest.getAnswer().equals(answerText[i].getText()) ) {
                rightNum++;
            } else {
                answerText[i].setText(answerText[i].getText() + "        正确答案:" + quest.getAnswer());
            }
            i++;
        }
        scoreLabel.setText("得分:" + rightNum*10);
        titlePanel.updateUI();
    }
}
