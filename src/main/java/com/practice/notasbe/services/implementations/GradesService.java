package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Grades;
import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.Course;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.StudentRepository;
import com.practice.notasbe.repositories.CourseRepository;
import com.practice.notasbe.repositories.GradesRepository;
import com.practice.notasbe.services.interfaces.GradesServiceInterface;
import com.practice.notasbe.shared.dto.GradesDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("notasService")
public class GradesService implements GradesServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    GradesRepository gradesRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    private GradesDTO convertToNotasDTO(Grades grades) {
        GradesDTO gradesDTO = new GradesDTO();
        BeanUtils.copyProperties(grades, gradesDTO);
        return gradesDTO;
    }

//    @Override
//    public GradesDTO notasPorAlumno(String nombre, String apellido) throws ItemNotFoundException{
//        Student student = studentRepository.findByNombresAndApellidos(nombre, apellido);
//        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
//        Grades grades = gradesRepository.findByStudent_id(student);
//        return this.convertToNotasDTO(grades);
//    }

//    @Override
//    public List<GradesDTO> notasPorCurso(String cursoName) throws ItemNotFoundException{
//        Course course = courseRepository.findByName(cursoName);
//        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
//        List<Grades> notas = gradesRepository.findByCourse_id(course);
//        return notas.stream()
//                .map(this::convertToNotasDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public GradesDTO crearNota(GradesDTO notaDTO) {
        Grades nota = new Grades();
        BeanUtils.copyProperties(notaDTO, nota);
        Grades nuevaNota = gradesRepository.save(nota);
        return convertToNotasDTO(nuevaNota);
    }

    @Override
    public GradesDTO editNota(Integer notaID, GradesDTO notaDTO) throws ItemNotFoundException{
        Grades nota = gradesRepository.findById(notaID).orElse(null);
        if (nota == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "NOTA").toUpperCase());
        BeanUtils.copyProperties(notaDTO, nota);
        Grades updatedNota = gradesRepository.save(nota);
        return convertToNotasDTO(updatedNota);
    }

    @Override
    public void eliminarNota(int id) throws ItemNotFoundException{
        Grades nota = gradesRepository.findById(id).orElse(null);
        if (nota == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "NOTA").toUpperCase());
        gradesRepository.deleteById(id);
    }
}
