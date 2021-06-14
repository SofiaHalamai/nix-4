package ua.com.alevel.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "topics")
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "course_id")
    private Course course;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;

}

