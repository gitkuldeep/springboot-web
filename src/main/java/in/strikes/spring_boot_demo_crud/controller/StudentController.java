package in.strikes.spring_boot_demo_crud.controller;

import in.strikes.spring_boot_demo_crud.dto.StudentDto;
import in.strikes.spring_boot_demo_crud.entity.Student;
import in.strikes.spring_boot_demo_crud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        System.out.println("Inside StudentController::  Student create request received successfully :: " + studentDto.toString());
        StudentDto studentDtoNew = studentService.createStudent(studentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentDtoNew);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id ,@RequestBody StudentDto studentDto){
        System.out.println("Inside StudentController::  Student update request received successfully :: " + studentDto.toString());
        StudentDto studentDtoUpdated = studentService.updateStudent(id, studentDto);
        if(studentDtoUpdated == null){
            System.out.println("Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentDtoUpdated);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id){
        System.out.println("Inside StudentController::  Student delete request received successfully for id :: " + id);
        Boolean deleted = studentService.deleteStudent(id);
        if(!deleted){
            System.out.println("Student not deleted");
            return ResponseEntity.status(HttpStatus.OK).body(deleted);
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        System.out.println("Inside StudentController::  Student get request received successfully :: " + id);
        StudentDto studentDto = studentService.getStudentById(id);
        if(studentDto == null){
            System.out.println("Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        System.out.println("Inside StudentController::  Student getAll request received successfully");
        List<StudentDto> studentDtos = studentService.getAllStudents();
        if(studentDtos == null){
            System.out.println("Students not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDtos);
    }

}
