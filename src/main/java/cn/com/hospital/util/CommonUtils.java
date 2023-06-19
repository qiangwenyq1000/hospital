package cn.com.hospital.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CommonUtils {
    public static String getCellValue(Row row, int len) {
        if(null == row.getCell(len) || "".equals(row.getCell(len))){
            return "";
        }
        //数字的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_NUMERIC){
            if (!DateUtil.isCellDateFormatted(row.getCell(len))) {
                DecimalFormat df = new DecimalFormat("0");
                return df.format(row.getCell(len).getNumericCellValue());
            } else {// 日期
                DateFormat formater = new SimpleDateFormat("yyyyMMdd");
                return formater.format(row.getCell(len).getDateCellValue());
            }
        }
        //字符串的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_STRING){
            return row.getCell(len).getStringCellValue().trim();
        }
        //公式
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_FORMULA){
            return "";
        }
        //空白
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BLANK){
            return "";
        }
        //Boolean
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BOOLEAN){
            return String.valueOf(row.getCell(len).getBooleanCellValue());
        }
        //ERROR
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_ERROR){
            return String.valueOf(row.getCell(len).getErrorCellValue());
        }
        return "";
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String byteToHexString(byte src){
        StringBuilder stringBuilder = new StringBuilder("");
        int v = src & 0xFF;
        String hv = Integer.toHexString(v);
        if (hv.length() < 2) {
            stringBuilder.append(0);
        }
        stringBuilder.append(hv);
        return stringBuilder.toString().toUpperCase();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 取EXL的值 里面多个种类型 取值不一样   所以写成统一的方法
     *
     * @param row
     * @return Integer
     */
    public static String getTimeCellValue(Row row, int len) {
        if(null == row.getCell(len) || "".equals(row.getCell(len))){
            return "";
        }
        //数字的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_NUMERIC){
            if (DateUtil.isCellDateFormatted(row.getCell(len))) {// 日期
                DateFormat formater = new SimpleDateFormat("HH:mm:ss");
                return formater.format(row.getCell(len).getDateCellValue());
            } else {
                DecimalFormat df = new DecimalFormat("0");
                return df.format(row.getCell(len).getNumericCellValue());
            }
        }
        //字符串的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_STRING){
            return row.getCell(len).getStringCellValue().trim();
        }
        //公式
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_FORMULA){
            return "";
        }
        //空白
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BLANK){
            return "";
        }
        //Boolean
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BOOLEAN){
            return String.valueOf(row.getCell(len).getBooleanCellValue());
        }
        //ERROR
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_ERROR){
            return String.valueOf(row.getCell(len).getErrorCellValue());
        }
        return "";
    }
}
