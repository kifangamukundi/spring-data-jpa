package com.kifangamukundi.jpa.StudentIdCard.entity;

import com.kifangamukundi.jpa.student.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;
// Lombok stuff
@Data // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_id_card_number_unique",
                        columnNames = "card_number"
                )
        }
)
public class StudentIdCard {

        @Id
        @SequenceGenerator(
                name = "student_card_id_sequence",
                sequenceName = "student_card_id_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "student_card_id_sequence"
        )
        @Column(
                name = "id",
                updatable = false
        )
        private Long id;

        @Column(
                name = "card_number",
                nullable = false,
                length = 15
        )
        private String cardNumber;

        // Uni-directional relationship -> OneToOne from StudentIdCard to Student but not vice-versa in Student Entity

        // One to One -> StudentIdCard to Student
        // Cascade types -> All, Detach, Merge, Persist, Refresh, Remove
        @OneToOne(
                cascade = CascadeType.ALL,
                // EAGER -> Also fetches the student -> Best for OneToOne
                // LAZY -> Does NOT fetch the student -> Best for OneToMany
                fetch = FetchType.EAGER
        )
        @JoinColumn(
                name = "student_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(
                        name = "student_id_fk"
                )
        )
        private Student student;
}
