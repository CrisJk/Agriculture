package main;

import sun.security.pkcs.ParsingException;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Created by ecnu_kuangjun on 7/13/17.
 */
public class SubsidyImport extends DataImport{
    Connection connection = null ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    /**
     * 导入补贴数据
     */
    public void importSubsidy() {
        this.connection = super.getConnection("jdbcUrl");
        //农业类型      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
        int table[] = {1,2,3,5,6,7,8,9,12,13,14,15,17,18,21,23,24,27,31,35,36,39,40,43};
        String [] agricultureType = {"种植业","种植业","种植业","渔业","农机购置","种植业","渔业","保险","贷款","种植业","项目","种植业","种植业","种植业","项目",
                "项目","项目","项目","项目","项目","项目","项目","项目","种植业"};
        String preSql = "SELECT 姓名 name,身份证号码 ID_Number,`合作社/企业名称` company,年度 year, 区县 county,镇 town,村 village,";
        String []sql = {preSql + "种植品种 cropType,`实际补贴(元)` subsidy from table1",
                preSql + "`实际补贴金额（各级财政合计）（元）` subsidy from table2",
                preSql + "补贴品种 cropType,`实际补贴（水稻、麦子、油菜）（公斤）` subsidy1,`实际补贴（玉米/棉花）（元）` subsidy2 from table3",
                preSql + "渔船类型 cropType ,`实际补贴金额（元）` from table5",
                preSql + "农机型号 cropType,`总补贴额(元)` subsidy from table6",
                preSql + "`实际补贴金额（各级财政合计）（元）` subsidy from table7",
                preSql + "`补贴金额（元）` subsidy from table8",
                "SELECT 姓名 name,身份证号码 ID_Number,单位名称 company,年度 year, 区 county,镇 town,村 village," +
                        "按市发文件规定保费补贴金额 subsidy ,险种 cropType from table9",
                preSql + "`当年实际补贴金额(各级财政合计,元)` subsidy from table12",
                preSql + "`实际补贴(元)` subsidy from table13",
                preSql + "`项目总投资(万元)`from table14",
                preSql + "`实际补贴金额` subsidy from table15",
                preSql + "`实际补贴(元)` subsidy from table17",
                preSql + "`实际补贴(元)` subsidy from table18",
                "SELECT 姓名 name,身份证号码 ID_Number,建设单位 company,年度 year, 区县 county,镇 town,村 village," +
                        "`当年实际拨付金额(各级财政合计)` subsidy from table21",
                "SELECT 姓名 name,身份证号码 ID_Number,项目实施单位 company,年度 year, 区县 county,镇 town,村 village,"  +
                        "`当年市财政实际拨付金额(元)` subsidy from table23",
                "SELECT 姓名 name,身份证号码 ID_Number,建设单位 company,年度 year, 区县 county,镇 town,村 village," +
                        "`实际拨付金额(元)` subsidy , 种植品种 cropType from table24",
                preSql + "`市级财政扶持金额（元）` subsidy from table27",
                preSql + "`市级财政经费补贴（元）` subsidy ,种类或品种 cropType from table31",
                "SELECT 姓名 name,身份证号码 ID_Number,`合作社/企业名称`,年度 year, 区县 county,镇 town,村 village," +
                        "`实际补贴(市级财政)(元)` subsidy ,种植品 cropType from table35",
                preSql + "`中央财政 subsidy1,市级财政 subsidy2 from table36",
                preSql + "实际补贴金额 subsidy from table39",
                preSql + "项目总投资 subsidy from table40",
                preSql + "`实际补贴(元)` subsidy from table43"
        };

        String [] cropType={null,"蔬菜",null,null,null,"蔬菜农药","渔船改造",null,"贷款","绿肥植物","农村村庄改造","夏淡绿叶菜","水稻农药","商品有机肥","高水平设施菜田建设","标准化水产养殖场建设",
                        null,"市级财政扶持农民专业合作社项目",null,null,"农业部蔬菜标准园及上海市蔬菜标准园创建补贴资金","农业旅游专项扶持",
                        "现代农业发展项目","小麦赤霉病防治药剂"};
        for(int i = 0 ; i<table.length;i++) {


            this.preparedStatement = super.getPreparedStatement(sql[i], this.connection);

            try {

                this.resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = trimStr(this.resultSet.getString("name"),true);
                    String idNumber = trimStr(this.resultSet.getString("ID_Number"),true);
                    String company = trimStr(this.resultSet.getString("company"),true);
                    String year = trimStr(this.resultSet.getString("year"),false);
                    String county = trimStr(this.resultSet.getString("county"),true);
                    String town = trimStr(this.resultSet.getString("town"),true);
                    String village = trimStr(this.resultSet.getString("village"),true);
                    if(i==0 || i==2 ||i==3||i==4||i==16||i==8) {
                        cropType[i] = trimStr(this.resultSet.getString("cropType"),true);
                        if( i== 3){
                            cropType[i]+="渔船油价";
                        }
                        if(i==16){
                            cropType[i]="经济作物标准化基地建设_"+cropType[i];
                        }
                        if(i==18){
                            cropType[i]="水产原种、良种资源保护_"+cropType[i];
                        }
                        if(i==19){
                            cropType[i]="稻麦作物高产_"+cropType[i];
                        }
                    }


                    String subsidy =null;
                    if(i!=2 && i!=19) {
                        subsidy = trimStr(this.resultSet.getString("subsidy"),false);
                    }
                    else{
                        if(i==2){
                            String subsidy1 = trimStr(this.resultSet.getString("subsidy1"),false);
                            String subsidy2 = trimStr(this.resultSet.getString("subsidy2"),false);
                            if(subsidy1!="0"){
                                subsidy = subsidy1;
                            }
                            else{
                                subsidy = subsidy2;
                            }
                        }
                        if(i==19){
                            String subsidy1 = trimStr(this.resultSet.getString("subsidy1"),false);
                            String subsidy2 = trimStr(this.resultSet.getString("subsidy2"),false);
                            if(subsidy1==null){
                                subsidy1="0";
                            }
                            if(subsidy2==null){
                                subsidy2="0";
                            }
                            subsidy = Integer.toString(Integer.parseInt(subsidy1)+ Integer.parseInt(subsidy2));
                        }
                    }
                    String receiverType = "";
                    ArrayList<String> receiverIdList = new ArrayList<>();
                    //获取receiver_type
                    receiverType = getReceiverType(company);
                    if(receiverType == "个人"){
                        receiverIdList = getReceiverId(name,idNumber);
                    }
                    else{

                        receiverIdList = getReceiverId(company);
                    }
                    if (receiverIdList.size() == 0) {
                        continue;
                    }
                    //获取locationId
                    ArrayList<String> locationIdList = new ArrayList<>();
                    locationIdList = getLocationId(county,town,village);
                    if(locationIdList.size() == 0){
                        continue;
                    }
                    String insert_sql = "INSERT INTO subsidy(receiver_id,receiver_type,location_id,year,type,subsidy_type,money) VALUES(?,?,?,?,?,?,?)";
                    JDBCTools.update("jdbcUrl", insert_sql, this.connection, receiverIdList.get(0), receiverType, locationIdList.get(0), year,agricultureType[i], cropType[i], subsidy);


                }
                JDBCTools.releaseDB(this.resultSet,this.preparedStatement,null);
            } catch (SQLException e) {
               // e.printStackTrace();
            }
        }
    }
}
