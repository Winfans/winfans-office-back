package top.wffanshao.office.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述：对象工具类
 */
public final class ObjectUtils {

    /**
     * 描述：对象转字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 描述：对象转长整形
     *
     * @param obj
     * @return
     */
    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    /**
     * 描述：对象转整形
     *
     * @param obj
     * @return
     */
    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }
}