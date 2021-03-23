package org.gds.emulator.impl;

import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import org.gds.emulator.Emulator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ini文件字典模拟器
 * 需要依赖外部提供一个ini文件
 */
public class IniFileEmulator extends Emulator {

    private Logger logger = Logger.getLogger(IniFileEmulator.class);

    @Override
    public List<String> getAllFieldEnums() {
        return dicValue.readAllFields();
    }

    @Override
    public Map<String, List<Object>> getAllDataExemple() {
        Map<String,List<Object>> result = new HashMap<>();
        Map<String,String> dicdatas = dicValue.readAllDataByConfig();
        try {
            for (String key : dicdatas.keySet()) {
                String temp = dicdatas.get(key);
                result.put(key,JSONArray.parseArray(temp,Object.class));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Object getRandomValue(List<Object> list) {
        return list.get(random.nextInt(list.size()));
    }

}
