package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kuangjun on 7/17/17.
 */
public class ViolationsImport  extends DataImport {
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    String query_sql = "SELECT times from illegal where receiver_id=? AND receiver_type = ? AND year = ?";
    String insert_sql = "INSERT INTO illegal(receiver_id,receiver_type,location_id,year,times) VALUES(?,?,?,?,?)";
    String update_sql = "UPDATE illegal SET times=times+? where receiver_id = ? AND receiver_type =? AND year=?";
    public void importData(ArrayList<ArrayList<String>>data){
        for(int i = 1 ;i<data.size();i++){
            String year = trimStr(data.get(i).get(0),false);
            String county = trimStr(data.get(i).get(1),true);
            String town = trimStr(data.get(i).get(2),true);
            String village = trimStr(data.get(i).get(3),true);
            String name = trimStr(data.get(i).get(4),true);
            String idNumber = trimStr(data.get(i).get(5),true);
            String company = trimStr(data.get(i).get(6),true);
            String times = trimStr(data.get(i).get(7),false);
            //get Receiver type
            String receiverType = getReceiverType(company);
            //get Receiver id
            ArrayList<String> receiverIdList = new ArrayList<String>();
            if(receiverType=="个人"){
                receiverIdList = getReceiverId(name,idNumber);
            }
            else{
                receiverIdList = getReceiverId(company);
            }
            if(receiverIdList.size() == 0){
                continue;
            }
            //get location id
            ArrayList<String>locationIdList = new ArrayList<String>();
            locationIdList = getLocationId(county,town,village);
            if(locationIdList.size()==0){
                continue;
            }
            ArrayList<String> resultList = JDBCTools.get("jdbcUrl",this.query_sql,this.connection,receiverIdList.get(0),receiverType,year);
            if(resultList.size()==0){
                JDBCTools.update("jdbcUrl",insert_sql,this.connection,receiverIdList.get(0),receiverType,locationIdList.get(0),year,times);
            }
            else{
                JDBCTools.update("jdbc",update_sql,this.connection,times,receiverIdList.get(0),receiverType,year);
            }

        }
        JDBCTools.releaseDB(null,this.preparedStatement,this.connection);
    }
    public void importViolations(){
        this.connection = super.getConnection("jdbcUrl");
        Excel_reader excel_reader = new Excel_reader();

        //Read data from csv file
        ArrayList<Object> args = new ArrayList<Object>();
        args.add(0);
        args.add(3);
        args.add(4) ;
        args.add(5);
        args.add(7);
        args.add(8);
        args.add(9);
        args.add(17);
        ArrayList<ArrayList<String>> data;
        try {
           data = excel_reader.xlsx_reader("崇明县-表5：渔业油价补贴-2012.xlsx",args);
           importData(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表5：渔业油价补贴-2013.xlsx",args);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表5：渔业油价补贴-2014.xlsx",args);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
