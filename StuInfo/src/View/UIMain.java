package View;

/**
 * Created by user on 2016/12/19.
 */
import Dao.CourseDao;
import Dao.ScoreDao;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

//定义类时 实现监听接口
public class UIMain extends JFrame implements ActionListener{

    JButton b0,b1,b2,b3;

    Panel cardPanel=new Panel();

    Panel controlpaPanel=new Panel();

    String stuID = null;

    //登录组件
    JButton LoginButton = null;
    JTextField stuidText = null;
    JTextField passwordText = null;

    //Card 1
    JTextArea Card1textarea = null;
    JTextField Card1CourseIdText = null;
    JButton Card1CourseButton = null;

    //Card 2
    JTextArea Card2textArea = null;

    //Card 3
    JTextArea Card3textArea = null;
    JTextField Card3Cidtext = null;
    JTextField Card3CNametext = null;
    JButton Card3cidButton = null;
    JButton Card3cnameButton = null;

    //定义卡片布局对象
    CardLayout card=new CardLayout();

    //定义字符数组，为卡片命名

    String cardName[]={"0","1","2","3"};

    //定义构造函数
    public UIMain() {

        super("教务系统");
        setSize(680,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //设置cardPanel面板对象为卡片布局
        cardPanel.setLayout(card);

        //实例化按钮对象
        b0=new JButton("选课");
        b1=new JButton("查询选课");
        b2=new JButton("查询成绩");
        b3=new JButton("退出登录");

        LoginButton = new JButton("登录");

        //Card 1 组键
        Card1CourseButton = new JButton("确定选课");
        Card1CourseIdText = new JTextField();

        //Card2 组件
        Card2textArea = new JTextArea(10,1);

        //card3 组件
        Card3cidButton = new JButton("根据课程号查询成绩");
        Card3cnameButton = new JButton("根据课程名查询成绩");
        Card3textArea = new JTextArea(10,1);
        Card3Cidtext = new JTextField();
        Card3CNametext = new JTextField();

        //为按钮对象注册监听器
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        LoginButton.addActionListener(this);
        Card1CourseButton.addActionListener(this);
        Card3cidButton.addActionListener(this);
        Card3cnameButton.addActionListener(this);

        //card 1
        Card1textarea =  new JTextArea(10,1);
        Card1textarea.setWrapStyleWord(true);
        Card1textarea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(Card1textarea);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JLabel("以下为可选课程", JLabel.CENTER), BorderLayout.PAGE_START);
        jPanel1.add(jScrollPane, BorderLayout.CENTER);


        JPanel jpanel1In = new JPanel();
        jpanel1In.setLayout(new GridLayout(1, 3));
        jpanel1In.add(new JLabel("请输入课程代码", JLabel.CENTER), BorderLayout.BEFORE_LINE_BEGINS);
        jpanel1In.add(Card1CourseIdText);
        jpanel1In.add(Card1CourseButton, BorderLayout.AFTER_LINE_ENDS);

        jPanel1.add(jpanel1In, BorderLayout.PAGE_END);

        cardPanel.add(cardName[0], jPanel1);


        //card 2
        Card2textArea.setWrapStyleWord(true);
        Card2textArea.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(Card2textArea);
        cardPanel.add(cardName[1], jScrollPane2);

        //card 3
        Card3textArea.setWrapStyleWord(true);
        Card3textArea.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(Card3textArea);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(jScrollPane3, BorderLayout.PAGE_START);

        JPanel jPanel3In = new JPanel();
        jPanel3In.setLayout(new GridLayout(2, 3));

        jPanel3In.add(new JLabel("请输入课程号", JLabel.CENTER));
        jPanel3In.add(Card3Cidtext);
        jPanel3In.add(Card3cidButton);

        jPanel3In.add(new JLabel("请输入课程名称", JLabel.CENTER));
        jPanel3In.add(Card3CNametext);
        jPanel3In.add(Card3cnameButton);

        jPanel3.add(jPanel3In, BorderLayout.PAGE_END);

        cardPanel.add(cardName[2], jPanel3);

        //card 4
        JPanel jPanelLogIn = new JPanel();
        jPanelLogIn.setLayout(new BorderLayout());
        JLabel title = new JLabel("学生信息管理系统", JLabel.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 30));
        jPanelLogIn.add(title, BorderLayout.PAGE_START);

        stuidText = new JTextField();
        passwordText  = new JTextField();

        JPanel jpanelLogInIn = new JPanel();
        jpanelLogInIn.setLayout(new GridLayout(3, 3));
        jpanelLogInIn.add(new JLabel("学号", JLabel.RIGHT));
        jpanelLogInIn.add(stuidText);
        jpanelLogInIn.add(new JLabel(""));
        jpanelLogInIn.add(new JLabel("密码", JLabel.RIGHT));
        jpanelLogInIn.add(passwordText);
        jpanelLogInIn.add(new JLabel(""));
        jpanelLogInIn.add(new JLabel(""));
        jpanelLogInIn.add(LoginButton);
        jpanelLogInIn.add(new JLabel(""));

        jPanelLogIn.add(jpanelLogInIn, BorderLayout.PAGE_END);

        cardPanel.add(cardName[3], jPanelLogIn);


        controlpaPanel.add(b0);
        controlpaPanel.add(b1);
        controlpaPanel.add(b2);
        controlpaPanel.add(b3);


        //定义容器对象为当前窗体容器对象
        Container container=getContentPane();
        //将 cardPanel面板放置在窗口边界布局的中间，窗口默认为边界布局
        container.add(cardPanel,BorderLayout.NORTH);
        // 将controlpaPanel面板放置在窗口边界布局的南边，
        container.add(controlpaPanel,BorderLayout.SOUTH);

        //初始化数据
        setModel();

        //初始化组件
        init();

    }
    public void init() {
        card.show(cardPanel, "3");
        controlpaPanel.setVisible(false);
    }

    //实现按钮的监听触发时的处理

    public void actionPerformed(ActionEvent e){
        CourseDao courseDao = new CourseDao();
        ScoreDao scoreDao = new ScoreDao();
        //test

        //用户单击b0按钮时执行的语句
        if(e.getSource()==b0){
            //选课
            card.show(cardPanel,cardName[0]);
        }
        if(e.getSource()==b1){
            //查询选课
            card.show(cardPanel,cardName[1]);
            ArrayList<HashMap<String, Object>> courseList = courseDao.getAllSelectedCor(stuID);
            String courses = "";
            for (HashMap<String, Object> h : courseList) {
                courses += h.toString();
                courses += "\n";
            }
            Card2textArea.setText(courses);
        }
        if(e.getSource()==b2){
            //查询成绩
            card.show(cardPanel,cardName[2]);
        }
        if(e.getSource()==b3){
            //退出登录
            card.show(cardPanel,cardName[3]);
            controlpaPanel.setVisible(false);

        }
        if(e.getSource() == LoginButton) {
            //登录
            if (stuidText.getText().equals("111111") && passwordText.getText().equals("111")) {
                controlpaPanel.setVisible(true);
                card.show(cardPanel, cardName[0]);

                //设置当前登录者学号
                stuID = stuidText.getText();
            } else {
                JOptionPane.showMessageDialog(
                        new JPanel(),
                        "学号或者密码错误，请检查!",
                        "出错信息",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == Card1CourseButton) {
            String courseId = Card1CourseIdText.getText();
            System.out.println("ddsdds");
            if (!courseDao.isExist(courseId)) {
                JOptionPane.showMessageDialog(
                        new JPanel(),
                        "此课程不存在！",
                        "出错信息",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                courseDao.selectCourse(courseId, stuID);
                JOptionPane.showMessageDialog(
                        new JPanel(),
                        "选课成功！",
                        "成功",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == Card3cidButton) {
            //根据课程号查找成绩
            HashMap<String, Object> score = scoreDao.getScoreByCId(Card3Cidtext.getText(), stuID);
            if (score == null) {
                JOptionPane.showMessageDialog(
                        new JPanel(),
                        "没有该课程记录！",
                        "出错信息",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Card3textArea.setText(score.toString());

        }
        if (e.getSource() == Card3cnameButton) {
            //根据课程名称查找成绩
            HashMap<String, Object> score = scoreDao.getScoreByCName(Card3CNametext.getText(), stuID);
            if (score == null) {
                JOptionPane.showMessageDialog(
                        new JPanel(),
                        "没有该课程记录！",
                        "出错信息",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Card3textArea.setText(score.toString());
        }

    }


    private void setModel() {
        CourseDao courseDao = new CourseDao();
        ArrayList<HashMap<String, Object>> courseList = courseDao.getallcoures();
        String allCourses = "";
        for (HashMap<String, Object> t : courseList) {
            allCourses += t.toString();
            allCourses += "\n";
        }
        Card1textarea.setText(allCourses);
    }
    public static void main(String[] args) {
        UIMain kapian=new UIMain();
        kapian.setVisible(true);
    }

}
