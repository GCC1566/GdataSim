package org.gds.Engine;

import org.gds.emulator.Emulator;
import org.gds.factory.AbstractFactory;
import org.gds.matcher.Matcher;
import org.gds.matcher.MatcherFactory;
import org.gds.parser.Parser;

import java.net.URL;

/**
 * 引擎工厂
 * 组装其他组件
 * @author GCC
 */
public class EngineFactory extends AbstractFactory {

    //默认解析器
    private final static String DEFAULT_PARSER = "parser.classurl";

    //默认数据模拟器
    private final static String DEFAULT_EMULATOR = "emulator.classurl";

    //默认引擎
    private final static String DEFAULT_ENGINE = "engine.classurl";

    //默认匹配算法
    private final static String DEFAULT_MATCHERALG = "matcher.algclassurl";

    //默认字典文件
    public final static String DEFAULT_DICFILE = "dictionary.ini";


    /**
     * 默认生成引擎
     * @return Engine 引擎
     */
    public final static Engine produceDefaultEngine(){
        URL defaulturl = EngineFactory.class.getClassLoader().getResource(DEFAULT_DICFILE);
        return produceEngineWithExternalDicFile(defaulturl.getFile());
    }

    /**
     * 生成携带外部字典的模拟引擎
     * @param dicfileurl 外部字典文件（仅支持ini文件）
     * @return
     */
    public final static Engine produceEngineWithExternalDicFile(String dicfileurl){
        Engine engine = produceCustomizeEngine(defaultConfigFileUrl.getFile(),DEFAULT_ENGINE);
        Emulator emulator = (Emulator)getObject(defaultConfigFileUrl.getFile(),DEFAULT_EMULATOR);
        emulator.setDefaultDicFileName(dicfileurl);
        engine.setEmulator(emulator);
        return engine;
    }

    /**
     * 可自定义生成引擎，指定配置文件，指定字典
     * @param configfileurl 外部配置文件
     * @param dicfileurl 外部字典文件
     * @return
     */
    public final static Engine produceCustomizeEngine(String configfileurl,String dicfileurl){
        clearCantionCache();
        Parser parser = (Parser) getObject(configfileurl,DEFAULT_PARSER);
        Matcher matcher = MatcherFactory.getMatcher(configfileurl,DEFAULT_MATCHERALG);
        Emulator emulator = (Emulator)getObject(configfileurl,DEFAULT_EMULATOR);
        emulator.setDefaultDicFileName(dicfileurl);
        Engine engine = (Engine)getObject(configfileurl,DEFAULT_ENGINE);
        engine.setParser(parser);
        engine.setEmulator(emulator);
        engine.setMatcher(matcher);
        return engine;
    }




}
