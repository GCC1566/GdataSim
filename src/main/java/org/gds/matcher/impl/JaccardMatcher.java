package org.gds.matcher.impl;

import org.apache.commons.collections4.SetUtils;
import org.gds.matcher.MatcherAlg;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * jaccard相似系数比较
 * @author GCC
 */
public class JaccardMatcher implements MatcherAlg {

    @Override
    public Float CalculateSimilarityRatioValue(String srcStr, String dstStr) {
        if(srcStr == null && dstStr ==null){
            return 1f;
        }
        if(srcStr == null || dstStr == null){
            return 0f;
        }
        Set<Integer> aChar = srcStr.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChar = dstStr.chars().boxed().collect(Collectors.toSet());

        int intersection = SetUtils.intersection(aChar,bChar).size();
        if(intersection == 0){
            return 0f;
        }
        int union =  SetUtils.union(aChar,bChar).size();
        return ((float)intersection/(float)union);
    }
}
