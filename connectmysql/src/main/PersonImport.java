package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;


public class  PersonImport extends DataImport {
    Connection connection = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null;
    HashMap<String,Integer> cate = new HashMap<>();
    public void importPerson() {
        this.connection = super.getConnection("jdbcUrl");
        for (int i = 0; i <= 61; i++) {
            try {
                // 连接lwdata数据库
                String sql = "SELECT 姓名 name, 身份证号码 ID_number FROM table" + i;
                this.preparedStatement = this.connection.prepareStatement(sql);
                // preparedStatement.setString(1, 'table1');
                this.resultSet = this.preparedStatement.executeQuery();
                while (this.resultSet.next()) {
                    String name = trimStr(this.resultSet.getString("name"));
                    String ID_number = trimStr(this.resultSet.getString("ID_number"));
                    cate.put(name + " " + ID_number, 1);
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
            // finally {
            //
            // }
        }

        String sql = "INSERT INTO person(name, ID_number) VALUES(?,?)";
        Iterator<String> it = cate.keySet().iterator();
        String[] input = null ;
        while (it.hasNext()) {
            String person = it.next();
            input = person.split(" ");
            JDBCTools.update("jdbcUrl", sql, this.connection,input[0],input[1]);
        }
        JDBCTools.releaseDB(this.resultSet,this.preparedStatement,null);
    }

}

