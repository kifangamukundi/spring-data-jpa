package com.kifangamukundi.jpa.student.repository;

import com.kifangamukundi.jpa.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// All transactions are read only unless explicitly stated at method level
@Transactional(readOnly = true)
// Data access layer
// We can extend JpaRepository, PagingAndSortingRepository, CrudRepository
@Repository
// Passing the Entity and its Identifier
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Predefined Methods -> save, saveAll, count, findById, findAll, deleteById etc

    // Custom queries instead of the predefined ones
    // @Query allows us to write JPQL and also Native queries
    // @Query allows us to have FULL control over JPQL query generated

    // JPQL Query Methods
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
            String firstName, Integer age);


    // Defining CUSTOM NATIVE queries specific to a given database
    @Query(
            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true)
    // If you use native format things can break in case the database engine is changed
    // So its best to make use of jpql as much as possible
    // Name parameters -> :firstName and :age then map arguments using @Param making positions not to matter
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    // Use it when modifying data -> overiding the @Transactional at class level
    @Transactional
    // Indicates that query does NOT need to map anything from db into entities but, instead we are just modifying data in a table
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
