package main;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by ecnu_kuangjun on 7/12/17.
 * 数据导入类的父类,导入每一张表的类都继承该类
 */
public class DataImport {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /**
     * 获取数据库连接
     * @param url:数据库url
     * @return :返回数据库连接
     */
    protected Connection getConnection(String url){
        try {
            this.connection = JDBCTools.getConnection(url);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * 获取PreparedStatement
     * @param sql SQL语句
     * @return PreparedStatement
     */
    protected PreparedStatement getPreparedStatement(String sql,Connection connection){
        try {
            this.preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return this.preparedStatement;
    }
    public String trimStr(String str,boolean type){
        if(str==null && type == true) {
            return "---";
        }
        if(str==null && type == false){
            return "0" ;
        }
        String cur = str.replaceAll("[\\s\\?]", "").replace("　", "");
        if("".equals(cur) && type == true){
            cur="---";
        }
        if("".equals(cur) && type == false){
            cur="0" ;
        }
	    return cur;
    }



}
