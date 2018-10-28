package Window;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public MainFrame() {                                                                                                //  无参数构造函数
        initFrame();
    }

    public void initFrame() {                                                                                           //  初始化窗口
        this.setTitle("自动出题小程序");
        this.setLogo();
        this.initWindowSize();
        mainPanel.setLayout(cardLayout);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE );
        this.setVisible(true);
    }

    private void setLogo() {                                                                                            //  设置图标
        ImageIcon logo = new ImageIcon("image/logoBlack.png");
        this.setIconImage(logo.getImage());
    }

    public void addPanel(JPanel newPanel, String name) {                                                                //  添加指定面板到容器
        mainPanel.add(newPanel);
        cardLayout.addLayoutComponent(newPanel, name);
    }

    public boolean goToPanel(String name) {                                                                             //  跳转到指定面板
        cardLayout.show(mainPanel, name);
        return true;
    }

    public void initWindowSize() {                                                                                      //  设置初始窗口大小
        //  测试窗口大小
        this.setBounds(100,100,1000,800);
    }
}
