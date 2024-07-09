package ru.arsentiev.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_link")
@Getter
@Setter
@ToString
public class UserLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_link")
    private Link link;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "remove_time")
    private LocalDateTime removeTime;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserLink userLink)) return false;
        return Objects.equals(getId(), userLink.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
