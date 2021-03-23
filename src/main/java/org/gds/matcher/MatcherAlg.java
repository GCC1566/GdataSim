package org.gds.matcher;

/**
 * 匹配器算法
 * @author GCC
 */
public interface MatcherAlg {

    /**
     * 计算两个串的相似度
     * @param srcStr
     * @param dstStr
     * @return
     */
   public Float CalculateSimilarityRatioValue(String srcStr,String dstStr);



}
