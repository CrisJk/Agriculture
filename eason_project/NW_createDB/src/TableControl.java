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
	
	
	
	
    static private HashMap<String , Integer> get_person_id_map(Connection connection ){
    	HashMap<String , Integer> map = new HashMap<String , Integer>();
    	String query_sql = "SELECT * FROM person ";
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_sql);
            ResultSet resultSet = preparedStatement.executeQuery();
       
			while (resultSet.next()) {
			    String id = String.valueOf(resultSet.getInt("id"));
			    String name = Trim_str(resultSet.getString("name"),true);
			    String ID_number = Trim_str(resultSet.getString("ID_number"),true);
			    map.put(name+" "+ID_number, Integer.parseInt(id));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
    
    static private HashMap<String , Integer> get_company_id_map(Connection connection ){
    	HashMap<String , Integer> map = new HashMap<String , Integer>();
    	String query_sql = "SELECT * FROM company ";
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_sql);
            ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
			    String id = String.valueOf(resultSet.getInt("id"));
			    String name = Trim_str(resultSet.getString("name"),true);
			    map.put(name, Integer.parseInt(id));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
    
    static private HashMap<String , Integer> get_location_id_map(Connection connection ){
    	HashMap<String , Integer> map = new HashMap<String , Integer>();
    	String query_sql = "SELECT * FROM location ";
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_sql);
            ResultSet resultSet = preparedStatement.executeQuery();
       
			while (resultSet.next()) {
			    String id = String.valueOf(resultSet.getInt("id"));
			    String county = Trim_str(resultSet.getString("county"),true);
			    String town = Trim_str(resultSet.getString("town"),true);
			    String village = Trim_str(resultSet.getString("village"),true);
			    map.put(county+" "+town+" "+village, Integer.parseInt(id));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
//
//    // 导入location表数据
//    public void Import_location() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//
//        HashMap<String, Integer> cate = new HashMap<>();
//        for (int i = 0; i <= 61; i++) {
//            try {
//                // 连接lwdata数据库
//                String sql = "SELECT 区县 country, 镇 town, 村 village FROM table" + i;
//                connection = JDBCTools.getConnection("jdbcUrl");
//                preparedStatement = connection.prepareStatement(sql);
//                // preparedStatement.setString(1, 'table1');
//                resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    String country = Trim_str(resultSet.getString("country"),true);
//                    String town = Trim_str(resultSet.getString("town"),true);
//                    String village = Trim_str(resultSet.getString("village"),true);
//                    cate.put(country + " " + town + " " + village, 1);
//                }
//            } catch (Exception e) {
//                // e.printStackTrace();
//            }
//            // finally {
//            //
//            // }
//            // System.out.println("table"+i);
//        }
//        System.out.println(cate.size());
//        // System.out.println(cate);
//        JDBCTools.releaseDB(resultSet, preparedStatement);
//
//        String sql = "INSERT INTO location (county, town, village) VALUES(?,?,?)";
//        // Iterator<Double> it = decision.descendingKeySet().iterator();
//        String[] input = null;
//        Iterator<String> it = cate.keySet().iterator();
//        while (it.hasNext()) {
//            String location = it.next();
//            input = location.split(" ");
//            JDBCTools.update(connection,  sql, input[0], input[1], input[2]);
//        }
//        JDBCTools.releaseCon(connection);
//        System.out.println("over!");
//    }
//
//    // 导入person表数据
//    public void Import_person() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        HashMap<String, Integer> cate = new HashMap<>();
//        for (int i = 0; i <= 61; i++) {
//            try {
//                // 连接lwdata数据库
//                String sql = "SELECT 姓名 name, 身份证号码 ID_number FROM table" + i;
//                connection = JDBCTools.getConnection("jdbcUrl");
//                preparedStatement = connection.prepareStatement(sql);
//                // preparedStatement.setString(1, 'table1');
//                resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    String name = Trim_str(resultSet.getString("name"),true);
//                    String ID_number = Trim_str(resultSet.getString("ID_number"),true);
//                    cate.put(name + " " + ID_number, 1);
//                }
//            } catch (Exception e) {
//                // e.printStackTrace();
//            }
//            // finally {
//            //
//            // }
//            // System.out.println("table"+i);
//        }
//        System.out.println(cate.size());
//        // System.out.println(cate);
//        JDBCTools.releaseDB(resultSet, preparedStatement);
//
//        String sql = "INSERT INTO person(name, ID_number) VALUES(?,?)";
//        // Iterator<Double> it = decision.descendingKeySet().iterator();
//        String[] input = null;
//        Iterator<String> it = cate.keySet().iterator();
//        while (it.hasNext()) {
//            String location = it.next();
//            input = location.split(" ");
//            JDBCTools.update(connection, sql, input[0], input[1]);
//        }
//        JDBCTools.releaseCon(connection);
//        System.out.println("over!");
//    }
//
//    // 导入company表数据
//    public void Import_company() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        HashMap<String, Integer> cate = new HashMap<>();
//        for (int i = 0; i <= 61; i++) {
//            try {
//                // 连接lwdata数据库
//                String sql = "SELECT `合作社/企业名称` name FROM table" + i;
//                connection = JDBCTools.getConnection("jdbcUrl");
//                preparedStatement = connection.prepareStatement(sql);
//                // preparedStatement.setString(1, 'table1');
//                resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    String name = Trim_str(resultSet.getString("name"),true);
//                    cate.put(name + " " + "#", 1);
//                }
//            } catch (Exception e) {
//                // e.printStackTrace();
//            }
//            // finally {
//            //
//            // }
//            // System.out.println("table"+i);
//        }
//        System.out.println(cate.size());
//        // System.out.println(cate);
//        JDBCTools.releaseDB(resultSet, preparedStatement);
//
//        String sql = "INSERT INTO company(name) VALUES(?)";
//        // Iterator<Double> it = decision.descendingKeySet().iterator();
//        String[] input = null;
//        Iterator<String> it = cate.keySet().iterator();
//        while (it.hasNext()) {
//            String location = it.next();
//            input = location.split(" ");
//            JDBCTools.update(connection, sql, input[0]);
//        }
//        JDBCTools.releaseCon(connection);
//        System.out.println("over!");
//    }
//
//    // 导入planting_area表数据----------------------------------------------------------------------------
//    public void Import_planting_area() {
//    	Long begin = new Date().getTime();
//        Connection connection = null ,conn_for_update = null;
//        PreparedStatement pst_for_update = null;
//        
//        //连接数据库
//        try {
//        	connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
//			conn_for_update = JDBCTools.getConnection("jdbcUrl");
//			conn_for_update.setAutoCommit(false); //改为非自动提交
//		} catch (ClassNotFoundException | IOException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}  //conn_for_update负责批量插入
//       
//        //存储字段到id的映射
//        HashMap<String,Integer> person_id_map = get_person_id_map(connection);
//        HashMap<String,Integer> company_id_map = get_company_id_map(connection);
//        HashMap<String,Integer> location_id_map = get_location_id_map(connection);
//        
//        //********************************************************************
//        // 从Excel_info类中加载表名和参数信息
//        ArrayList<String> excelList = Excel_info.get_planting_area_excelList();
//        ArrayList<ArrayList<Object>> argList = Excel_info.get_planting_area_argList();
//        //********************************************************************
//       System.out.println("excel number: "+excelList.size());
//        //遍历所有相关表
//        for (int i = 0; i < excelList.size(); i++) {
//        
//            int query_times = 0;
//            try {
//                // 获取每张表的内容
//            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
//            
//                //创建批量插入任务
//                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,crop,area) VALUES(?,?,?,?,?,?)";
//                //**************************************************************************************************
//                String pre_sql= "INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,crop,area) VALUES";
//                //**************************************************************************************************
//                StringBuffer suffix = new StringBuffer();
//
//                pst_for_update = conn_for_update.prepareStatement("");
//                
//                //第0行是列名，不用插入
//                for(int j=1;j < table_result.size() ;j++) {
//                    //**************************************************************************************************
//                	ArrayList<String> row_result = table_result.get(j);
//                	
//                	//第二个参数为true代表该字段在sql语句中属于varchar类型
//                	String person_name = Trim_str(row_result.get(0),true);
//                	String ID_number = Trim_str(row_result.get(1),true);
//                	String company_name = Trim_str(row_result.get(2),true);
//                	String county = Trim_str(row_result.get(3),true);
//                	String town = Trim_str(row_result.get(4),true);
//                	String village = Trim_str(row_result.get(5),true);
//                	String year = Trim_str(row_result.get(6),false);
//                	String crop = Trim_str(row_result.get(7),true);
//                	String area = Trim_str(row_result.get(8),false);
//
//                    String receiver_id = "";
//                    String location_id = "";
//                    //**************************************************************************************************
//                    // 判断该记录属于个人还是企业
//                    String receiver_type = "个人";
//                    if (!"'---'".equals(company_name)) {
//                        receiver_type = "企业";
//                    }
//                    
//                    if (receiver_type.equals("个人")) { // 个人的话查person表
//                        receiver_id = person_id_map.get(person_name+" "+ID_number).toString();
//                    } else { // 否则为企业类型，查询company表
//                    	receiver_id = company_id_map.get(company_name).toString();
//                    }
//                    //获取地点id
//                    location_id = location_id_map.get(county+" "+town+" "+village).toString();
//                    
//                    
//                    receiver_id=Trim_str(receiver_id,false);
//                    receiver_type=Trim_str(receiver_type,true);
//                    location_id=Trim_str(location_id,false);
//                    // 边查询边插入
//                    //**************************************************************************************************
//                    
//                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + crop + " "
//                            + area;
//                   
//                    //**************************************************************************************************
//                    
//                    String[] input = null;
//                    input = str.split(" ");
//                    suffix.append("("+input[0]);
//                    for(int k=1;k<input.length;k++){
//                        suffix.append(","+input[k]);
//                    }
//                    suffix.append("),");
//                    
//                    //System.out.println(pre_sql+suffix.substring(0, suffix.length() - 1));
//                    // 每查询插入10000次打印一次，并且提交一次批任务
//                    query_times++;
//                    if (query_times % 10000 == 0) {
//                        System.out.print("query+insert times:");
//                        System.out.println(query_times);
//                        pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
//                        pst_for_update.executeBatch();
//                        conn_for_update.commit();
//                        pst_for_update.close();
//                        pst_for_update = conn_for_update.prepareStatement("");
//                        suffix = new StringBuffer();
//                    }
//                }
//                //最后%10000的部分还要插一次
//                pst_for_update.addBatch(pre_sql+suffix.substring(0, suffix.length() - 1));
//                pst_for_update.executeBatch();
//                conn_for_update.commit();               
//                pst_for_update.close();
//                pst_for_update = conn_for_update.prepareStatement("");
//                suffix = new StringBuffer();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Long end = new Date().getTime();
//            System.out.println("第" + (i+1) + "个表 已导入! time="+(end - begin) / 1000 + " s");
//        }
//
//        // 释放连接
//        JDBCTools.releaseCon(connection);
//        JDBCTools.releaseCon(conn_for_update);
//
//        Long end = new Date().getTime();
//        System.out.println("program over! time="+(end - begin) / 1000 + " s");
//        
//    }


 // 导入fertilizer_pesticide_area表数据----------------------------------------------------------------------------
    public void Import_fertilizer_pesticide_area() {
    	Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement pst_for_update = null;
        
        //连接数据库
        try {
        	connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
			conn_for_update = JDBCTools.getConnection("jdbcUrl");
			conn_for_update.setAutoCommit(false); //改为非自动提交
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //conn_for_update负责批量插入
       
        //存储字段到id的映射
        HashMap<String,Integer> person_id_map = get_person_id_map(connection);
        HashMap<String,Integer> company_id_map = get_company_id_map(connection);
        HashMap<String,Integer> location_id_map = get_location_id_map(connection);
        
        //********************************************************************
        // 从Excel_info类中加载表名和参数信息
        ArrayList<String> excelList = Excel_info.get_fertilizer_pesticide_are_excelList();
        ArrayList<ArrayList<Object>> argList = Excel_info.get_fertilizer_pesticide_are_argList();
        //********************************************************************
       System.out.println("excel number: "+excelList.size());
        //遍历所有相关表
        for (int i = 0; i < excelList.size(); i++) {
        
            int query_times = 0;
            try {
                // 获取每张表的内容
            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
            
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
                        receiver_id = person_id_map.get(person_name+" "+ID_number).toString();
                    } else { // 否则为企业类型，查询company表
                    	receiver_id = company_id_map.get(company_name).toString();
                    }
                    //获取地点id
                    location_id = location_id_map.get(county+" "+town+" "+village).toString();
                    
                    
                    receiver_id=Trim_str(receiver_id,false);
                    receiver_type=Trim_str(receiver_type,true);
                    location_id=Trim_str(location_id,false);
                    // 边查询边插入
                    //**************************************************************************************************
                    
                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + type + " "
                            + quantity + " " + unit;
                    //**************************************************************************************************
                    
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int k=1;k<input.length;k++){
                        suffix.append(","+input[k]);
                    }
                    suffix.append("),");
                    
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

    
    
 // 导入fishing_scale表数据----------------------------------------------------------------------------
    public void Import_fishing_scale() {
    	Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement pst_for_update = null;
        
        //连接数据库
        try {
        	connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
			conn_for_update = JDBCTools.getConnection("jdbcUrl");
			conn_for_update.setAutoCommit(false); //改为非自动提交
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //conn_for_update负责批量插入
       
        //存储字段到id的映射
        HashMap<String,Integer> person_id_map = get_person_id_map(connection);
        HashMap<String,Integer> company_id_map = get_company_id_map(connection);
        HashMap<String,Integer> location_id_map = get_location_id_map(connection);
        
        //********************************************************************
        // 从Excel_info类中加载表名和参数信息
        ArrayList<String> excelList = Excel_info.get_fishing_scale_excelList();
        ArrayList<ArrayList<Object>> argList = Excel_info.get_fishing_scale_argList();
        //********************************************************************
       System.out.println("excel number: "+excelList.size());
        //遍历所有相关表
        for (int i = 0; i < excelList.size(); i++) {
        
            int query_times = 0;
            try {
                // 获取每张表的内容
            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
            
                //创建批量插入任务
                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,type,name,power) VALUES(?,?,?,?,?,?)";
                //**************************************************************************************************
            	String pre_sql= "INSERT INTO fishing_scale(receiver_id,receiver_type,location_id,year,type,name,power) VALUES";
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
                	String name = Trim_str(row_result.get(8),true);
                	String power = Trim_str(row_result.get(9),false);


                    String receiver_id = "";
                    String location_id = "";
                    //**************************************************************************************************
                    // 判断该记录属于个人还是企业
                    String receiver_type = "个人";
                    if (!"'---'".equals(company_name)) {
                        receiver_type = "企业";
                    }
                    
                    if (receiver_type.equals("个人")) { // 个人的话查person表
                        receiver_id = person_id_map.get(person_name+" "+ID_number).toString();
                    } else { // 否则为企业类型，查询company表
                    	receiver_id = company_id_map.get(company_name).toString();
                    }
                    //获取地点id
                    location_id = location_id_map.get(county+" "+town+" "+village).toString();
                    
                    
                    receiver_id=Trim_str(receiver_id,false);
                    receiver_type=Trim_str(receiver_type,true);
                    location_id=Trim_str(location_id,false);
                    // 边查询边插入
                    //**************************************************************************************************
                    
                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + type + " "
                            + name + " " + power;
                    //**************************************************************************************************
                    
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int k=1;k<input.length;k++){
                        suffix.append(","+input[k]);
                    }
                    suffix.append("),");
                    
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

    
 // 导入graziery_scale表数据----------------------------------------------------------------------------
    public void Import_graziery_scale() {
    	Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement pst_for_update = null;
        
        //连接数据库
        try {
        	connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
			conn_for_update = JDBCTools.getConnection("jdbcUrl");
			conn_for_update.setAutoCommit(false); //改为非自动提交
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //conn_for_update负责批量插入
       
        //存储字段到id的映射
        HashMap<String,Integer> person_id_map = get_person_id_map(connection);
        HashMap<String,Integer> company_id_map = get_company_id_map(connection);
        HashMap<String,Integer> location_id_map = get_location_id_map(connection);
        
        //********************************************************************
        // 从Excel_info类中加载表名和参数信息
        ArrayList<String> excelList = Excel_info.get_graziery_scale_excelList();
        ArrayList<ArrayList<Object>> argList = Excel_info.get_graziery_scale_argList();
        //********************************************************************
       System.out.println("excel number: "+excelList.size());
        //遍历所有相关表
        for (int i = 0; i < excelList.size(); i++) {
        
            int query_times = 0;
            try {
                // 获取每张表的内容
            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
            
                //创建批量插入任务
                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,type,name,power) VALUES(?,?,?,?,?,?)";
                //**************************************************************************************************
            	String pre_sql= "INSERT INTO graziery_scale(receiver_id,receiver_type,location_id,year,breed,quantity) VALUES";
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
                	String breed = Trim_str(row_result.get(7),true);
                	String quantity = Trim_str(row_result.get(8),false);


                    String receiver_id = "";
                    String location_id = "";
                    //**************************************************************************************************
                    // 判断该记录属于个人还是企业
                    String receiver_type = "个人";
                    if (!"'---'".equals(company_name)) {
                        receiver_type = "企业";
                    }
                    
                    if (receiver_type.equals("个人")) { // 个人的话查person表
                    	System.out.println(person_name+" "+ID_number);
                        receiver_id = person_id_map.get(person_name+" "+ID_number).toString();
                    } else { // 否则为企业类型，查询company表
                    	receiver_id = company_id_map.get(company_name).toString();
                    }
                    //获取地点id
                    location_id = location_id_map.get(county+" "+town+" "+village).toString();
                    
                    
                    receiver_id=Trim_str(receiver_id,false);
                    receiver_type=Trim_str(receiver_type,true);
                    location_id=Trim_str(location_id,false);
                    // 边查询边插入
                    //**************************************************************************************************
                    
                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + breed + " "
                            + quantity;
                    //**************************************************************************************************
                    
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int k=1;k<input.length;k++){
                        suffix.append(","+input[k]);
                    }
                    suffix.append("),");
                    
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

    
 // 导入graziery_scale表数据----------------------------------------------------------------------------
    public void Import_insurance_scale() {
    	Long begin = new Date().getTime();
        Connection connection = null ,conn_for_update = null;
        PreparedStatement pst_for_update = null;
        
        //连接数据库
        try {
        	connection = JDBCTools.getConnection("jdbcUrl");  //connection负责查询
			conn_for_update = JDBCTools.getConnection("jdbcUrl");
			conn_for_update.setAutoCommit(false); //改为非自动提交
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //conn_for_update负责批量插入
       
        //存储字段到id的映射
        HashMap<String,Integer> person_id_map = get_person_id_map(connection);
        HashMap<String,Integer> company_id_map = get_company_id_map(connection);
        HashMap<String,Integer> location_id_map = get_location_id_map(connection);
        
        //********************************************************************
        // 从Excel_info类中加载表名和参数信息
        ArrayList<String> excelList = Excel_info.get_insurance_excelList();
        ArrayList<ArrayList<Object>> argList = Excel_info.get_insurance_argList();
        //********************************************************************
       System.out.println("excel number: "+excelList.size());
        //遍历所有相关表
        for (int i = 0; i < excelList.size(); i++) {
        
            int query_times = 0;
            try {
                // 获取每张表的内容
            	ArrayList<ArrayList<String>> table_result = Excel_reader.xlsx_reader(excelList.get(i), argList.get(i));
            
                //创建批量插入任务
                //String insert_sql="INSERT INTO planting_area(receiver_id,receiver_type,location_id,year,type,name,power) VALUES(?,?,?,?,?,?)";
                //**************************************************************************************************
            	String pre_sql= "INSERT INTO insurance(receiver_id,receiver_type,location_id,year,type,insurance_type,quantity) VALUES";
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
                	String insurance_type = Trim_str(row_result.get(8),true);
                	String quantity = Trim_str(row_result.get(9),false);
                	
                	type = Excel_info.get_type_by_insurance(insurance_type);  //获取农业类型		

                    String receiver_id = "";
                    String location_id = "";
                    //**************************************************************************************************
                    // 判断该记录属于个人还是企业
                    String receiver_type = "个人";
                    if (!"'---'".equals(company_name)) {
                        receiver_type = "企业";
                    }
                    
                    if (receiver_type.equals("个人")) { // 个人的话查person表
                        receiver_id = person_id_map.get(person_name+" "+ID_number).toString();
                    } else { // 否则为企业类型，查询company表
                    	receiver_id = company_id_map.get(company_name).toString();
                    }
                    //获取地点id
                    location_id = location_id_map.get(county+" "+town+" "+village).toString();
                    
                    
                    receiver_id=Trim_str(receiver_id,false);
                    receiver_type=Trim_str(receiver_type,true);
                    location_id=Trim_str(location_id,false);
                    // 边查询边插入
                    //**************************************************************************************************
                    
                    String str = receiver_id + " " + receiver_type + " " + location_id + " " + year + " " + type + " "
                            + insurance_type + " " + quantity;
                    //**************************************************************************************************
                    
                    String[] input = null;
                    input = str.split(" ");
                    suffix.append("("+input[0]);
                    for(int k=1;k<input.length;k++){
                        suffix.append(","+input[k]);
                    }
                    suffix.append("),");
                    
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