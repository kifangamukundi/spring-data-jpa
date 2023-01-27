package com.kifangamukundi.jpa.enrolment.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Lombok stuff
@Data // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class EnrolmentId implements Serializable {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;
}
