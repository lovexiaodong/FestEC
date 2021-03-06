package com.newpos.latt.eec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {


    @Id
    private long userId = 0;
    private String name;
    private String avater;
    private String gender;
    private String address;
    @Generated(hash = 1978868221)
    public UserProfile(long userId, String name, String avater, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avater = avater;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvater() {
        return this.avater;
    }
    public void setAvater(String avater) {
        this.avater = avater;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
