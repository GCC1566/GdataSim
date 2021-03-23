package org.gds;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.gds.Engine.Engine;
import org.gds.Engine.EngineFactory;
import org.gds.util.ConfigUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;


/**
 * 试用样例
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class);
    //样例数据配置文件
    final static URL modeldatafileurl = App.class.getClassLoader().getResource("modeldata.ini");

    static ConfigUtil configUtil = new ConfigUtil(modeldatafileurl.getFile());

    static String json = "";

    /**
     * 将数据写成json文件
     * @return
     */
    public static Boolean writeJsonFile(String path, JSONObject jsondata){
        File file=new File(path);
        try {
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);//实例化FileOutputStream
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");//将字符流转换为字节流
            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象
            bufferedWriter.write(stringToJSONFormData(jsondata.toJSONString()));//将格式化的jsonarray字符串写入文件
            bufferedWriter.flush();//清空缓冲区，强制输出数据
            bufferedWriter.close();//关闭输出流

        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }


    static String stringToJSONFormData(String strJson){
        int tabNum = 0;
        StringBuffer jsonFormat = new StringBuffer();
        int length = strJson.length();
        for (int i = 0; i < length; i++) {
            char c = strJson.charAt(i);
            if (c == '{') {
                tabNum++;
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum,true));
            } else if (c == '}') {
                tabNum--;
                jsonFormat.append("\n");
                jsonFormat.append(getSpaceOrTab(tabNum,true));
                jsonFormat.append(c);
            } else if (c == ',') {
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum,true));
            } else {
                jsonFormat.append(c);
            }
        }
        return jsonFormat.toString();
    }

    static String getSpaceOrTab(int tabNum,boolean enter) {
        StringBuffer sbTab = new StringBuffer();
        for (int i = 0; i < tabNum; i++) {
            if (enter) {
                sbTab.append('\t');
            } else {
                sbTab.append("    ");
            }
        }
        return sbTab.toString();
    }

    static String TimeFileName(){
        String name = "resultData";
        String classPath =App.class.getClass().getResource("/").getPath();
        return classPath+name+System.currentTimeMillis()+".json";
    }



    public static void main( String[] args )
    {
        Engine engine = EngineFactory.produceDefaultEngine();

        json = configUtil.getValueByConfigkey("modeldata");

        int num = Integer.parseInt(configUtil.getValueByConfigkey("number"));

        List<String> list = engine.getRandomSimluatorDatas(json,num);

        JSONObject json = new JSONObject(true);
        JSONArray jsonarray = new JSONArray();
        for(String s:list){
            jsonarray.add(JSONObject.parse(s));
        }
        json.put("data",jsonarray);
        writeJsonFile(TimeFileName(),json);


    }














}
