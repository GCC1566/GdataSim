package org.gds.parser;


import org.gds.parser.structure.FieldTree;

/**
 * 解释器接口
 * 用具解析数据的格式
 * 扩展不同数据源时，可实现该接口
 * @author GCC
 */
public interface Parser {

    /**
     * 数据是否合法
     * @param textStr
     * @return
     */
    boolean isLegal(String textStr);

    /**
     * 获取标准数据结构树
     * @param textStr
     * @return
     */
    FieldTree getFeildTree(String textStr);

    /**
     * 获取value数据类型
     * @param key
     * @return
     */
    ValueType getValueType(Object obj, String key);



}
