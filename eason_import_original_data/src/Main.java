import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException  {
		TableControl tab = new TableControl();
//		tab.Import_location();
//		tab.Import_person();
//		tab.Import_company();
		//tab.Import_planting_area();
		tab.Import_fertilizer_pesticide_area();
//		Excel_reader test= new Excel_reader();
//		ArrayList<ArrayList<String>> arr=test.xlsx_reader("崇明县-表7：蔬菜农药补贴-2012.xlsx",0,"dsa",2,3,4,5,6,7,8,9,10,11,12);
//		for(int i=0;i<arr.size();i++){
//			ArrayList<String> row=arr.get(i);
//			for(int j=0;j<row.size();j++){
//				System.out.print(row.get(j)+" ");
//			}
//			System.out.println("");
//		}
		   
	}
}
