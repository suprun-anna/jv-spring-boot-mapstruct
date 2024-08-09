package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mapping(source = "student.group.id", target = "groupId")
    @Mapping(source = "student.subjects", target = "subjectIds", qualifiedByName = "idsBySubjects")
    StudentDto toDto(Student student);

    @Mapping(source = "student.group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectsById")
    Student toModel(CreateStudentRequestDto requestDto);
}
