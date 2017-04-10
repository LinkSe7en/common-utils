package link.se7en.common.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by Link on 2017/3/27.
 * 资源工具
 */
public class ResourcesUtil {

    /**
     * 以流获取资源文件
     * @param resourcePath 资源文件路径
     * @return InputStream
     * @throws IOException
     */
    public static InputStream getResourceAsStream(String resourcePath) throws IOException {
        return ResourcesUtil.class.getClassLoader().getResourceAsStream(resourcePath);
    }

    /**
     * 你需要按行读资源文件吗？看这里
     *
     * 建议用例：
        try(BufferedReader br = ResourcesUtil.getResourceAsBufferedReader(path)) {
            // do your work
        } catch (IOException e) {
            e.printStackTrace();
        }
     * @param resourcePath 资源文件路径
     * @return BufferedReader
     * @throws IOException
     */
    public static BufferedReader getResourceAsBufferedReader(String resourcePath) throws IOException {
        InputStream resourceStream = getResourceAsStream(resourcePath);
        if(resourceStream==null) throw new FileNotFoundException("file " + resourcePath + " not found");

        return new BufferedReader(new InputStreamReader(resourceStream));
    }

    /**
     * 导入配置
     * @param resourcePath 资源文件路径
     * @return Properties
     * @throws IOException
     */
    public static Properties loadProperties(String resourcePath) throws IOException {
        if(!resourcePath.toLowerCase().endsWith(".properties")) throw new IllegalArgumentException("resourcePath must end with .properties");
        InputStream stream = getResourceAsStream(resourcePath);
        if(stream==null) throw new FileNotFoundException("properties file " + resourcePath + " not found");

        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }
}
