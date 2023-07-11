import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class MyTest {

    @Test
    public void test01(){
        Date cur = new Date();
        System.out.println("cur="+cur);

        //开始 14 ： 00 ：00:00
        Date yes = DateUtils.addDays(cur, -1);
        System.out.println("yes="+yes);
        //需要零点时间
        Date truncate = DateUtils.truncate(DateUtils.addDays(cur, -1), Calendar.DATE);
        System.out.println("truncate = " + truncate);


        Date end = DateUtils.truncate(cur, Calendar.DATE);
        System.out.println("end = " + end);

    }
}
