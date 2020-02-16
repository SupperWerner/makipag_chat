package ots.layim.util;

public class MyStringUtils {

    /**
     * 截取文件名后缀
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
