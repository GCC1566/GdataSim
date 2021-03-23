package org.gds.Engine.impl;

import com.alibaba.fastjson.JSONObject;
import org.gds.Engine.Engine;
import org.gds.parser.ValueType;
import org.gds.parser.structure.FieldTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonEngine extends Engine {

    private static Map<String, List<Object>> cache = new HashMap<>();

    private static List<String> dicCache = new ArrayList<>();

    public JsonEngine() {
        super(JsonEngine.class);
    }



    /**
     * 加载数据字典
     */
    private void initCache(){
        if(cache.isEmpty()){
            cache = emulator.getAllDataExemple();
        }
        if(dicCache.isEmpty()){
            dicCache = emulator.getAllFieldEnums();
        }
    }

    @Override
    public String getSimluatorData(FieldTree fieldTree) {
        initCache();
        return DFScreateJson(fieldTree).toJSONString();
    }


    /**
     * 解析数据填充
     * @param fieldTree 字段树
     * @return JSONObject
     */
    private JSONObject DFScreateJson(FieldTree fieldTree){
        JSONObject json = new JSONObject(true);
        List<FieldTree> fieldTrees = fieldTree.getChilds();
        for(FieldTree f:fieldTrees){
            if(f.getValuetype()!= ValueType.Array && f.getValuetype() != ValueType.JSON){
                String field = matcher.getMostSimilarStr(f.getFieldname(),dicCache);
                Object value = emulator.getRandomValue(cache.get(field));
                json.put(f.getFieldname(),value);
            }else{
                json.put(f.getFieldname(),DFScreateJson(f));
            }
        }
        return json;
    }

}
