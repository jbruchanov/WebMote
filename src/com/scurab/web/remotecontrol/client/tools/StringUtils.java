package com.scurab.web.remotecontrol.client.tools;

public class StringUtils {
    private static final String SLASH = "/";

    /**
     * 
     * @param s
     * @param separator
     *            Currently implemented '/' or '\' only
     * @return
     */
    public static String getParentDir(String s, String separator) {
        if (isEmpty(s)) {
            return null;
        } else if ("\\".equals(separator)) {
            return getParentDir(s);
        } else if (SLASH.equals(separator)) {
            return getParentDirUnix(s);
        } else {
            return null;
        }
    }

    /**
     * Get parent dir for Unix system separator '/'
     * 
     * @param s
     * @return
     */
    public static String getParentDirUnix(String s) {
        if (isEmpty(s)) {
            return null;
        } else if (SLASH.equals(s)) {
            return null;
        }

        String data[] = s.split(SLASH);
        StringBuilder sb = new StringBuilder(SLASH);
        for (int i = 1, n = data.length - 1; i < n; i++) {
            sb.append(data[i]).append(SLASH);
        }
        int len = sb.length();
        if (len > 1) {
            sb.setLength(len - 1);
        }
        return sb.toString();
    }

    /**
     * Get parent dir for Unix system separator '\'
     * 
     * @param s
     * @return
     */
    public static String getParentDir(String s) {
        if (s == null) {
            return null;
        }

        String data[] = s.split("\\\\"); // '\'
        String result = "";
        for (int i = 0; i < data.length - 1; i++) {
            if (result.length() > 0) {
                result = result + data[i] + "\\";
            } else {
                result = data[i] + "\\";
            }
        }

        if (result.trim().length() == 0) {
            result = null;
        } else {
            if (data.length > 2) {
                // be C:\ (keep \)
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    public static String get2DecimalValue(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return String.valueOf(value);
        }
    }

    public static boolean isEmpty(String v) {
        return (!(v != null && v.trim().length() != 0));
    }
}
