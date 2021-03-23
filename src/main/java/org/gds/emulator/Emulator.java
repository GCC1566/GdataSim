package org.gds.emulator;

import org.gds.util.ConfigUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 数据模拟器
 * 模拟各类数据
 * @author GCC
 */
public abstract class Emulator {

    public Random random = new Random();

    public ConfigUtil dicValue;

    //默认字典文件
    public String DefaultDicFileName = "dictionary.ini";

    /**
     * 获取所有待选字段
     * @return List<String>
     */
    public abstract List<String> getAllFieldEnums();

    /**
     * 获取全部待选数据字典
     * @return Map
     */
    public abstract Map<String,List<Object>> getAllDataExemple();

    /**
     * 获取随机数据
     * @param list 字典
     * @return Object 随机数据
     */
    public abstract Object getRandomValue(List<Object> list);

    public String getDefaultDicFileName() {
        return DefaultDicFileName;
    }

    public void setDefaultDicFileName(String defaultDicFileName) {
        DefaultDicFileName = defaultDicFileName;
        dicValue = new ConfigUtil(defaultDicFileName);
    }

    /**
     * 随机获取唯一UUID
     * @return String
     */
    String getRandomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 随机获取字符串
     * @return String
     */
    public String getRandomString(int length){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }


}
