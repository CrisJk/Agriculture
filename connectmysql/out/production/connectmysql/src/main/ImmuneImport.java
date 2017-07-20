package main;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by kuangjun on 7/19/17.
 */
public class ImmuneImport extends DataImport{
    Connection connnection = null ;
    String insert_sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,quantity VALUES(?,?,?,?,?)";
    public void importData(String sql,ArrayList<ArrayList<String>> data){
        for(int i = 1;i<data.size();i++){
            String year = trimStr(data.get(i).get(0),false);
            String county = trimStr(data.get(i).get(1),true);
            String town = trimStr(data.get(i).get(2),true);
            String village = trimStr(data.get(i).get(3),true);
            String name = trimStr(data.get(i).get(4),true);
            String idNumber = trimStr(data.get(i).get(5),true);
            String company = trimStr(data.get(i).get(6),true);
            String quantity1 = trimStr(data.get(i).get(7),false);
            String quantity2 = trimStr(data.get(i).get(8),false);
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
            String quantity = Float.toString(Float.parseFloat(quantity1)+Float.parseFloat(quantity2));
            JDBCTools.update("jdbcUrl",sql,this.connection,receiverIdList.get(0),receiverType,locationIdList.get(0),year,quantity);


        }
    }
    ArrayList<Object> getArray(Object ... args){
        ArrayList<Object> array = new ArrayList<Object>();
        for(int i = 0 ;i<args.length;i++){
                array.add(args[i]);
        }
        return  array ;

    }
    public void importImmune(){
        this.connnection = super.getConnection("jdbcUrl");
        Excel_reader excelReader = new Excel_reader();
        ArrayList<Object> array = new ArrayList<Object>();

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            array = getArray(0,3,4,5,7,8,9,12,13);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            String sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫灭活苗',?)";
            importData(sql, data);
            array=getArray(0,3,4,5,7,8,9,14,15);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫合成肽苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,16,17);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql ="INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛羊口蹄疫二价苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,18,19);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛A型口蹄疫苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,20,21);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（单价）',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,22,23);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（双价）',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,24,25);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪瘟',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,26,27);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2013.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'蓝耳病活苗',?)";
            importData(sql,data);


        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            array = getArray(0,3,4,5,7,8,9,12,13);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            String sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫灭活苗',?)";
            importData(sql, data);
            array=getArray(0,3,4,5,7,8,9,14,15);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫合成肽苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,16,17);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql ="INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛羊口蹄疫二价苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,18,19);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛A型口蹄疫苗',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,20,21);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（单价）',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,22,23);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（双价）',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,24,25);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪瘟',?)";
            importData(sql,data);
            array = getArray(0,3,4,5,7,8,9,26,27);
            data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2014.xlsx", array);
            sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'蓝耳病活苗',?)";
            importData(sql,data);
        } catch (IOException e) {
            e.printStackTrace();
        }
         try {
             array = getArray(0,3,4,5,7,8,9,12,13);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             String sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫灭活苗',?)";
             importData(sql, data);
             array=getArray(0,3,4,5,7,8,9,14,15);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪O型口蹄疫合成肽苗',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,16,17);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql ="INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛羊口蹄疫二价苗',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,18,19);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'牛A型口蹄疫苗',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,20,21);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（单价）',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,22,23);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'禽流感（双价）',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,24,25);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'猪瘟',?)";
             importData(sql,data);
             array = getArray(0,3,4,5,7,8,9,26,27);
             data = excelReader.xlsx_reader("崇明县-表11：动物强制免疫-2015.xlsx", array);
             sql = "INSERT INTO immune(receiver_id,receiver_type,location_id,year,type,quantity) VALUES (?,?,?,?,'蓝耳病活苗',?)";
             importData(sql,data);
         } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
