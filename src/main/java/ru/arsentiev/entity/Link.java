package ru.arsentiev.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortLink;
    private String longLink;
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "link", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLink> userLinks = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public String getShortLink() {
        return this.shortLink;
    }

    public String getLongLink() {
        return this.longLink;
    }

    public List<UserLink> getUserLinks() {
        return this.userLinks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUserLinks(List<UserLink> userLinks) {
        this.userLinks = userLinks;
    }
}
