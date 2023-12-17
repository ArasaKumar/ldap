package com.il.authenticatingldap.daoandrepo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videos_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "thumbnail", length = Integer.MAX_VALUE)
    private String thumbnail;

    @Column(name = "video_url", nullable = false, length = Integer.MAX_VALUE)
    private String videoUrl;

    @Column(name = "duration", length = 20)
    private String duration;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

    @Column(name = "size")
    private Long size;

}