import com.freeter.common.util.DateUtil;
import com.freeter.common.util.JedisUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 测试纯数字 {
    public static void main(String[] args){
        //目标字符串
        String str = "22,";
        if(str.indexOf(",") >= 0){
            //截取获得字符串数组
            String[] strArray = str.split(",");
            for (int x = 0; x < strArray.length; x++) {
                if (x == 0){
                    System.out.println("000000000:" + strArray[x]);
                }else if (x == 1){
                    System.out.println("111111111111:" + strArray[x]);
                }

            }
        }

       /* String aaa="2020-06-18";
        boolean res=DateUtil.belongCalendarBefore(aaa);
        if (res){
            System.out.println("---------true---------"+res);
        }else {
            System.out.println("---------false---------"+res);
        }*/
        /*final JedisUtils jedis = new JedisUtils("116.62.204.50",6379 ,"awH3mHmkNJB87Rxm");
        Long res=jedis.setnx("S2bb6c6002-48e0-476f-af8c-936cae7db585", "1234567");
        Long sss=jedis.setnx("S2bb6c6002-48e0-476f-af8c-936cae7db585", "1234567");
        jedis.Expire("S2bb6c6002-48e0-476f-af8c-936cae7db585" ,60);
        System.out.println("--------------1111111----------:"+res);
        System.out.println("--------------2222222----------:"+sss);*/
       /* jedis.rpush("anClassId:"+1, "10");
        jedis.rpush("队列", "1");
        jedis.rpush("队列", "1");
        jedis.rpush("队列", "2");
        jedis.rpush("队列", "3");
        jedis.rpush("队列", "4");
        jedis.lrem("队列", 0, "5");
        for (int i=0;i<10;i++){
            System.out.println(i);
            if (i==8){
                break;
            }
        }*/
       // System.out.println(jedis.lpop("队列"));//移除并返回列表 key 的尾元素
       /* Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );

        Long minGroupPrice=990L;
        Long couponDiscount=400L;
        Long juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
        Long promotionRate=100L;//佣金比例，千分比
        BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
        BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
        BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(cc);*/
       /* String mobile="15137065943";
        String phone=mobile.substring(0, 3)+"*****"+mobile.substring(7, 11);
        System.out.println(phone);*/
        //final JedisUtils jedis = new JedisUtils("116.62.204.50",6379 ,"awH3mHmkNJB87Rxm");
        //jedis.set("aa", "123");
        /*long startTime = System.currentTimeMillis();
        Date date=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyMMddhhmmss");
        String dateString=sdf.format(date);
        System.out.println("---------"+dateString);
        String str="2584299757183";
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence)str);
        boolean result=matcher.matches();
        if (result) {
            System.out.println("该字符串是纯数字");
        }if (!result){
            System.out.println("该字符串不是纯数字");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间:" + (endTime - startTime) + "ms");*/
        /*int gearsStatus=3;
        String number = null;
        String time = null;
        if (gearsStatus == 1){
            number =jedis.get("am_oneNumber");
            time =jedis.get("am_oneTime");
        }if (gearsStatus == 2){
            number =jedis.get("am_twoNumber5");
            time =jedis.get("am_twoTime5");
        }if (gearsStatus == 3){
            number =jedis.get("am_threeNumber");
            time =jedis.get("am_threeTime");
        }
        System.out.println("----"+number);
        System.out.println("----"+time);*/
    }
}
