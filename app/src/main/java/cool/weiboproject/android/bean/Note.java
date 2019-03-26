package cool.weiboproject.android.bean;

import cn.bmob.v3.BmobObject;

public class Note extends BmobObject {

    private long creatTime;//帖子时间
    private String noteTitle;//帖子标题
    private String noteContent;//帖子内容
    private String sendUserName;//发帖人名字

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    @Override
    public String toString() {
        return "Note{" +
                "creatTime=" + creatTime +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", sendUserName='" + sendUserName + '\'' +
                '}';
    }
}
