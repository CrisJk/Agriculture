package main;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Created by ecnu_kuangjun on 7/12/17.
 * 数据导入类的父类,导入每一张表的类都继承该类
 */
public class DataImport {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //some sql

    String location_sql = "SELECT id FROM location WHERE (county=? AND town= ? AND village=?)";
    String receiver_company_sql = "SELECT id FROM company WHERE (name = ?)";
    String receiver_person_sql = "SELECT id FROM person WHERE (name=? AND ID_number = ?)" ;
    String receiver_person_insert_sql = "INSERT INTO person(name, ID_number) VALUES(?,?)";
    String receiver_company_insert_sql = "INSERT INTO company(name) VALUES(?)";
    String location_insert_sql="INSERT INTO location (county, town, village) VALUES(?,?,?)";

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

     /**获取与当前插入的location_id和receive_id
     *
      @param querySql 查询的sql语句
     * @param insertSql 插入的sql语句
     * @param args
     * @return
     */
    public ArrayList<String> getIdList(String querySql, String insertSql, Object... args){
        for(int i = 0;i<args.length;i++){
            if(args[i]==null){
                args[i]="---";
            }
        }
        ArrayList<String> idList =new ArrayList<>();
        try {
            idList = JDBCTools.get("jdbcUrl",querySql,this.connection,args);
            if(idList.size() == 0){
                JDBCTools.update("jdbcUrl",insertSql,this.connection,args);
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String read = scanner.nextLine();
                    if (read == "aaaa") {
                        break;
                    }
                }
                idList=getIdList(querySql,insertSql,args);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idList;
    }

    /**
     *
     * @param company
     * @return receiver为个人或企业
     */

    public String getReceiverType(String company){
        String receiverType ;
        //获取receiver_type
        if (company == "---" || company == "0") {
            receiverType = "个人";
        }
        else{
            receiverType = "企业";
        }
        return receiverType;
    }

    /**
     *
     * @param name
     * @param idNumber
     * @return 个人id
     */
    public ArrayList<String> getReceiverId(String name,String idNumber){
        ArrayList<String> receiverIdList = new ArrayList<String>() ;
        if(name == "---" && idNumber == "---"){
            return receiverIdList;
        }
        else{
            receiverIdList = this.getIdList(this.receiver_person_sql,this.receiver_person_insert_sql,name,idNumber);
        }
        return receiverIdList;
    }

    /**
     *
     * @param company
     * @return 公司ID
     */
    public ArrayList<String> getReceiverId(String company){
        ArrayList<String> receiverIdList = new ArrayList<String>();
        if(company == "---" || company == "0"){
            return receiverIdList;
        }
        else {
            receiverIdList = getIdList(receiver_company_sql, receiver_company_insert_sql,company);
        }
        return  receiverIdList ;
    }

    /**
     *
     * @param county
     * @param town
     * @param villega
     * @return 地点ID
     */
    public ArrayList<String>getLocationId(String county,String town,String villega){
        ArrayList<String> locationIdList = new ArrayList<String>();
        if(county == null && town == null && villega == null){
            locationIdList.add("-1");
        }
        else{
            locationIdList = getIdList(location_sql,location_insert_sql,county,town,villega);
        }
        return locationIdList;
    }
}
