package ru.arsentiev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(nullable = false, unique = true)
    private String title;

    public Short getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
