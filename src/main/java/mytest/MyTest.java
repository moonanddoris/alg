package mytest;

import org.apache.commons.codec.binary.Base64;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lixinhai
 * @date 2022/6/24
 */
public class MyTest {

    public static void main(String[] args) {

        String username = "yiducloud";
        String password = "dmpT6l27LW";
        String auth = new String(Base64.encodeBase64((username + ":" + password).getBytes()));


        Pattern compile = Pattern.compile("([a-zA-Z0-9]+)_([a-zA-Z0-9]+)_(\\w+)_(main|aux)");
        //Matcher matcher = compile.matcher("1400379125_1658307113_doctor_42230248248639488_main");
        Matcher matcher = compile.matcher("1400379125_1679899835_USER_116085407436488705_main");

        if (!matcher.find() || matcher.groupCount() != 4) {
            return;
        }

        Long roomId = Long.valueOf(matcher.group(2));
        String imId = matcher.group(3); // 参加会话人员的im标识 可能是患者或者医生


        String s = "([)]";
        System.out.println("---------------" + s);


        Pattern compile1 = Pattern.compile("http(.*)\\?d=([0-9]+)&c=([0-9]+)");
        Matcher matcher1 = compile1.matcher("https://ihospital-stage.causacloud.com/miniqrcode?d=22009&c=11");

        System.out.println("---------------"+matcher1.find());
        System.out.println("---------------"+matcher1.groupCount());
        System.out.println("---------------"+matcher1.group(2));



    }
}
