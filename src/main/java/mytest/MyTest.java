package mytest;

import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lixinhai
 * @date 2022/6/24
 */
public class MyTest {

    public static String getColumnName(int columnIndex) {
        StringBuilder columnName = new StringBuilder();

        while (columnIndex > 0) {
            int remainder = (columnIndex - 1) % 26;
            columnName.insert(0, (char) (65 + remainder));
            columnIndex = (columnIndex - 1) / 26;
        }

        return columnName.toString();
    }
    public static void main(String[] args) {


        System.out.println("111111111111");
        System.out.println(String.format(null));


        int columnIndex = 1;
        int totalColumns = 50;
        Map<Integer, String> columnMap = new HashMap<>();

        for (int i = 0; i < totalColumns; i++) {
            String columnName = getColumnName(columnIndex);
            columnMap.put(columnIndex, columnName);
            columnIndex++;
        }


        BigDecimal num1 = new BigDecimal("0.03");
        BigDecimal num2 = new BigDecimal("100");
        BigDecimal result = num1.multiply(num2).setScale(0, RoundingMode.UP); // 乘法运算并向上取整
        System.out.println(result);


        Date date1 = new Date(2023, 5, 1); // 第一个日期
        Date date2 = new Date(2023,6, 30); // 第二个日期

        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        System.out.println(diffInDays);

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
