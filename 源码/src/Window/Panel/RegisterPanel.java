package Window.Panel;
import javax.swing.*;
import Window.ImageLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Window.MainFrame;

public class RegisterPanel extends JPanel {
    private MainFrame fatherWindow;
    public RegisterPanel(MainFrame fatherWindow) {                                                                      //  注册界面
        this.setFatherWindow(fatherWindow);
        this.initPanel();
    }

    private void initPanel() {                                                                                          //  初始化
        this.setLayout(null);
        this.initControl();
    }

    //  待优化
    private void initControl() {                                                                                        //  初始化控件
        //  logo
        ImageLabel logoImage = new ImageLabel();
        logoImage.setImageUrl("image/registerBlack.png");
        logoImage.setBounds(320,200,50,50);
        this.add(logoImage);
        JLabel logoLabel = new JLabel("创建您的账号");
        logoLabel.setBounds(370,200,400,50);
        logoLabel.setFont(new Font("楷体", Font.BOLD, 40));
        this.add(logoLabel);

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

        //  确认密码
        JLabel passwordSureLabel = new JLabel("确认密码:");
        passwordSureLabel.setBounds(200,450,90,50);
        passwordSureLabel.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(passwordSureLabel);
        JPasswordField passwordSureText = new JPasswordField();
        passwordSureText.setBounds(300,450,400,50);
        this.add(passwordSureText);

        //  注册
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(300,520,200,50);
        registerButton.setFont(new Font("楷体", Font.BOLD, 18));
        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                saveInfo();
                fatherWindow.goToPanel("LoginPanel");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(registerButton);

        //  返回
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(500,520,200,50);
        returnButton.setFont(new Font("楷体", Font.BOLD, 18));
        returnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                fatherWindow.goToPanel("LoginPanel");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(returnButton);
    }

    public MainFrame getFatherWindow() {                                                                                //  获得父窗口
        return fatherWindow;
    }

    public void setFatherWindow(MainFrame fatherWindow) {                                                               //  设置父窗口
        this.fatherWindow = fatherWindow;
    }

    private void saveInfo() {                                                                                           //  保存账号密码

    }
}
