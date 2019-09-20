package spi;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;

/**
 * Created by jinbiao.yao on 2019/2/28.
 */
public class Demo {
    private Date sendTime;

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }
}
