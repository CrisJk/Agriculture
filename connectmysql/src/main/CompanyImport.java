package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class CompanyImport extends DataImport {
    Connection connection = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null;
    HashMap<String,Integer> cate = new HashMap<>();
    public void importCompany() {
        this.connection = super.getConnection("jdbcUrl");
        for (int i = 0; i <= 61; i++) {
            try {
                String sql = "SELECT `合作社/企业名称` name FROM table" + i;
				this.preparedStatement = super.getPreparedStatement(sql,this.connection);
				this.resultSet = this.preparedStatement.executeQuery();
				while(this.resultSet.next()) {
					String name = trimStr(this.resultSet.getString("name"),true);
					cate.put(name+" "+"#", 1);
				}

            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }

        String sql = "INSERT INTO company(name) VALUES(?)";
        Iterator<String> it = cate.keySet().iterator();
        String[] input = null ;
        while (it.hasNext()) {
            String company = it.next();
            input = company.split(" ");
            JDBCTools.update("jdbcUrl", sql,this.connection,input[0]);
        }
        JDBCTools.releaseDB(this.resultSet,this.preparedStatement,this.connection);
    }

}
