import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 工厂模式 
 */
public class Factory {

    
}

/**
 * 所有按钮共同的接口
 */

interface Button{
    void render();
    void onClick();
}

/**
 * html按钮实现
 */
class HtmlButton implements Button{

    @Override
    public void render() {
        // TODO Auto-generated method stub
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        // TODO Auto-generated method stub
        System.out.println("Click! Button says - 'Hello World!'");
    }
    
}

/**
 * windows按钮实现
 */

class WindowsButton implements Button {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JButton button;

    public void render() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World!");
        label.setOpaque(true);
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 44));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(panel);
        panel.add(label);
        onClick();
        panel.add(button);

        frame.setSize(320, 200);
        frame.setVisible(true);
        onClick();
    }

    public void onClick() {
        button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
    }
}


/**
 * 基本工厂类. 注意"工厂"仅仅是类的一个角色.
 * 它应该有那些需要创建不同产品的的商业逻辑代码在里面
 * 
 */
abstract class Dialog {

    public void renderWindow() {
        // ... 其他代码 ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * 子类将会为了创建特殊的按钮而重写该方法对象
     */
    public abstract Button createButton();
}

/**
 * html diglog 将会生产 html 按钮.
 */

class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}

/**
 * Windows Dialog 将会生产 Windows 按钮
 */
class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}


/**
 * Demo类,所有东西将会组合在一起
 */
class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * 具体类通常选择依赖当前配置或者当前运行环境选项
     */
    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * 所有客户端代码应该随着工厂工作和通过抽象接口来生产,它并不关心工厂是怎么工作和生产返回的是什么类型 
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}