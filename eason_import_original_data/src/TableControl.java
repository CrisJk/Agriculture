import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class TableControl {

    //字符串修剪  去除所有空白符号 ， 问号 ， 中文空格
	//长度为0或null，若为字符返回 '---'  否则返回 0
	static private String Trim_str(String str , boolean type){
        if(str==null){
        	if(type) return "'---'";
        	else return "0";
        }
        String cur = str.replaceAll("[\\s\\?]", "").replace("　", "");
        if("".equals(cur)){
        	if(type) return "'---'";
        	else return "0";
        }
        if(type){
        	cur="'"+cur+"'";
        }
        return cur;
    }
	
    
//    //如果是作为字符串，非空加''  否则不加
//    //如果是空，返回null字符串
//    //type=true代表字符串  否则不是
//    static private String str_for_sql(String str,boolean type){
//    	if(str != null)
//    		return str;
//    	if(type==true)
//    		return "---";
//    	else
//    		return "0";
//    }

    // 导入location表数据
    public void Import_location() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        HashMap<String, Integer> cate = new HashMap<>();
        for (int i = 0; i <= 61; i++) {
            try {
                // 连接lwdata数据库
                String sql = "SELECT 区县 country, 镇 town, 村 village FROM table" + i;
                connection = JDBCTools.getConnection("jdbcUrl");
                preparedStatement = connection.prepareStatement(sql);
                // preparedStatement.setString(1, 'table1');
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String country = Trim_str(resultSet.getString("country"),true);
                    String town = Trim_str(resultSet.getString("town"),true);
                    String village = Trim_str(resultSet.getString("village"),true);
                    cate.put(country + " " + town + " " + village, 1);
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
            // finally {
            //
            // }
            // System.out.println("table"+i);
        }
        System.out.println(cate.size());
        // System.out.println(cate);
        JDBCTools.releaseDB(resultSet, preparedStatement);

        String sql = "INSERT INTO location (county, town, village) VALUES(?,?,?)";
        // Iterator<Double> it = decision.descendingKeySet().iterator();
        String[] input = null;
        Iterator<String> it = cate.keySet().iterator();
        while (it.hasNext()) {
            String location = it.next();
            input = location.split(" ");
            JDBCTools.update(connection,  sql, input[0], input[1], input[2]);
        }
        JDBCTools.releaseCon(connection);
        System.out.println("over!");
    }

    // 导入person表数据
    public void Import_person() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HashMap<String, Integer> cate = new HashMap<>();
        for (int i = 0; i <= 61; i++) {
            try {
                // 连接lwdata数据库
                String sql = "SELECT 姓名 name, 身份证号码 ID_number FROM table" + i;
                connection = JDBCTools.getConnection("jdbcUrl");
                preparedStatement = connection.prepareStatement(sql);
                // preparedStatement.setString(1, 'table1');
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = Trim_str(resultSet.getString("name"),true);
                    String ID_number = Trim_str(resultSet.getString("ID_number"),true);
                    cate.put(name + " " + ID_number, 1);
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
            // finally {
            //
            // }
            // System.out.println("table"+i);
        }
        System.out.println(cate.size());
        // System.out.println(cate);
        JDBCTools.releaseDB(resultSet, preparedStatement);

        String sql = "INSERT INTO person(name, ID_number) VALUES(?,?)";
        // Iterator<Double> it = decision.descendingKeySet().iterator();
        String[] input = null;
        Iterator<String> it = cate.keySet().iterator();
        while (it.hasNext()) {
            String location = it.next();
            input = location.split(" ");
            JDBCTools.update(connection, sql, input[0], input[1]);
        }
        JDBCTools.releaseCon(connection);
        System.out.println("over!");
    }

    // 导入company表数据
    public void Import_company() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HashMap<String, Integer> cate = new HashMap<>();
        for (int i = 0; i <= 61; i++) {
            try {
                // 连接lwdata数据库
                String sql = "SELECT `合作社/企业名称` name FROM table" + i;
                connection = JDBCTools.getConnection("jdbcUrl");
                preparedStatement = connection.prepareStatement(sql);
                // preparedStatement.setString(1, 'table1');
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = Trim_str(resultSet.getString("name"),true);
                    cate.put(name + " " + "#", 1);
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
            // finally {
            //
            // }
            // System.out.println("table"+i);
        }
        System.out.println(cate.size());
        // System.out.println(cate);
        JDBCTools.releaseDB(resultSet, preparedStatement);

        String sql = "INSERT INTO company(name) VALUES(?)";
        // Iterator<Double> it = decision.descendingKeySet().iterator();
        String[] input = null;
        Iterator<String> it = cate.keySet().iterator();
        while (it.hasNext()) {
            String location = it.next();
            input = location.split(" ");
            JDBCTools.update(connection, sql, input[0]);
        }
        JDBCTools.releaseCon(connection);
        System.out.println("over!");
    }

    // 导入planting_area表数据----------------------------------------------------------------------------
    public void Import_planting_area() {
        Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement preparedStatement = null, preparedStatement2 = null,pst_for_update = null;
        ResultSet resultSet = null, resultSet2 = null;
        // 涉及的表有： 1，3，13，15

        // 每张表对应一个sql语句
        //**************************************************************************************************
        ArrayList<String> sqlList = new ArrayList<String>();
//        sqlList.add("SELECT 姓名 person_name,身份证号码 ID_number,`合作社/企业名称` company_name,区县 county,镇 town,村 village,年度 year,种植品种 crop,`种植面积(亩)` area from table1");
//        sqlList.add("SELECT 姓名 person_name,身份证号码 ID_number,`合作社/企业名称` company_name,区县 county,镇 town,村 village,年度 year,补贴品种 crop,`种植面积(亩)` area  from table3");
//        sqlList.add("SELECT 姓名 person_name,身份证号码 ID_number,`合作社/企业名称` company_name,区县 county,镇 town,村 village,年度 year,'绿肥' crop,`种植面积(亩)` area  from table13");
//        sqlList.add("SELECT 姓名 person_name,身份证号码 ID_number,`合作社/企业名称` company_name,区县 county,镇 town,村 village,年度 year,'夏淡绿叶菜' crop,`市级补贴面积（亩）` area  from table15");
        //**************************************************************************************************
        for (int i = 0; i < sqlList.size(); i++) {
            int query_times = 0;
            try {
                // 连接lwdata数据库
                String sql = sqlList.get(i);
                connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
                conn_for_update = JDBCTools.getConnection("jdbcUrl");  //conn_for_update负责批量插入
                conn_for_update.setAutoCommit(false); //改为非自动提交

                //创建批量插入任务
                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,crop,area) VALUES(?,?,?,?,?,?)";
                //**************************************************************************************************
                String pre_sql= "INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,crop,area) VALUES";
                //**************************************************************************************************
                StringBuffer suffix = new StringBuffer();

                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                pst_for_update = conn_for_update.prepareStatement("");

                while (resultSet.next()) {
                    //**************************************************************************************************
                    String person_name = Trim_str(resultSet.getString("person_name"),true);
                    String ID_number = Trim_str(resultSet.getString("ID_number"),true);
                    String company_name = Trim_str(resultSet.getString("company_name"),true);
                    String county = Trim_str(resultSet.getString("county"),true);
                    String town = Trim_str(resultSet.getString("town"),true);
                    String village = Trim_str(resultSet.getString("village"),true);
                    String year = Trim_str(resultSet.getString("year"),false);
                    String crop = Trim_str(resultSet.getString("crop"),true);
                    String area = Trim_str(resultSet.getString("area"),false);
                    //**************************************************************************************************
                    // 判断该记录属于个人还是企业
                    String receiver_type = "个人";
                    if (company_name != null && !company_name.equals("")) {
                        receiver_type = "企业";
                    }
                    String receiver_id = "";
                    String location_id = "";
                    if (receiver_type.equals("个人")) { // 个人的话查person表
                        String query_sql = "SELECT id FROM person WHERE name='";
                        query_sql += person_name + "' AND ID_number='" + ID_number + "'";
                        preparedStatement2 = connection.prepareStatement(query_sql);
                        resultSet2 = preparedStatement2.executeQuery();
                        if (resultSet2.next()) {
                            receiver_id = String.valueOf(resultSet2.getInt("id"));
                        }

                    } else { // 否则为企业类型，查询company表
                        String query_sql = "SELECT id FROM company WHERE name='" + company_name + "'";
                        preparedStatement2 = connection.prepareStatement(query_sql);
                        resultSet2 = preparedStatement2.executeQuery();
                        if (resultSet2.next()) {
                            receiver_id = String.valueOf(resultSet2.getInt("id"));
                        }
                    }

                    // 执行查询语句
                    String query_sql = "SELECT id FROM location WHERE county='" + county + "' AND town='";
                    query_sql += town + "' AND village='" + village + "'";
                    preparedStatement2 = connection.prepareStatement(query_sql);
                    resultSet2 = preparedStatement2.executeQuery();
                    if (resultSet2.next()) {
                        location_id = String.valueOf(resultSet2.getInt("id"));
                    }

                    resultSet2.close();
                    preparedStatement2.close();

                    // 边查询边插入
                    //**************************************************************************************************
                    String str = receiver_id + " '" + receiver_type + "' " + location_id + " " + year + " '" + crop + "' "
                            + area;
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int j=1;j<input.length;j++){
                        suffix.append(","+input[j]);
                    }
                    suffix.append("),");
                    //**************************************************************************************************
                    //JDBCTools.update_delay(pst_for_update, input[0], input[1], input[2], input[3],input[4], input[5]);


                    // 每查询插入10000次打印一次，并且提交一次批任务
                    query_times++;
                    if (query_times % 10000 == 0) {
                        System.out.print("query+insert times:");
                        System.out.println(query_times);
                        pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
                        pst_for_update.executeBatch();
                        conn_for_update.commit();
                        pst_for_update.close();
                        pst_for_update = conn_for_update.prepareStatement("");
                        suffix = new StringBuffer();
                    }
                }
                //最后%10000的部分还要插一次
                pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
                pst_for_update.executeBatch();
                conn_for_update.commit();
                resultSet.close();
                preparedStatement.close();
                pst_for_update.close();
                pst_for_update = conn_for_update.prepareStatement("");
                suffix = new StringBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Long end = new Date().getTime();
            System.out.println("第" + i + "个表 over! time="+(end - begin) / 1000 + " s");
        }

        // 释放连接
        JDBCTools.releaseDB(resultSet, preparedStatement);
        JDBCTools.releaseDB(resultSet2, preparedStatement2);
        JDBCTools.releaseCon(connection);
        JDBCTools.releaseCon(conn_for_update);

        Long end = new Date().getTime();
        System.out.println("program over! time="+(end - begin) / 1000 + " s");
    }


    // 导入fertilizer_pesticide_are表数据----------------------------------------------------------------------------
    public void Import_fertilizer_pesticide_area() {
        Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement preparedStatement2 = null,pst_for_update = null;
        ResultSet resultSet = null;
        
        //********************************************************************
        // 从Excel_info类中加载表名和参数信息
        ArrayList<String> excelList = Excel_info.get_fertilizer_pesticide_are_excelList();
        ArrayList<ArrayList<Object>> argList = Excel_info.get_fertilizer_pesticide_are_argList();
        //********************************************************************
       
        //遍历所有相关表
        for (int i = 0; i < excelList.size(); i++) {
            int query_times = 0;
            try {
                // 获取每张表的内容
            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
                connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
                conn_for_update = JDBCTools.getConnection("jdbcUrl");  //conn_for_update负责批量插入
                conn_for_update.setAutoCommit(false); //改为非自动提交

                //创建批量插入任务
                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,crop,area) VALUES(?,?,?,?,?,?)";
                //**************************************************************************************************
                String pre_sql= "INSERT INTO fertilizer_pesticide_area(receiver_id,receiver_type,location_id,year,type,quantity,unit) VALUES";
                //**************************************************************************************************
                StringBuffer suffix = new StringBuffer();

                pst_for_update = conn_for_update.prepareStatement("");
                
                //第0行是列名，不用插入
                for(int j=1;j < table_result.size() ;j++) {
                    //**************************************************************************************************
                	ArrayList<String> row_result = table_result.get(j);
                	
                	//第二个参数为true代表该字段在sql语句中属于varchar类型
                	String person_name = Trim_str(row_result.get(0),true);
                	String ID_number = Trim_str(row_result.get(1),true);
                	String company_name = Trim_str(row_result.get(2),true);
                	String county = Trim_str(row_result.get(3),true);
                	String town = Trim_str(row_result.get(4),true);
                	String village = Trim_str(row_result.get(5),true);
                	String year = Trim_str(row_result.get(6),false);
                	String type = Trim_str(row_result.get(7),true);
                	String quantity = Trim_str(row_result.get(8),false);
                	String unit = Trim_str(row_result.get(9),true);

                    String receiver_id = "";
                    String location_id = "";
                    //**************************************************************************************************
                    // 判断该记录属于个人还是企业
                    String receiver_type = "个人";
                    if (!"'---'".equals(company_name)) {
                        receiver_type = "企业";
                    }
                    
                    if (receiver_type.equals("个人")) { // 个人的话查person表
                        String query_sql = "SELECT id FROM person WHERE name=";
                        query_sql += person_name + " AND ID_number=" + ID_number + "";
                        preparedStatement2 = connection.prepareStatement(query_sql);
                        resultSet = preparedStatement2.executeQuery();
                        if (resultSet.next()) {
                            receiver_id = String.valueOf(resultSet.getInt("id"));
                        }

                    } else { // 否则为企业类型，查询company表
                        String query_sql = "SELECT id FROM company WHERE name=" + company_name;
                        preparedStatement2 = connection.prepareStatement(query_sql);
                        resultSet = preparedStatement2.executeQuery();
                        if (resultSet.next()) {
                            receiver_id = String.valueOf(resultSet.getInt("id"));
                        }
                    }

                    // 执行查询语句
                    String query_sql = "SELECT id FROM location WHERE county=" + county + " AND town=";
                    query_sql += town + " AND village=" + village + "";
                    
                    preparedStatement2 = connection.prepareStatement(query_sql);
                    resultSet = preparedStatement2.executeQuery();
                    if (resultSet.next()) {
                        location_id = String.valueOf(resultSet.getInt("id"));
                    }
                    
                    resultSet.close();
                    preparedStatement2.close();
                    
                    receiver_id=Trim_str(receiver_id,false);
                    receiver_type=Trim_str(receiver_type,true);
                    location_id=Trim_str(location_id,false);
                    // 边查询边插入
                    //**************************************************************************************************
                    
                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + type + " "
                            + quantity + " " + unit;
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int k=1;k<input.length;k++){
                        suffix.append(","+input[k]);
                    }
                    suffix.append("),");
                    //**************************************************************************************************
                    
                    //System.out.println(pre_sql+suffix.substring(0, suffix.length() - 1));
                    // 每查询插入10000次打印一次，并且提交一次批任务
                    query_times++;
                    if (query_times % 10000 == 0) {
                        System.out.print("query+insert times:");
                        System.out.println(query_times);
                        pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
                        pst_for_update.executeBatch();
                        conn_for_update.commit();
                        pst_for_update.close();
                        pst_for_update = conn_for_update.prepareStatement("");
                        suffix = new StringBuffer();
                    }
                }
                //最后%10000的部分还要插一次
                pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
                pst_for_update.executeBatch();
                conn_for_update.commit();               
                pst_for_update.close();
                pst_for_update = conn_for_update.prepareStatement("");
                suffix = new StringBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Long end = new Date().getTime();
            System.out.println("第" + (i+1) + "个表 已导入! time="+(end - begin) / 1000 + " s");
        }

        // 释放连接
        JDBCTools.releaseCon(connection);
        JDBCTools.releaseCon(conn_for_update);

        Long end = new Date().getTime();
        System.out.println("program over! time="+(end - begin) / 1000 + " s");
    }


}
