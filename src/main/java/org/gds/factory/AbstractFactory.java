package org.gds.factory;
import org.apache.log4j.Logger;
import org.gds.util.ConfigUtil;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础工厂，其他组件工厂的实现可用基于该类进行扩展
 * 功能：根据配置文件动态生成对象
 * @author GCC
 */
public abstract class AbstractFactory {

    private static Logger logger = Logger.getLogger(AbstractFactory.class);

    //TODO 可扩展为单例或多例模式
    //对象容器池
    private static Map<String,Object> objectContain = new ConcurrentHashMap<>();

    //默认自带的类控制配置文件
    private final static String DEFAULTCONFIG_FILE_URL = "factoryconfig.ini";

    //默认的配置文件
    public static URL defaultConfigFileUrl = AbstractFactory.class.getClassLoader().getResource(DEFAULTCONFIG_FILE_URL);

    /**
     * 根据配置文件以及key值，获取对象的类路径
     * @param url   配置文件路径
     * @param key   关键字
     * @return  String 类路径
     */
    static String getClassUrl(String url,String key){
        ConfigUtil config = new ConfigUtil(url);
        return config.getValueByConfigkey(key);
    }

    /**
     * 根据指定配置文件及指定关键字生成对象
     * @param url   配置文件路径
     * @param key   关键字
     * @return  Object 具体对象
     */
    public static Object getObject(String url,String key){
        String classurl = getClassUrl(url,key);
        if(objectContain.containsKey(classurl)){
            return objectContain.get(classurl);
        }
        try{
            Class oneclass = Class.forName(classurl);
            Object obj = oneclass.newInstance();
            objectContain.put(classurl,obj);
            return obj;
        }catch (Exception e){
            logger.error(e.getMessage() +" plase check"+ DEFAULTCONFIG_FILE_URL );
        }
        return null;
    }

    /**
     * 缓存池清除
     */
    public static void clearCantionCache(){
        objectContain.clear();
    }


}
