package org.gds.matcher;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 数据匹配器
 * @author GCC
 */
public class Matcher {

    //匹配算法
    private MatcherAlg matcheralgutil;

    public void setMatcheralgutil(MatcherAlg alg){
        this.matcheralgutil = alg;
    }

    /**
     * 获取最相近数据
     * @param srcStr 主串
     * @param list  待比对列表
     * @return String 最相近结论
     */
    public String getMostSimilarStr(String srcStr, List<String> list) {
        float max=0f;
        String key="";
        if(srcStr.equalsIgnoreCase("date")){
            return new Date().toString();
        }
        if(srcStr.equalsIgnoreCase("id")){
            return UUID.randomUUID().toString();
        }
        for(String obj:list){
            float temp = matcheralgutil.CalculateSimilarityRatioValue(srcStr,obj);
            if(max <= temp){
                max = temp;
                key = obj;
            }
        }
        return key;
    }

}
