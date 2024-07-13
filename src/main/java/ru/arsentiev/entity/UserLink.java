package ru.arsentiev.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "user_link")
public class UserLink {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_link")
    private Link link;

    public void setUser(User user) {
        this.user = user;
        user.getUserLinks().add(this);
    }

    public void setLink(Link link) {
        this.link = link;
        link.getUserLinks().add(this);
    }

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
