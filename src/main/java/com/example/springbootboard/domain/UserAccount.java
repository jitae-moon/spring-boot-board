package com.example.springbootboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false) private String password;
    @Setter @Column(nullable = false) private String email;
    @Setter private String nickname;

    protected UserAccount() { }

    private UserAccount(String userId, String password, String email, String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserAccount of(String userId, String password, String email, String nickname) {
        return new UserAccount(userId, password, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return Objects.equals(this.id, userAccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
