import java.util.ArrayList;
import java.util.HashMap;

public class Excel_info {
	
	ArrayList<String> excelList = new ArrayList<String>();
    ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    
    static public ArrayList<String> get_planting_area_excelList(){
    	// 涉及的表有： 1,3,13,15
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("崇明县-表1：水稻直补、农资综合、机育插秧补贴-2013.xlsx");
    	list.add("崇明县-表1：水稻直补、农资综合、机育插秧补贴-2014.xlsx");
    	list.add("崇明县-表1：水稻直补、农资综合、机育插秧补贴-2015.xlsx");
    	list.add("崇明县-表3：农作物良种补贴-2013.xlsx");
    	list.add("崇明县-表3：农作物良种补贴-2014.xlsx");
    	list.add("崇明县-表3：农作物良种补贴-2015.xlsx");
    	list.add("崇明县-表13：绿肥种植补贴-2013.xlsx");
    	list.add("崇明县-表13：绿肥种植补贴-2014.xlsx");
    	list.add("崇明县-表13：绿肥种植补贴-2015.xlsx");
    	list.add("崇明县-表15：“夏淡”绿叶菜种植补贴-2013.xlsx");
    	list.add("崇明县-表15：“夏淡”绿叶菜种植补贴-2014.xlsx");
    	list.add("崇明县-表15：“夏淡”绿叶菜种植补贴-2015.xlsx");
    	return list;
    }
    
  //填写需要读取的参数信息  
    static public ArrayList<ArrayList<Object>> get_planting_area_argList(){
    	//姓名，身份证，企业名，区县，镇，村，年度，种植作物，种植面积
    	ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    	ArrayList <Object> args = null;
    	
    	args = new ArrayList <Object>();
    	args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
    	args.add(10);args.add(11);
       // 3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add(10);args.add(11);
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("绿肥");args.add(10);
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("夏淡绿叶菜");args.add(11);
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
        
        return argList;
    }
    
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
    
    
  //填写需要读取的文件名
    static public ArrayList<String> get_fishing_scale_excelList(){
    	// 涉及的表有： 5
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("崇明县-表5：渔业油价补贴-2012.xlsx");
    	list.add("崇明县-表5：渔业油价补贴-2013.xlsx");
    	list.add("崇明县-表5：渔业油价补贴-2014.xlsx");
    	return list;
    }
    
    //填写需要读取的参数信息  
    static public ArrayList<ArrayList<Object>> get_fishing_scale_argList(){
    	//姓名，身份证，企业名，区县，镇，村，年度，渔船类型，渔船名号，渔船功率
    	ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    	ArrayList <Object> args = null;
    	
    	args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add(11);args.add(10);args.add(12);
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
       
        return argList;
    }
    
    
  //填写需要读取的文件名
    static public ArrayList<String> get_graziery_scale_excelList(){
    	// 涉及的表有： 
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("崇明县-表11：动物强制免疫-2013.xlsx");
    	list.add("崇明县-表11：动物强制免疫-2014.xlsx");
    	list.add("崇明县-表11：动物强制免疫-2015.xlsx");
    	return list;
    }
    
    //填写需要读取的参数信息  
    static public ArrayList<ArrayList<Object>> get_graziery_scale_argList(){
    	//姓名，身份证，企业名，区县，镇，村，年度,养殖品种，养殖规模
    	ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    	ArrayList <Object> args = null;
    	
    	args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add(10);args.add(11);;
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
       
        return argList;
    }
    
  //填写需要读取的文件名
    static public ArrayList<String> get_insurance_excelList(){
    	// 涉及的表有： 9，
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("崇明县-表9：农业保险补贴-2013.xlsx");
    	list.add("崇明县-表9：农业保险补贴-2014.xlsx");
    	list.add("崇明县-表9：农业保险补贴-2015.xlsx");
    	return list;
    }
    
    //填写需要读取的参数信息  
    static public ArrayList<ArrayList<Object>> get_insurance_argList(){
    	//姓名，身份证，企业名，区县，镇，村，年度,农业类型，险种，投保数量（万）
    	ArrayList<ArrayList<Object>> argList = new  ArrayList<ArrayList<Object>>();
    	ArrayList <Object> args = null;
    	
    	args = new ArrayList <Object>();
        args.add(7);args.add(8);args.add(9);args.add(3);args.add(4);args.add(5);args.add(0);
        args.add("...");args.add(11);args.add(13);
        //3个表参数一样  
        argList.add(args);
        argList.add(args);
        argList.add(args);
       
        return argList;
    }
    
    static public String get_type_by_insurance(String insurance){
    	if(insurance == null)
    		return null;
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("'保淡绿叶菜综合成本价格保险'", "'种植业'");
    	map.put("'大棚设施保险'", "'种植业'");
    	map.put("'果树(水果收获)保险'", "'种植业'");
    	map.put("'麦子种植保险'", "'种植业'");
    	map.put("'农机具综合保险'", "'种植业'");
    	map.put("'蔬菜种植保险'", "'种植业'");
    	map.put("'水稻制种保险'", "'种植业'");
    	map.put("'水稻种植保险'", "'种植业'");
    	map.put("'西甜瓜种植成本保险'", "'种植业'");
    	map.put("'鲜食玉米种植保险'", "'种植业'");
    	map.put("'油菜种植保险'", "'种植业'");
    	map.put("'林木种植保险'", "'种植业'");
    	map.put("'露地种植青菜鸡毛菜气象指数保险'", "'种植业'");
    	
    	
    	map.put("'淡水养殖（经济鱼虾）保险'", "'渔业'");
    	map.put("'淡水养殖（南美白对虾）保险'", "'渔业'");
    	map.put("'群众渔船综合保险'", "'渔业'");
    	map.put("'淡水养殖（南美白对虾）保险'", "'渔业'");
    	
    	
    	map.put("'奶牛养殖保险'", "'畜牧业'");
    	map.put("'能繁母猪养殖保险'", "'畜牧业'");
    	map.put("'生猪养殖保险'", "'畜牧业'");
    	map.put("'羊养殖保险'", "'畜牧业'");
    	
    	String ans = map.get(insurance);
    	if(ans!=null)
    		return ans;
    	ans = insurance;
    	
    	if(ans.indexOf("菜")!=-1){
    		   return "种植业";
    	}else if (ans.indexOf("瓜")!=-1){
    		   return "种植业";
    	}else if (ans.indexOf("果")!=-1){
 		       return "种植业";
 	    }else if (ans.indexOf("农")!=-1){
 	           return "种植业";
  	    }else if (ans.indexOf("林")!=-1){
  		       return "种植业";
  	    }else if (ans.indexOf("米")!=-1){
		       return "种植业";
	    }else if (ans.indexOf("稻")!=-1){
		       return "种植业";
	    }else if (ans.indexOf("麦")!=-1){
		       return "种植业";
	    }else if (ans.indexOf("鱼")!=-1){
		       return "渔业";
	    }else if (ans.indexOf("虾")!=-1){
		       return "渔业";
	    }else if (ans.indexOf("淡水")!=-1){
		       return "渔业";
	    }else if (ans.indexOf("海")!=-1){
		       return "渔业";
	    }else if (ans.indexOf("渔")!=-1){
		       return "渔业";
	    }else if (ans.indexOf("船")!=-1){
		       return "渔业";
	    }else {
	    	   return "畜牧业";
	    }
    }
}
