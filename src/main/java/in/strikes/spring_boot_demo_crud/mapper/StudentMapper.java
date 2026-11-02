package in.strikes.spring_boot_demo_crud.mapper;

import in.strikes.spring_boot_demo_crud.dto.StudentDto;
import in.strikes.spring_boot_demo_crud.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto mapToStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        studentDto.setRollNo(student.getRollNo());
        studentDto.setSubject(student.getSubject());
        return studentDto;
    }

    public Student mapToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setRollNo(studentDto.getRollNo());
        student.setSubject(studentDto.getSubject());
        return student;
    }
}
