package com.civelek.Ticket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VTUtil {

    public static final String strDateFormat = "dd/MM/yyyy";
    public static final String strDateFormatVadegmecum = "yyyy-mm-dd";

    public static Integer reqGetInteger(String reqStr, Integer defaultVal) {
        if (reqStr != null && !reqStr.equals("") && !reqStr.equals("null")) {
            return Integer.valueOf(reqStr);
        }
        return defaultVal;
    }

    public static Long reqGetLong(String reqStr, Long defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return Long.valueOf(reqStr);
        }
        return defaultVal;
    }

    public static String reqGetString(String reqStr, String defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return reqStr;
        }
        return defaultVal;
    }

    public static Date reqGetDate(String reqStr, Date defaultVal, String format) {
        if (reqStr != null && !reqStr.equals("")) {
            SimpleDateFormat sdf;
            if (format == null) {
                sdf = new SimpleDateFormat(strDateFormat);
            } else {
                sdf = new SimpleDateFormat(format);
            }
            try {
                return sdf.parse(reqStr);
            } catch (ParseException e) {
                return null;
            }
        }
        return defaultVal;
    }
    public static Boolean reqGetBoolean(String reqStr, Boolean defaultVal) {
        try {
            if (reqStr != null && !reqStr.equals("")) {
                return Boolean.valueOf(reqStr);
            }
        } catch (Exception e) {
            return null;
        }
        return defaultVal;
    }

    public static  String replaceText(String text){
       String newText = text.replaceAll("\\p{Punct}", "").replaceAll(" ","");

        return newText;
    }

    public static String convertCreditCardSecurtiy(String creditCard, int left, int right) {


        StringBuilder b = new StringBuilder(creditCard.length());
        b.append(creditCard.substring(0, left));
        for (int z = 0; z < creditCard.length() - left - right; ++z) {
            b.append('*');
        }
        b.append(creditCard.substring(creditCard.length() - right));
        String x = b.toString();
        return x;
    }

}