package org.gds.parser;
import org.gds.factory.AbstractFactory;

/**
 * 解析器工厂
 * 根据配置生成解析器
 * 供外部调用
 * @author GCC
 */
public class ParserFactory extends AbstractFactory{


    private final static String DEFAULT_PARSERCLASSURL_KEY="parser.classurl";

    /**
     * 默认获取解析器
     * 读取内置配置文件获取解析器
     * 默认获取JSON解析器
     * @return Parser
     */
    public final static Parser getParser(){
        return getParser(defaultConfigFileUrl.getFile());
    }

    /**
     * 根据指定配置文件获取解析器
     * 所用配置文件必须遵循内置配置文件gdsconfig.ini的内容格式（字段名称）
     * 具有一定的灵活性，可以根据复制的配置文件进行动态绑定对象
     * @param configurl 指定配置文件的路径
     * @return Parser
     */
    public final static Parser getParser(String configurl){
        return getParser(configurl,DEFAULT_PARSERCLASSURL_KEY);
    }


    /**
     * 根据指定自定义的配置文件获取解析器
     * 任意的.ini配置文件，均可，自定义定义字段
     * @param configurl 指定自定义配置文件的路径
     * @param key 自定义用来获取类路径的字段
     * @return Parser
     */
    public final static Parser getParser(String configurl,String key){
        return (Parser)getObject(configurl,key);
    }



}
