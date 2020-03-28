package cn.edu.bupt.ch2_1.repository;

public interface NoPassword {
        //创建一个投影接口，因为有时候获取用户不必获取其密码
        String getUid();
        String getNickname();
}
