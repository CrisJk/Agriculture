package main;




import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by kuangjun on 7/18/17.
 */
public class ProjectScaleImport extends DataImport{
    Connection connection = null;
    public void importData(ArrayList<ArrayList<String>> data,String sql){
        for(int i = 1 ; i< data.size();i++){
            String year = trimStr(data.get(i).get(0),false);
            String county = trimStr(data.get(i).get(1),true);
            String town = trimStr(data.get(i).get(2),true);
            String village = trimStr(data.get(i).get(3),true);
            String company= trimStr(data.get(i).get(4),true);
            String area = trimStr(data.get(i).get(5),false);


            //get receiver type
            String receiver_type = "企业";
            //get receiver id

            ArrayList<String> receiverIdList = new ArrayList<String>() ;
            receiverIdList = getReceiverId(company);
            if(receiverIdList.size() == 0){
                continue;
            }
            //get location Id
            ArrayList<String> locationIdList = new ArrayList<String>();
            locationIdList = getLocationId(county,town,village);
            if(locationIdList.size() == 0) {
                continue;
            }
            JDBCTools.update("jdbcUrl",sql,this.connection,receiverIdList.get(0),receiver_type,locationIdList.get(0),year,area);
        }
    }
    public void importData2(ArrayList<ArrayList<String>> data,String sql){
        for(int i = 1 ; i< data.size();i++){
            String year = trimStr(data.get(i).get(0),false);
            String county = trimStr(data.get(i).get(1),true);
            String town = trimStr(data.get(i).get(2),true);
            String village = trimStr(data.get(i).get(3),true);
            String company= trimStr(data.get(i).get(4),true);
            String type = trimStr(data.get(i).get(5),true);
            String area = trimStr(data.get(i).get(6),false);


            //get receiver type
            String receiver_type = "企业";
            //get receiver id
            ArrayList<String> receiverIdList = new ArrayList<String>() ;
            receiverIdList = getReceiverId(company);
            if(receiverIdList.size() == 0){
                continue;
            }
            //get location Id
            ArrayList<String> locationIdList = new ArrayList<String>();
            locationIdList = getLocationId(county,town,village);
            if(locationIdList.size() == 0) {
                continue;
            }
            JDBCTools.update("jdbcUrl",sql,this.connection,receiverIdList.get(0),receiver_type,locationIdList.get(0),year,type,area);
        }
    }
    public void importProjectScale(){
        connection = super.getConnection("jdbcUrl");
        Excel_reader excel_reader = new Excel_reader();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<Object> array = new ArrayList<>();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(8);
        //高水平菜田
        try {
            data=excel_reader.xlsx_reader("崇明县-表21：高水平设施菜田建设项目资金（含管护经费）-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','高水平设施菜田建设项目',?)";
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表21：高水平设施菜田建设项目资金（含管护经费）-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','高水平设施菜田建设项目',?)";
            importData(data,sql);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表21：高水平设施菜田建设项目资金（含管护经费）-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','高水平设施菜田建设项目',?)";
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //标准化水产养殖场
        try {
            data = excel_reader.xlsx_reader("崇明县-表23：标准化水产养殖场建设资金-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'渔业','标准化水产养殖场建设',?)";
            importData(data,sql);
        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表23：标准化水产养殖场建设资金-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'渔业','标准化水产养殖场建设',?)";
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表23：标准化水产养殖场建设资金-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'渔业','标准化水产养殖场建设',?)";
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //经济作物标准化
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(9);
        try {
            data = excel_reader.xlsx_reader("崇明县-表24：经济作物标准化基地建设资金-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','经济作物标准化基地建设',?)"  ;
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表24：经济作物标准化基地建设资金-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','经济作物标准化基地建设',?)"  ;
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表24：经济作物标准化基地建设资金-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'种植业','经济作物标准化基地建设',?)"  ;
            importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //市级财政扶持农民--无面积信息
        //水产原种  良种资源保护资金分配 -- 无面积信息
        //稻麦作物高产
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(8);
        array.add(9);
        try {
            data=excel_reader.xlsx_reader("崇明县-表35：稻麦作物高产创建-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','稻麦作物高产_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data=excel_reader.xlsx_reader("崇明县-表35：稻麦作物高产创建-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','稻麦作物高产_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表35：稻麦作物高产创建-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','稻麦作物高产_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //农业部蔬菜标准园
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        try {
            data = excel_reader.xlsx_reader("崇明县-表36：农业部蔬菜标准园及上海市蔬菜标准园创建补贴资金-2013.xlsx",array);
              String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','农业部蔬菜标准园及上海市蔬菜标准园创建',?)";
              importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表36：农业部蔬菜标准园及上海市蔬菜标准园创建补贴资金-2014.xlsx",array);
              String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','农业部蔬菜标准园及上海市蔬菜标准园创建',?)";
              importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表36：农业部蔬菜标准园及上海市蔬菜标准园创建补贴资金-2015.xlsx",array);
              String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'种植业','农业部蔬菜标准园及上海市蔬菜标准园创建',?)";
              importData(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //旅游专项
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(8);
        array.add(10);
        try {
            data = excel_reader.xlsx_reader("崇明县-表39：农业旅游专项扶持资金补贴资金-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'项目','农业旅游专项扶持资金_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表39：农业旅游专项扶持资金补贴资金-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'项目','农业旅游专项扶持资金_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表39：农业旅游专项扶持资金补贴资金-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES(?,?,?,?,'项目','农业旅游专项扶持资金_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //现代农业发展
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(8);
        array.add(10);
        try {
            data = excel_reader.xlsx_reader("崇明县-表40：现代农业发展项目资金公示-2013.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'项目','现代农业发展项目_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = excel_reader.xlsx_reader("崇明县-表40：现代农业发展项目资金公示-2014.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'项目','现代农业发展项目_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        array.clear();
        array.add(0);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.add(9);
        try {
            data = excel_reader.xlsx_reader("崇明县-表40：现代农业发展项目资金公示-2015.xlsx",array);
            String sql = "INSERT INTO project_scale(receiver_id,receiver_type,location_id,year,type,name,area) VALUES (?,?,?,?,'项目','现代农业发展项目_'?,?)";
            importData2(data,sql);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
