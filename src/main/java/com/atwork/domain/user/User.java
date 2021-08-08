package com.atwork.domain.user;

import com.atwork.domain.department.Department;
import com.atwork.domain.job.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Where(clause = "status = 'ACTIVE'")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "jobId")
    private Job job;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private int point;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserState status;

    @Builder
    public User(String email, String name, String description) {
        this.email = email;
        this.name = name;
        this.description = description;
        this.point = 0;
        this.status = UserState.ACTIVE;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void inactive() {
        this.status = UserState.INACTIVE;
    }

    public void delete() {
        this.status = UserState.DELETED;
    }

    public enum UserState {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        DELETED("DELETED");

        private String value;
        UserState(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
