package org.gds.matcher;

import org.gds.factory.AbstractFactory;
import org.gds.matcher.Matcher;
import org.gds.matcher.MatcherAlg;



/**
 * 匹配器工厂
 * 供外部调用
 * @author GCC
 */
public class MatcherFactory extends AbstractFactory {


    private final static String DEFAULT_MATCHER_ALG_KEY = "matcher.algclassurl";

    /**
     * 默认获取匹配器
     * 此处依赖于默认的配置文件配置内容
     * @return Matcher
     */
    public final static Matcher getMatcher(){
        return getMatcher(defaultConfigFileUrl.getFile());
    }

    /**
     *根据指定配置文件来获取匹配器
     *指定的配置文件格式必须遵循内置格式
     * @param fileurl 配置文件位置
     * @return Matcher
     */
    public final static Matcher getMatcher(String fileurl){
        return getMatcher(fileurl,DEFAULT_MATCHER_ALG_KEY);
    }

    /**
     * 根据指定自定义配置文件，指定字段来获取匹配器
     * @param fileurl 自定义配置文件位置
     * @param key 自定义的获取类路径的字段
     * @return Matcher
     */
    public final static Matcher getMatcher(String fileurl,String key){
        Matcher matcher = new Matcher();
        MatcherAlg alg = (MatcherAlg) getObject(fileurl,key);
        matcher.setMatcheralgutil(alg);
        return matcher;
    }


}
