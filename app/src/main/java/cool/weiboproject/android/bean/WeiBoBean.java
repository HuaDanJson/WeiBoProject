package cool.weiboproject.android.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

@Entity
public class WeiBoBean extends BmobObject {

    @Id(autoincrement = true)
    private long creatTime;//时间

    @Property(nameInDb = "WeiBoBean")
    private String title;//标题
    private String introduce;//简介
    private String value;//内容
    private String sendUserName;//人名字
    private String collectionUserName;

    @Generated(hash = 707614071)
    public WeiBoBean(long creatTime, String title, String introduce, String value,
                     String sendUserName, String collectionUserName) {
        this.creatTime = creatTime;
        this.title = title;
        this.introduce = introduce;
        this.value = value;
        this.sendUserName = sendUserName;
        this.collectionUserName = collectionUserName;
    }

    @Generated(hash = 1694485307)
    public WeiBoBean() {
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getCollectionUserName() {
        return this.collectionUserName;
    }

    public void setCollectionUserName(String collectionUserName) {
        this.collectionUserName = collectionUserName;
    }

    @Override
    public String toString() {
        return "标题：" + title + "\n" + "简介：" + introduce + "\n" + "内容" + value;
    }
}
