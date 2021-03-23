package org.gds.matcher;
import java.util.List;

/**
 * 数据比对器
 * @author GCC
 */
public abstract class AbstractMatcher{
    /**
     * 相似度算法
     * @param srcStr
     * @param dstStr
     * @return
     */
    public abstract Float getSimilarityRatioValue(String srcStr, String dstStr);

    /**
     * 获取最相近数据
     * @param srcStr
     * @param list
     * @return
     */
    String getMostSimilarStr(String srcStr, List<Object> list) {
        float max=0f;
        String key="";
        for(Object obj:list){
            float temp = getSimilarityRatioValue(srcStr,String.valueOf(obj));
            if(max <= temp){
               max = temp;
               key = String.valueOf(obj);
            }
        }
        return key;
    }
}
