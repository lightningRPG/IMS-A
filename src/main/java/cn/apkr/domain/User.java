package cn.apkr.domain;

import lombok.Data;

@Data
public class User {
    private String uid;
    private String username;
    private String password;
    private Boolean deleted;

}
