package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by ecnu_kuangjun on 7/12/17.
 */
public class LocationImport extends DataImport {
    Connection connection = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null;
    HashMap<String,Integer> cate = new HashMap<>();
    public void importLocation() {
        this.connection = super.getConnection("jdbcUrl");
        for (int i = 0; i <= 61; i++) {
            try {
                String sql = "SELECT 区县 county, 镇 town, 村 village FROM table" + i;
                this.preparedStatement = super.getPreparedStatement(sql,this.connection);
                this.resultSet = this.preparedStatement.executeQuery();
                while (this.resultSet.next()) {
                    String county = trimStr(this.resultSet.getString("county"),true);
                    String town = trimStr(this.resultSet.getString("town"),true);
                    String village = trimStr(this.resultSet.getString("village"),true);
                    cate.put(county + " " + town + " " + village, 1);
                }

            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        JDBCTools.releaseDB(this.resultSet,this.preparedStatement,null);


        String sql = "INSERT INTO location (county, town, village) VALUES(?,?,?)";
        Iterator<String> it = cate.keySet().iterator();
        String[] input = null ;

        while (it.hasNext()) {
            String location = it.next();
            input = location.split(" ");
            JDBCTools.update("jdbcUrl", sql, this.connection,input[0], input[1], input[2]);
        }
    }

 }
