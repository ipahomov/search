package com.search.task.loader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IPahomov on 10.07.2016.
 */
public class Test {
    public static void main(String[] args) {
        String url = "?qwery=http://101.ru/?an=port_channel_mp3&channel=69";
        String urlName = "";
        String patern = "(https?://.+)";
        Pattern p = Pattern.compile(patern);
        Matcher matcher = p.matcher(url);
        if(matcher.find()){
            urlName = matcher.group(0);
        }

        System.out.println(urlName);
    }
}
