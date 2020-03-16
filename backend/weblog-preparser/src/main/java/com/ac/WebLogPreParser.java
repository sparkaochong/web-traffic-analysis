package com.ac;

public class WebLogPreParser {

    public static PreparsedLog parse(String line) {
        if (line.startsWith("#")) {
            return null;
        } else {
            PreparsedLog preParsedLog = new PreparsedLog();
            String[] temps = line.split(" ");
            preParsedLog.setServerTime(temps[0] + " " + temps[1]);
            preParsedLog.setServerIp(temps[2]);
            preParsedLog.setMethod(temps[3]);
            preParsedLog.setUriStem(temps[4]);
            String queryString = temps[5];
            preParsedLog.setQueryString(queryString);
            String[] queryStrTemps = queryString.split("&");
            String command = queryStrTemps[1].split("=")[1];
            preParsedLog.setCommand(command);
            String profileIdStr = queryStrTemps[2].split("=")[1];
            preParsedLog.setProfileId(getProfileId(profileIdStr));
            preParsedLog.setServerPort(Integer.parseInt(temps[6]));
            preParsedLog.setClientIp(temps[8]);
            preParsedLog.setUserAgent(temps[9].replace("+", " "));
            String tempTime = preParsedLog.getServerTime().replace("-", "");
            preParsedLog.setDay(Integer.parseInt(tempTime.substring(0, 8)));
            preParsedLog.setMonth(Integer.parseInt(tempTime.substring(0, 6)));
            preParsedLog.setYear(Integer.parseInt(tempTime.substring(0, 4)));
            return preParsedLog;
        }
    }

    private static int getProfileId(String profileIdStr) {
        return Integer.valueOf(profileIdStr.substring(profileIdStr.indexOf("-") + 1));
    }

}
