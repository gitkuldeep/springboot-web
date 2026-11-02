package in.strikes.spring_boot_demo_crud.repository;

import in.strikes.spring_boot_demo_crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository Since this is interface so this annotation not required
public interface StudentRepository extends JpaRepository<Student, Long> {
}
