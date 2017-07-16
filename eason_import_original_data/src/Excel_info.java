import java.util.ArrayList;
import java.util.HashMap;

public class Excel_info {
	
	ArrayList<String> excelList = new ArrayList<String>();
    ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    
  //填写需要读取的文件名
    static public ArrayList<String> get_fertilizer_pesticide_are_excelList(){
    	// 涉及的表有： 7，17，18，43
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("崇明县-表7：蔬菜农药补贴-2012.xlsx");
    	list.add("崇明县-表7：蔬菜农药补贴-2013.xlsx");
    	list.add("崇明县-表7：蔬菜农药补贴-2014.xlsx");
    	list.add("崇明县-表17：水稻农药补贴-2013.xlsx");
    	list.add("崇明县-表17：水稻农药补贴-2014.xlsx");
    	list.add("崇明县-表17：水稻农药补贴-2015.xlsx");
    	list.add("崇明县-表18：商品有机肥补贴-2013.xlsx");
    	list.add("崇明县-表18：商品有机肥补贴-2014.xlsx");
    	list.add("崇明县-表18：商品有机肥补贴-2015.xlsx");
    	list.add("崇明县-表43：小麦赤霉病防治药剂补贴-2013.xlsx");
    	list.add("崇明县-表43：小麦赤霉病防治药剂补贴-2014.xlsx");
    	list.add("崇明县-表43：小麦赤霉病防治药剂补贴-2015.xlsx");
    	return list;
    }
    
    //填写需要读取的参数信息  
    static public ArrayList<ArrayList<Object>> get_fertilizer_pesticide_are_argList(){
    	//姓名，身份证，企业名，区县，镇，村，年度，农药肥料类型，使用量，单位
    	ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    	ArrayList <Object> args = null;
    	
    	args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("蔬菜农药");args.add(11);args.add("亩");
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("水稻农药");args.add(10);args.add("亩");
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("商品有机肥");args.add(10);args.add("件");
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("小麦赤霉病防治药剂");args.add(10);args.add("亩");
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        return argList;
    }
}
