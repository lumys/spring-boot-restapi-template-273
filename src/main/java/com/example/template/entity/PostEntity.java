package com.example.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postSeq;

    @Column(nullable = false, length = 100)
    private String postTitle;

    @Column(nullable = false, length = 500)
    private String postContents;

    public PostEntity(String postTitle, String postContents) {
        this.postTitle = postTitle;
        this.postContents = postContents;
    }
}
