package com.kifangamukundi.jpa.student.entity;

import com.kifangamukundi.jpa.StudentIdCard.entity.StudentIdCard;
import com.kifangamukundi.jpa.book.entity.Book;
import com.kifangamukundi.jpa.course.entity.Course;
import com.kifangamukundi.jpa.enrolment.entity.Enrolment;
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

// Entity stuff
@Entity(name = "Student")
@Table(
        name = "student",
        // Control over the naming of constraints
        // ColumnNames can be an array if multiple constraints
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;

    // Bi-directional relationship -> both StudentIdCard and Student can fetch from both sides
    // Bi-directional is ONLY best for OneToOne relationships
    // Fetching student also fetches the Card
    @OneToOne(
            mappedBy = "student",
            // orphanRemoval -> By deleting a Student the associated StudentIdCard is also deleted
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private StudentIdCard studentIdCard;

    // Bi-directional oneToMany relationship
    // A student can have many books
    @OneToMany(
            mappedBy = "student",
            // Deleting a student also deletes books associated with them
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            // We can override Lazy by using a getter
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    // ManyToMany where "enrolment" table is created automatically
    // Without using an embeddable class
//        @ManyToMany(
//                cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
//        )
//        @JoinTable(
//                name = "enrolment",
//                joinColumns = @JoinColumn(
//                        name = "student_id",
//                        foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
//                ),
//                inverseJoinColumns = @JoinColumn(
//                        name = "course_id",
//                        foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
//                )
//        )
//        private List<Course> courses = new ArrayList<>();

    // Using Embeddable class to create ManyToMany
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student"
    )
    private List<Enrolment> enrolments = new ArrayList<>();

}
