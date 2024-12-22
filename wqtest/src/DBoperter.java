import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBoperter {
    public ArrayList<Contact> getAll(){
        //存放遍历的结果
        ArrayList<Contact> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList<Contact> list1 =new LinkedList<>();
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建查询的sql语句
            String sql = "select * from t_area order by  area desc ";
            ps = conn.prepareStatement(sql);
            //3 执行查询，返回结果
            rs = ps.executeQuery();
            //4 遍历集合
            while (rs.next()) {
                //根据查询结果创建对象
                Contact contact = new Contact(rs.getInt("id"),rs.getString("class"),rs.getString("name"),rs.getString("number"),rs.getDouble("area"),rs.getString("now"));
                //将对象添加到list中
                list.add(contact);
                list1.add(contact);
                System.out.println("id: "+rs.getInt("id")+"\t"+"class: "+rs.getString("class")+"\t"+"name: "+rs.getString("name")+"\t"+"number: "+rs.getString("number")+"\t"+"area: "+rs.getString("area")+"\t"+"now: "+rs.getString("now"));
            }
            System.out.println("一共有"+list1.size()+"条记录！");
            showFrame(list1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return list;
    }
    private static void showFrame(List<Contact> list){

        //1，设定窗口
        JFrame frame =new JFrame("从mysql中获取数据并展示~");
        frame.setLocation(700,400);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        //2,添加table
        JTable table =null;
        String [] index={"id","class","name","number","area","now"};
        Object [][] data=new Object[list.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < list.size(); i++) {
            Contact contact =list.get(i);
            data[i][0]=contact.getId();
            data[i][1]=contact.getClass1();
            data[i][2]=contact.getName();
            data[i][3]=contact.getNumber();
            data[i][4]=contact.getArea();
            data[i][5]=contact.getNow();

        }
        //4,创建一个默认的表格模型
        DefaultTableModel defaultModel = new DefaultTableModel(data, index);
        table=new JTable(defaultModel);
        table.setBackground(Color.cyan);
        table.setPreferredScrollableViewportSize(new Dimension(100, 80));//JTable的高度和宽度按照设定
        table.setFillsViewportHeight(true);

        //5，给表格设置滚动条
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);

        Font font = new Font("宋体", Font.BOLD, 13);

        //添加button
        JButton button =new JButton("查询");
        button.setBounds(50,10,50,30);

        //添加label
        JLabel label =new JLabel("点击按钮，查询MySQL数据库中的数据：");
        label.setFont(font);
        label.setBounds(1,10,240,30);

        //通过panel组合button，label
        JPanel panel =new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setSize(200,100);
        panel.add(label);
        panel.add(button);

        //6，添加表格、滚动条到容器中
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.add(jScrollPane,BorderLayout.CENTER);
                frame.setVisible(true);

            }
        });

    }
    public int save(Contact contact){
        Connection conn = null;
        PreparedStatement ps = null;
        int row;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建添加的sql语句
            String sql = "insert into t_area(class,name,number,area,now) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //3 设置问号的值
            ps.setString(1,contact.getClass1());
            ps.setString(2,contact.getName());
            ps.setString(3,contact.getNumber());
            ps.setDouble(4,contact.getArea());
            ps.setString(5,contact.getNow());
            //4 执行添加，返回结果
//            System.out.println(ps.executeUpdate());
            row = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return row;
    }
}
