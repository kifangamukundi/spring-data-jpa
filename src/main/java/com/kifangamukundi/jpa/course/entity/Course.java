package com.kifangamukundi.jpa.course.entity;

import com.kifangamukundi.jpa.enrolment.entity.Enrolment;
import com.kifangamukundi.jpa.student.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

// Lombok stuff
@Data // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    // ManyToMany Mapped to courses
    // Without using an embeddable class
//        @ManyToMany(
//            mappedBy = "courses"
//        )
//        private List<Student> students = new ArrayList<>();

    // Using Embeddable
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    private List<Enrolment> enrolments = new ArrayList<>();
}
