package main;

import jdk.nashorn.internal.scripts.JD;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kuangjun on 7/18/17.
 */
public class TrainingImport extends DataImport {
    Connection connection = null;
    String query_sql = "SELECT times from training where receiver_id=? AND receiver_type = ? AND year = ?";
    String insert_sql = "INSERT INTO training(receiver_id,receiver_type,location_id,year,times) VALUES(?,?,?,?,?)";
    String update_sql = "UPDATE training SET times=times+? where receiver_id = ? AND receiver_type =? AND year=?";

    public void importData(ArrayList<ArrayList<String>> data){
        for(int i =1 ;i<data.size();i++) {
            String year = trimStr(data.get(i).get(0), false);
            String county = trimStr(data.get(i).get(1), true);
            String town = trimStr(data.get(i).get(2), true);
            String village = trimStr(data.get(i).get(3), true);
            String name = trimStr(data.get(i).get(4), true);
            String idNumber = trimStr(data.get(i).get(5), true);
            String times = trimStr(data.get(i).get(6), false);

            String receiver_type = "个人";
            // get receiver id
            ArrayList<String> receiverIdList = new ArrayList<String>();
            receiverIdList = getReceiverId(name,idNumber);
            if(receiverIdList.size() == 0){
                continue;
            }
            //get location id
            ArrayList<String> locationIdList;
            locationIdList = getLocationId(county,town,village);
            if(locationIdList.size() == 0){
                continue;
            }
            ArrayList<String> resultList  = JDBCTools.get("jdbcUrl",query_sql,this.connection,receiverIdList.get(0),receiver_type,year);
            if(resultList.size() == 0){
                JDBCTools.update("jdbcUrl",insert_sql,this.connection,receiverIdList.get(0),receiver_type,locationIdList.get(0),year,times);
            }
            else{
                JDBCTools.update("jdbcUrl",update_sql,this.connection,times,receiverIdList.get(0),receiver_type,year);
            }

        }
    }
    public void importTraining(){
        this.connection = super.getConnection("jdbcUrl");
        Excel_reader excelReader = new Excel_reader();
        //read data from csv
        ArrayList<Object> array = new ArrayList<Object>();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(7);
        array.add(8);
        array.add(10);
        ArrayList<ArrayList<String>> data ;
        try {
            data = excelReader.xlsx_reader("崇明县-表10：农民培训补贴-2013.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excelReader.xlsx_reader("崇明县-表10：农民培训补贴-2014.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excelReader.xlsx_reader("崇明县-表10：农民培训补贴-2015.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
