package com.orangelab.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.orangelab.config.Constants;


public class ConfigUtil {

    
    private static Logger log = Logger.getLogger(ConfigUtil.class);
    
    public static String getProperty(String key, String config){
        if(key == null || "".equals(key)){
        	log.error("请传入正确的key信息");
            return null;
        }
            
        if(config == null || "".equals(config)){
        	log.error("请传人正确的配置文件信息");
            return null;
        }
        return loadConfig(config).getProperty(key);
    }

    private static Properties loadConfig(String config){
        Properties p = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(config);
        try {
            if(in == null)
                throw new Exception();
            p.load(in);
        } catch (Exception e) {
        	log.error("配置文件加载失败", e.fillInStackTrace());
        }
        return p;
    }
    
    public static void main(String[] args) {
        String value = ConfigUtil.getProperty("image.maxSize", Constants.CONFIG_COMMON);
        System.out.println(value);
    }

}
