package ru.arsentiev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.security.Principal;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity<Integer> implements UserDetails, Principal {
    @Serial
    private static final long serialVersionUID = 5888887312509702446L;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, name = "firstname")
    private String firstName;

    @Column(nullable = false, name = "lastname")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Link> links = new ArrayList<>();

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
