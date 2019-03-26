package cool.weiboproject.android.bean;

import cn.bmob.v3.BmobObject;

public class CommentBean extends BmobObject {

    private String weiBoObjectId;
    private long commentTime;//时间
    private String value;//内容
    private String sendUserName;

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
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

    public String getWeiBoObjectId() {
        return weiBoObjectId;
    }

    public void setWeiBoObjectId(String weiBoObjectId) {
        this.weiBoObjectId = weiBoObjectId;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "weiBoObjectId='" + weiBoObjectId + '\'' +
                ", commentTime=" + commentTime +
                ", value='" + value + '\'' +
                ", sendUserName='" + sendUserName + '\'' +
                '}';
    }
}
