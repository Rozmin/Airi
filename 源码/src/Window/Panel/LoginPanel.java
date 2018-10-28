package Window.Panel;

import Window.MainFrame;
import Window.ImageLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPanel extends JPanel {
    private MainFrame fatherWindow;

    public LoginPanel(MainFrame mainFrame) {                                                                            //  参数为父级窗口的构造函数
        this.setFatherWindow(mainFrame);
        this.initPanel();
    }

    private void initPanel() {                                                                                          //  初始化登陆面板
        this.setLayout(null);                                                                                           //  设置面板布局为空
        this.setSize(fatherWindow.getSize());                                                                           //  设置面板大小
        this.initControl();                                                                                             //  初始化控件
    }

    public MainFrame getFatherWindow() {
        return fatherWindow;
    }

    public void setFatherWindow(MainFrame fatherWindow) {
        this.fatherWindow = fatherWindow;
    }

    //  待优化
    private void initControl() {                                                                                        //  初始化面板控件
        //  logo
        ImageLabel logoLabel = new ImageLabel();
        logoLabel.setImageUrl("image/logoBlack.png");
        logoLabel.setBounds(320,200,50,50);
        setFont(new Font("楷体", Font.BOLD, 18));
        this.add(logoLabel);

        JLabel TitleText = new JLabel("自动出题小程序");
        TitleText.setBounds(370,200,400,50);
        TitleText.setFont(new Font("楷体", Font.BOLD, 40));
        this.add(TitleText);


        //  账号
        JLabel accountLabel = new JLabel("账号:");
        accountLabel.setBounds(240,300,50,50);
        accountLabel.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(accountLabel);
        JTextField accountText = new JTextField();
        accountText.setBounds(300,300,400,50);
        this.add(accountText);

        //  密码
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(240,380,50,50);
        passwordLabel.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(300,380,400,50);
        this.add(passwordText);

        //  登陆
        JButton loginButton = new JButton("登陆");
        loginButton.setBounds(400,460,200,50);
        loginButton.setFont(new Font("楷体", Font.BOLD, 18));
        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (checkInfo()) {
                    fatherWindow.goToPanel("UIPanel");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(loginButton);

        //  注册
        JLabel registerLabel = new JLabel("没有账号?在此注册");
        registerLabel.setFont(new Font("楷体", Font.PLAIN, 15));
        registerLabel.setBounds(650,470,200,50);
        registerLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fatherWindow.goToPanel("RegisterPanel");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(registerLabel);
    }

    public boolean checkInfo() {                                                                                        //
        return true;
    }
}
