package ru.arsentiev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "links")
public class Link extends BaseEntity<Long> {

    @Column(name = "short_link", columnDefinition = "CHAR(7)")
    private String shortLink;

    @Column(name = "long_link")
    private String longLink;

    @Column(name = "link_name")
    private String linkName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Link link)) return false;
        return Objects.equals(getId(), link.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
