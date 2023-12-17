package com.il.authenticatingldap.daoandrepo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flashcardid", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "createdon", nullable = false)
    private Instant createdon;

    @Column(name = "summary", length = Integer.MAX_VALUE)
    private String summary;

    @Column(name = "question", length = Integer.MAX_VALUE)
    private String question;

    @Column(name = "title_media", length = Integer.MAX_VALUE)
    private String titleMedia;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

    @Column(name = "fs_text", length = Integer.MAX_VALUE)
    private String fsText;

    @Column(name = "fs_image_url", length = Integer.MAX_VALUE)
    private String fsImageUrl;

    @Column(name = "fs_image_required")
    private Boolean fsImageRequired;

    @Column(name = "fs_orientation", length = Integer.MAX_VALUE)
    private String fsOrientation;

    @Column(name = "bs_text", length = Integer.MAX_VALUE)
    private String bsText;

    @Column(name = "bs_image_url", length = Integer.MAX_VALUE)
    private String bsImageUrl;

    @Column(name = "bs_image_required")
    private Boolean bsImageRequired;

    @Column(name = "bs_orientation", length = Integer.MAX_VALUE)
    private String bsOrientation;

}