package org.gds.matcher.impl;

import org.apache.commons.collections4.SetUtils;
import org.gds.matcher.MatcherAlg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 基于余弦相似度比较
 * @author GCC
 */
public class CosineMatcher implements MatcherAlg {
    @Override
    public Float CalculateSimilarityRatioValue(String srcStr, String dstStr) {
        if(srcStr == null || dstStr == null){
            return 0f;
        }

        Set<Integer> aChar = srcStr.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChar = dstStr.chars().boxed().collect(Collectors.toSet());

        //字频统计
        Map<Integer,Integer> aMap = new HashMap<>();
        Map<Integer,Integer> bMap = new HashMap<>();
        for(Integer a1:aChar){
            aMap.put(a1,aMap.getOrDefault(a1,0)+1);
        }
        for(Integer b1:bChar){
            bMap.put(b1,bMap.getOrDefault(b1,0)+1);
        }

        //向量处理
        Set<Integer> union = SetUtils.union(aChar,bChar);
        int[] aVec = new int[union.size()];
        int[] bVec = new int[union.size()];
        List<Integer> collect = new ArrayList<>(union);
        for(int i = 0;i < collect.size(); i++){
            aVec[i] = aMap.getOrDefault(collect.get(i),0);
            bVec[i] = bMap.getOrDefault(collect.get(i),0);
        }

        int p1 = 0;
        for(int i = 0; i < aVec.length; i++){
            p1+=(aVec[i] * bVec[i]);
        }

        float p2 = 0f;
        for(int i:aVec){
            p2 += ( i * i );
        }
        p2 = (float)Math.sqrt(p2);

        float p3 = 0f;
        for(int i:bVec){
            p3+=( i * i );
        }
        p3 = (float)Math.sqrt(p3);

        return ((float)p1) / (p2 * p3);
    }
}
