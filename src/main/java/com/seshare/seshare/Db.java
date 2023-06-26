package com.seshare.seshare;
import  java.sql.*;
import   java.sql.Connection;
import org.jgroups.protocols.STOMP;

public class Db {
    public static Connection connection(){
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost/se_share","root","");
            System.out.println("success");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
