package org.gds.util;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;

/**
 * 配置文件工具类
 * @author GCC
 */
public class ConfigUtil {

    private String configfileurl;

    private Logger logger = Logger.getLogger(ConfigUtil.class);

    public ConfigUtil(String configfileurl){
        this.configfileurl = configfileurl;
    }

    public void setConfigfileurl(String configfileurl) {
        this.configfileurl = configfileurl;
    }

    /**
     * 根据获取配置文件中key获取对应内容
     * @param key 字段
     * @return  String value
     */
    public String getValueByConfigkey(String key){
        InputStream input = null;
        Properties properties = new Properties();
        try {
           input = new BufferedInputStream(new FileInputStream(this.configfileurl));
           properties.load(input);
           if(properties.containsKey(key)){
               return properties.getProperty(key);
           }else{
               logger.error("this "+this.configfileurl+" file not contains '"+key+"'");
           }
        }catch (Exception e){
           logger.error(e.getMessage());
        }finally {
            try{
                input.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return "";
    }

    /**
     * 获取配置文件全部内容
     * @return Map<String,String>
     */
    public Map<String,String> readAllDataByConfig(){
        Map<String,String> result = new HashMap<>();
        BufferedReader input = null;
        Properties properties = new Properties();
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(this.configfileurl)));
            properties.load(input);
            Enumeration<?> enm = properties.propertyNames();
            while(enm.hasMoreElements()){
                String key = (String)enm.nextElement();
                result.put(key,properties.getProperty(key));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                input.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 获取全部的配置字段
     * @return
     */
    public List<String> readAllFields(){
        List<String> result = new ArrayList<>();

        BufferedReader input = null;
        Properties properties = new Properties();
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(this.configfileurl)));
            properties.load(input);
            Enumeration<?> enm = properties.propertyNames();
            while(enm.hasMoreElements()){
                result.add((String)enm.nextElement());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                input.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return result;
    }





}
