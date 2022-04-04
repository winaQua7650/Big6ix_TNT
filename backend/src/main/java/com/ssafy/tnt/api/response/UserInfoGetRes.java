package com.ssafy.tnt.api.response;

import com.ssafy.tnt.db.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * [GET] /api/users/{userId}
 * 유저 정보 API 요청에 대한 Response 값 정의.
 */
@Getter
@Setter
@Builder
public class UserInfoGetRes {
    private String name;
    private String userId;
    private String email;
    private LocalDate birth;
    private LocalDate reg;

    public static UserInfoGetRes of(User user) {
       UserInfoGetRes userInfoGetRes= UserInfoGetRes.builder()
               .userId(user.getUserId())
               .name(user.getName())
               .email(user.getEmail())
               .birth(user.getBirth())
                .build();
        return userInfoGetRes;
    }
}
