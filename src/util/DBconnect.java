package util;

import java.sql.*;
import java.util.List;

public class DBconnect
{
    static String url="jdbc:mysql://localhost:3306/2019summerVacation?useSSL=true";
    static String user="root";
    static String pw = "";
    static Connection conn=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
    static Statement st=null;


    public static void init() throws SQLException, ClassNotFoundException {//SQl程序初始化
        try{
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            conn= DriverManager.getConnection(url, user, pw);  //建立连接
        }catch (Exception e){
            System.out.println("SQL程序初始化失败");
            e.printStackTrace();
        }
    }

    public static void Update(Object ... args){//第一个参数用来传插入数据库的名称
        String sql;
        if (args[0].equals("artworks")){
            sql = "insert into artworks (imageFileName,title,description,yearOfWork,location,view,type) value (?,?,?,?,?,?,?)";
        }else if (args[0].equals("users")){
            sql = "insert into  users (name, email, password, type) VALUE (?,?,?,?)";
        }else {
            sql = "insert into favor (userID, artworkID, time) VALUE (?,?,?)";
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for (int i = 0;i < args.length; i++){
                preparedStatement.setObject(i+1,args[i+1]);
            }
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Delete(Object ... args){
        String sql;
        if (args[0].equals("artworks")){
            sql = "delete from artworks where artworkID = ?";
        }else if (args[0].equals("users")){
            sql = "delete from users where userID = ?";
        }else {
            sql = "delete from favor where favorID = ?";
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1,args[1]);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet Query(Object ... args){//第一个参数为数据库名字，第二个参数为选择的属性，第三个为其值
        try {
            String sql = "select * from "+args[0]+" where "+args[1]+" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1,args[2]);
            return preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static int addUpdateDelete(String sql){
        int i=0;
        try{
            ps=conn.prepareStatement(sql);
            boolean flag= ps.execute();
            if (!flag){//如果第一个结果是结果集对象，则返回true;如果第一个结果是更新计数或者没有结果，则返回false
                i++;
            }
        }catch(Exception e){
            System.out.println("数据库增删改异常 ");
            e.printStackTrace();
        }
        return i;
    }

    public static ResultSet selectSql(String sql){
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

        }catch(Exception e){
            System.out.println("数据库查询异常");
            e.printStackTrace();
        }
        return rs;
    }

    public static  void closeConn(){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("数据库关闭异常");
            e.printStackTrace();
        }
    }
}