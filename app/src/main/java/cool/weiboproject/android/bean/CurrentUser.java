package cool.weiboproject.android.bean;

import cn.bmob.v3.BmobUser;

public class CurrentUser extends BmobUser {

    private String petName;
    private String petKind;
    private String petAge;
    private String petWeight;
    private String petAvatar;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetAvatar() {
        return petAvatar;
    }

    public void setPetAvatar(String petAvatar) {
        this.petAvatar = petAvatar;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "petName='" + petName + '\'' +
                ", petKind='" + petKind + '\'' +
                ", petAge='" + petAge + '\'' +
                ", petWeight='" + petWeight + '\'' +
                ", petAvatar='" + petAvatar + '\'' +
                '}';
    }
}
