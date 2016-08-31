package com.demo;

import com.utils.HttpclientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nilszhang on 2016/3/22.
 */
public class KanHouseWatcher implements Runnable {

    private final String HOUSE_URL = "http://kanfang.nj.house365.com/#house4671";

    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception  {
        KanHouseWatcher kanHouseWatcher = new KanHouseWatcher();
        kanHouseWatcher.watch365();
    }



    private static List<String> getStringListFromTwo(String oraString, String one, String two, boolean isCut)
    {
        List<String>  backString = new ArrayList<String>();
        Pattern rePat = Pattern.compile(one+".*?"+two);
        Matcher matcher = rePat.matcher(oraString);
        while (matcher.find()) {
            String sFind = matcher.group();
            if(isCut)
            {
                sFind = sFind.substring(one.length(),sFind.length()-two.length());
            }
            backString.add(sFind);
            continue;
        }

        return backString;
    }

    private static String getStringFromTwo(String oraString,String one,String two,boolean isCut)
    {
        String backString = null;
        Pattern rePat = Pattern.compile(one+".*?"+two);
        Matcher matcher = rePat.matcher(oraString);
        while (matcher.find()) {
            backString = matcher.group();
            if(isCut)
            {
                backString = backString.substring(one.length(),backString.length()-two.length());
            }
            break;
        }

        return backString;
    }

    private static String getStringFromTwo(String oraString,String one,String two)
    {

        return getStringFromTwo(oraString,one,two,false);
    }


    private void watch365()  throws Exception {
        String string = HttpclientUtil.get(HOUSE_URL);
        System.out.println(string);
        //List<String> ways = findWays(string);

    }

    private List<String> findWays(String string) {
        String pre =  "】-->";
        String end =   "</a></h3>";

        List<String> stringListFromTwo = getStringListFromTwo(string, pre, end, true);

        for (int i = 0; i < stringListFromTwo.size(); i++) {
            String s =  stringListFromTwo.get(i);
            System.out.println(s);
        }

        return stringListFromTwo;
    }
}
