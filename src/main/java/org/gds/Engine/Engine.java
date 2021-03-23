package org.gds.Engine;

import org.apache.log4j.Logger;
import org.gds.emulator.Emulator;
import org.gds.matcher.Matcher;
import org.gds.parser.Parser;
import org.gds.parser.structure.FieldTree;
import java.util.*;

/**
 * 数据制造引擎
 * 组装数据
 * @author GCC
 */
public abstract class Engine {

    public Logger logger;

    /**
     * 构建模拟引擎
     * @param obj 日志输出
     */
    public Engine(Class obj){
        logger = Logger.getLogger(obj);
    }

    /**
     * 解析器
     */
    public Parser parser;

    /**
     * 匹配器
     */
     public Matcher matcher;

    /**
     * 模拟数据
     */
     public Emulator emulator;


    /**
     * 获取指定条数的模拟数据
     * 数据不重复
     * @param modeldata 待模拟数据样例
     * @param number    条数
     * @return  Set<String> 结果
     */
    public Set<String> getNotSameSimluatorDatas(String modeldata,Integer number){
        Set<String> result = new HashSet<>();
        FieldTree fieldTree = parser.getFeildTree(modeldata);
        for(int i = 0; i < number;i++){
            String data = getSimluatorData(fieldTree);
            if(result.contains(data)){
                i--;
                continue;
            }
            result.add(data);
        }
        return result;
    }

    /**
     * 随机获取指定数量的模拟数据
     * @param modeldata 待模拟数据样例
     * @param number    条数
     * @return  List<String> 结果
     */
    public List<String> getRandomSimluatorDatas(String modeldata,Integer number){
        List<String> result = new ArrayList<>();
        FieldTree fieldTree = parser.getFeildTree(modeldata);
        for(int i = 0;i < number;i++){
            result.add(getSimluatorData(fieldTree));
        }
        return result;
    }

    /**
     * 获取单个模拟数据
     * @param modeldata 待模拟数据样例
     * @return  String 结果
     */
    public String getSimluatorData(String modeldata){
        FieldTree fieldTree = parser.getFeildTree(modeldata);
        return getSimluatorData(fieldTree);
    }


    public void setParser(Parser parser) {
        this.parser = parser;
    }


    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }


    public void setEmulator(Emulator emulator) {
        this.emulator = emulator;
    }

    /**
     * 根据解析结果生成单个模拟数据
     * @param fieldTree 结构树
     * @return  String 模拟的数据
     */
    public abstract String getSimluatorData(FieldTree fieldTree);

}
