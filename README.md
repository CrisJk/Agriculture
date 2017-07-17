# Agriculture  

This is a system about agriculture manager.

Tips: 使用Excel_reader在java中从excel读取数据，若单元格无内容或只有空格，则照常返回null或""
      需要在java代码中，对读取的数据进行Trim_str(str,true)处理  
      str代表读取的内容，true/false代表改字段在数据库中是varchar/int
      如果单元格为null或只有空格，那么如果是varchar字段返回"---"，如果是int字段返回"0"
      保证在数据库存储中不出现NULL  ！！！！

########original_data_sql路径：
      从原始excel提取出的有用的数据
      需要建表后将该目录下的sql导入

########jar：
      用到的jar包
      
########excel_reader：
      excel文档读取器
