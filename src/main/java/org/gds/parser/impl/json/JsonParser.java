package org.gds.parser.impl.json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.gds.parser.Parser;
import org.gds.parser.ValueType;
import org.gds.parser.structure.FieldTree;
import java.util.List;


/**
 * json数据解析器
 * @author GCC
 */
public class JsonParser implements Parser {

    private static final Logger logger = Logger.getLogger(JsonParser.class);

    @Override
    public boolean isLegal(String textStr) {
        if(isJsonObject(textStr) || isJsonArray(textStr)){
            return true;
        }
        return false;
    }

    /**
     * 该结构是否为jsonarray
     * @param textStr
     * @return
     */
    private boolean isJsonArray(String textStr){
        try{
            JSONObject.parseArray(textStr);
            return true;
        }catch (Exception e){
            logger.warn("this is not json Array" + e.getMessage());
        }
        return false;
    }

    /**
     * 该结构是否为JsonObject
     * @param textStr
     * @return
     */
    private boolean isJsonObject(String textStr){
        try{
            JSON.parseObject(textStr);
            return true;
        }catch (Exception e){
            logger.warn("this "+textStr+" not a JSON !" + e.getMessage());
        }
        return false;
    }

    @Override
    public FieldTree getFeildTree(String textStr) {
        FieldTree resultTree = new FieldTree(0,-1,"");
        parserKeys(textStr,resultTree);
        return resultTree;
    }

    /**
     * 递归解析数据，翻译为语义树
     * @param textStr
     * @param fieldTree
     */
    private void parserKeys(String textStr,FieldTree fieldTree){
        JSONObject json = null;
        String item = "";
        if(isJsonObject(textStr)){
            json = JSON.parseObject(textStr);
            item = "{}";
        }else if(isJsonArray(textStr)){
            json = JSONObject.parseArray(textStr).getJSONObject(0);
            item = "[]";
        }else {
            return;
        }
        for(String key:json.keySet()){
            FieldTree child = fieldTree.clone();
            child.setFieldname(key);
            child.setValuetype(getValueType(json,key));
            child.setItem(item);
            fieldTree.addChild(child);
            parserKeys(String.valueOf(json.get(key)),child);
        }
    }

    @Override
    public ValueType getValueType(Object json, String key) {
        Object obj = ((JSONObject)json).get(key);
        if(obj instanceof String){
            return ValueType.String;
        }else if(obj instanceof Integer){
            return ValueType.Integer;
        }else if(obj instanceof JSONObject){
            return ValueType.JSON;
        } else if(obj instanceof Long){
            return ValueType.Long;
        }else if(obj instanceof Double){
            return ValueType.Double;
        }else if(obj instanceof Float){
            return ValueType.Float;
        }else if(obj instanceof Short){
            return ValueType.Short;
        }else if(obj instanceof JSONArray || obj instanceof List){
            return ValueType.Array;
        }else{
            return ValueType.Object;
        }
    }

}
