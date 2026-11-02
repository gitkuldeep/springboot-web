package in.strikes.spring_boot_demo_crud.service;

import in.strikes.spring_boot_demo_crud.dto.StudentDto;
import in.strikes.spring_boot_demo_crud.entity.Student;
import in.strikes.spring_boot_demo_crud.mapper.StudentMapper;
import in.strikes.spring_boot_demo_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    public StudentService(StudentRepository studentRepository ,StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto createStudent(StudentDto studentDto){
        System.out.println("Inside StudentService::  StudentDto received successfully :: " + studentDto.toString());
        Student student = studentMapper.mapToStudent(studentDto);
        // Persist student to db
        Student studentNew = studentRepository.save(student);
        System.out.println("Inside StudentService::  Student created successfully :: " + studentNew.toString());
        StudentDto studentDto1 = studentMapper.mapToStudentDto(studentNew);
        return studentDto1;
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto){
        System.out.println("Inside StudentService::  StudentDto received successfully :: " + studentDto.toString());
        Optional<Student> studentOptional =  studentRepository.findById(id);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setName(studentDto.getName());
            student.setAge(studentDto.getAge());
            student.setEmail(studentDto.getEmail());
            student.setRollNo(studentDto.getRollNo());
            student.setSubject(studentDto.getSubject());
            Student studentUpdated = studentRepository.save(student);
            System.out.println("Inside StudentService::  Student updated successfully :: " + studentUpdated.toString());
            StudentDto studentDto1 = studentMapper.mapToStudentDto(studentUpdated);
            return studentDto1;
        }
        System.out.println("Student not found in db for id :: " + id + "");
        return null;
    }

    public Boolean deleteStudent(Long id){
        System.out.println("Inside StudentService::  Student id received for deletion successfully for id :: " + id);
        Optional<Student> studentOptional =  studentRepository.findById(id);
        if(studentOptional.isPresent()){
            studentRepository.delete(studentOptional.get());
            System.out.println("Inside StudentService::  Student deleted successfully for id:: " + id);
            return Boolean.TRUE;
        }else{
            System.out.println("Student not found in db for id :: " + id);
        }
        System.out.println("Student not deleted for id :: " + id);
        return Boolean.FALSE;
    }

    public StudentDto getStudentById(Long id){
        System.out.println("Inside StudentService::  Student get request received successfully :: " + id);
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            StudentDto studentDto = studentMapper.mapToStudentDto(student);
            return studentDto;
        }
        return null;
    }

    public List<StudentDto> getAllStudents(){
        System.out.println("Inside StudentService::  Student getAllStudent request received successfully");
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        if(students != null && students.size() > 0){
            students.forEach(student -> {
                StudentDto studentDto = studentMapper.mapToStudentDto(student);
                studentDtos.add(studentDto);
            });
            return studentDtos;
        }
        return null;
    }

}
