package com.Student.repo;



import org.springframework.data.repository.CrudRepository;
import com.Student.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {


}
