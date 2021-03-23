package org.gds.emulator;


import org.gds.factory.AbstractFactory;

/**
 * 数据模拟器工厂
 * @author GCC
 */
public class EmulatorFactory extends AbstractFactory {

    private final static String DEFAULT_Emulator_ClassURL = "emulator.classurl";


    /**
     * 默认获取数据模拟器器
     * 读取内置配置文件获取数据模拟器
     * 默认获取JSON数据模拟器
     * @return Emulator
     */
    public final static Emulator getEmulator(){
        return getEmulator(defaultConfigFileUrl.getFile());
    }

    /**
     * 根据指定配置文件获取数据模拟器
     * 具有一定的灵活性，可以根据复制的配置文件进行动态绑定对象
     * @param configurl 指定配置文件的路径
     * @return Emulator
     */
    public final static Emulator getEmulator(String configurl){
        return getEmulator(configurl,DEFAULT_Emulator_ClassURL);
    }


    /**
     * 根据指定自定义的配置文件获取数据模拟器
     * 任意的.ini配置文件，均可，自定义定义字段
     * @param configurl 指定自定义配置文件的路径
     * @param key 自定义用来获取类路径的字段
     * @return Emulator
     */
    public final static Emulator getEmulator(String configurl,String key){
         return (Emulator)getObject(configurl,key);
    }




}
