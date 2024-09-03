package com.example.springbootboard.dto;

import com.example.springbootboard.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
        String password,
        String email,
        String nickname,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt
) {
    public static UserAccountDto of(String userId,
                                    String password,
                                    String email,
                                    String nickname,
                                    String createdBy,
                                    LocalDateTime createdAt,
                                    String modifiedBy,
                                    LocalDateTime modifiedAt) {
        return new UserAccountDto(userId, password, email, nickname, createdBy, createdAt, modifiedBy, modifiedAt);
    }

    public static UserAccountDto from(UserAccount userAccount) {
        return new UserAccountDto(userAccount.getUserId(),
                userAccount.getPassword(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getCreatedBy(),
                userAccount.getCreatedAt(),
                userAccount.getModifiedBy(),
                userAccount.getModifiedAt());
    }

    public UserAccount toEntity() {
        return UserAccount.of(userId, password, email, nickname);
    }

}
