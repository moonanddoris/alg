package mytest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lixinhai
 * @date 2022/6/24
 */
public class MyTest {

    public static void main(String[] args) {

        Short a = null;
        System.out.println(a == Short.valueOf((short)1));
        //System.out.println(a == new Short("1"));

        Pattern compile = Pattern.compile("([a-zA-Z0-9]+)_([a-zA-Z0-9]+)_(\\w+)_(main|aux)");
        //Matcher matcher = compile.matcher("1400379125_1658307113_doctor_42230248248639488_main");
        Matcher matcher = compile.matcher("1400379125_1658307113_d38394760848105472_main");
        matcher.find();


        String s = "([)]";
        System.out.println("---------------" + s);

    }
}
