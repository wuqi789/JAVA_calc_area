import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Myframe {
    private JFrame frame=new JFrame("梯形面积计算器");
    private JTextArea jta= new  JTextArea(30,30);
    private JLabel up=new JLabel("上底:");
    private JTextField upfield= new JTextField(30);
    private JLabel down=new JLabel("下底:");
    private JTextField downfield= new JTextField(30);
    private JLabel hight=new JLabel("高:");
    private JTextField hightfield=new JTextField(30);
    private JLabel calc=new JLabel("面积:");
    private JTextField calcfield=new JTextField(30);
    private JButton calcbutton=new JButton("计算");
    private JButton printbutton=new JButton("打印");
    private JButton savebutton=new JButton("保存");
    private DBoperter db = new DBoperter();
    public Myframe() {
        //设置计算按钮监听事件
        calcbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (upfield.getText().isEmpty()|| downfield.getText().isEmpty()||hightfield.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"请填写数据！","错误",JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                else if(!check(upfield.getText())||!check(downfield.getText())||!check(hightfield.getText())){
                    JOptionPane.showMessageDialog(frame,"输入格式不正确！","warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                double up=Double.parseDouble(upfield.getText());
                double bottom=Double.parseDouble(downfield.getText());
                double height=Double.parseDouble(hightfield.getText());
//                计算面积
                double ans=((up+bottom)*height)/2;
                System.out.println(ans);
                String ans1=String.valueOf(ans);//转String
                calcfield.setText(ans1);
            }
        });
//        打印按钮
        printbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                db.getAll();
            }
        });
//        保存按钮
        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upfield.getText().isEmpty()|| downfield.getText().isEmpty()||hightfield.getText().isEmpty()||calcfield.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"请填写数据！","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double up=Double.parseDouble(upfield.getText());
                double bottom=Double.parseDouble(downfield.getText());
                double height=Double.parseDouble(hightfield.getText());
                String name="吴棋";
                String class1="22计算机6班";
                String number="202205050616";
//                获取时间为time
                String time=gettime();
                double str=((up+bottom)*height)/2;
//                使用String.format保留2位小数
                String ans1 = String.format("%.2f", str);
//                String转double
                double ans = Double.parseDouble(ans1);
//                calcfield.setText(ans1);
                Contact contact=new Contact(class1,name,number,ans,time);
                int row = db.save(contact);
                if (row >0){
                    JOptionPane.showMessageDialog(frame,"添加成功");
                }
            }
        });
        Box box=Box.createHorizontalBox();
        box.add(up);
        box.add(Box.createHorizontalStrut(10));
//        upfield.setColumns(20);
        box.add(upfield);
//        box.add(Box.createHorizontalStrut(30));
        Box box1=Box.createHorizontalBox();
        box1.add(down);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(downfield);

        Box box2=Box.createHorizontalBox();
        box2.add(hight);
        box2.add(Box.createHorizontalStrut(23));
        box2.add(hightfield);
//        box.add(Box.createHorizontalStrut(30));

        Box box3=Box.createHorizontalBox();
        box3.add(calc);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(calcfield);

        Box all=Box.createVerticalBox();
        all.add(box);
        all.add(box1);
        all.add(box2);
        all.add(box3);


        Box box4=Box.createHorizontalBox();
        box4.add(calcbutton);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(printbutton);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(savebutton);
        box4.add(Box.createHorizontalStrut(10));
//        all.add(calc);
        all.add(box4);




        JPanel panel=new JPanel();
//        panel.setLayout(new GridLayout(5,2));
        panel.add(all);
//        frame.add(panel);
        frame.add(panel);
        frame.setSize(500,200);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private String gettime(){
        Date date = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("YYYY-MM-DD");
        SimpleDateFormat s2 = new SimpleDateFormat("hh:mm:ss");
        String time=s1.format(date);
        String time2=s2.format(date);
        String time4=time+" "+time2;
        String t= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE).format(new java.util.Date());
//        System.out.println(t);
        return t;
    }
    private boolean check(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                if(str.charAt(i)=='.'){
                    continue;
                }
                return false;
            }
        }
        return true;

    }
}
