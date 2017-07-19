package main;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by kuangjun on 7/19/17.
 */
public class ImmuneImport extends DataImport{
    Connection connnection = null ;
//    String insert_sql()
    public void importData(ArrayList<ArrayList<String>> data){
        for(int i = 1;i<data.size();i++){
            String year = trimStr(data.get(i).get(0),false);
            String county = trimStr(data.get(i).get(1),true);
            String town = trimStr(data.get(i).get(2),true);
            String village = trimStr(data.get(i).get(3),true);
            String name = trimStr(data.get(i).get(4),true);
            String idNumber = trimStr(data.get(i).get(5),true);
            String company = trimStr(data.get(i).get(6),true);
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
//            ArrayList<String> resultList = JDBCTools.get("jdbcUrl",this.query_sql,this.connection,receiverIdList.get(0),receiverType,year);
//            if(resultList.size()==0){
//                JDBCTools.update("jdbcUrl",insert_sql,this.connection,receiverIdList.get(0),receiverType,locationIdList.get(0),year,times);
//            }
//            else{
//                JDBCTools.update("jdbc",update_sql,this.connection,times,receiverIdList.get(0),receiverType,year);
//            }
        }
    }
    public void importImmune(){
        this.connnection = super.getConnection("jdbcUrl");
        Excel_reader excelReader = new Excel_reader();
        ArrayList<Object> array = new ArrayList<Object>();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(7);
        array.add(8);
        array.add(9);
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
         try {
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx",array);
            importData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
