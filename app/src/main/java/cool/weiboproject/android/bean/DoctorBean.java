package cool.weiboproject.android.bean;

import cn.bmob.v3.BmobObject;

public class DoctorBean extends BmobObject {

    private String doctorName;
    private String goodAt;
    private String workFrom;
    private String phoneNumber;
    private String avatar;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getWorkFrom() {
        return workFrom;
    }

    public void setWorkFrom(String workFrom) {
        this.workFrom = workFrom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "DoctorBean{" +
                "doctorName='" + doctorName + '\'' +
                ", goodAt='" + goodAt + '\'' +
                ", workFrom='" + workFrom + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
