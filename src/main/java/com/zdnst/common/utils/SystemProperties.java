package com.zdnst.common.utils;

import java.io.InputStream;
import java.util.Properties;


/**
 * 读取web.properties配置文件
 * @author zhongyq
 * @version 1.0.0, 2013-3-19
 */
public class SystemProperties {
	 // the properties object
    private static Properties props;
    
    /**
     * This method returns the property values, if found. It loads the properties
     * from the map properties file if not already loaded.
     * @param       String      property        the property whose value is requested for
     * @param       String      def             the default value to return if property not found
     * @return      String      retVal          the value returned
     */
    public static String getProperty(String property, String def) {
        String retVal = null;
        getProperties();
        if(props != null)
            retVal = props.getProperty(property, def).trim();        
        else
            retVal = def;
        return retVal;
    }
    
    /**
     * @describe： 根据给定的KEY获取配置信息,没有默认返回空字符串
     * @param key
     * @return
     * @author:kui.he
     * @time:2014年9月22日下午12:04:25
     */
   public static String getProperty(String key){
	   return getProperty(key,"");
   }
    /**
     * sets the property for the given key & value
     * @param       strKey          String      Key
     * @param       strValue        String      Value
     * @return                      void        
     *
     **/
    public static void setProperty(String strKey, String strValue) {        
        if(props != null)
            props.setProperty(strKey, strValue);
    }

    /**
     * This method loads the properties object and returns it.
     * @return      Properties      the loaded property object, else null
     */
    public static Properties getProperties() {
        if(props == null) {
            try {
                loadProperties("web.properties");
            }
            catch(Exception exc) {
                System.err.println(exc.getMessage());
                props = null;
            }
        }
        return props;
    }

    /**
     * This method loads the Properties object from the Map Properties file
     * @param       String      file        the configuration file
     */
    public static void loadProperties(String file) throws Exception {
        props = loadPropertiesFile(file);
    }

    /**
     * This method loads a Properties object and returns it
     * @param       String      file        the configuration file to load
     */
    public static Properties loadPropertiesFile(String file) throws Exception {
        Properties retVal = new Properties();
        InputStream in = SystemProperties.class.getClassLoader().getResourceAsStream(file);
        if(in != null)
            retVal.load(in);        
        in.close();
        return retVal;
    }
    
}
