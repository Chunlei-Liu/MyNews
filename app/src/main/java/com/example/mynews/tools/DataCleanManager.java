package com.example.mynews.tools;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;


// 清除缓存
public class DataCleanManager {

    private static final String TAG = "MainActivity";

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        Log.i(TAG, ">>>cleanInternalCache: " + context.getCacheDir());
        deleteFilesByDirectory(context.getCacheDir());
    }


    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param file
     */
    private static void deleteFilesByDirectory(File file) {

        if (file.isDirectory()) {
            File[] zFiles = file.listFiles();
            for (File file2 : Objects.requireNonNull(zFiles)) {
                deleteFilesByDirectory(file2);
            }
            file.delete();
        } else {

            file.delete();
        }

    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File value : fileList) {
                // 如果下面还有文件
                if (value.isDirectory()) {
                    size = size + getFolderSize(value);
                } else {
                    size = size + value.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;

        BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
        return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "GB";

    }

    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }
}