package com.atwork.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private int status;

    @Builder
    public User(String email, String name, String description) {
        this.email = email;
        this.name = name;
        this.description = description;
        this.status = 1;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
