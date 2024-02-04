package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.models.Course;
import br.com.noberto.upswing.repositories.BusinessAreaRepository;
import br.com.noberto.upswing.repositories.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list")
public class AcademicListController {
    private final BusinessAreaRepository repository;
    private final CourseRepository courseRepository;

    AcademicListController(BusinessAreaRepository repository, CourseRepository courseRepository)    {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/business-area")
    public ResponseEntity<List<BusinessAreaRequest>> findAllBusinessArea(){
        List<BusinessArea> businessAreas = repository.findAll();
        List<BusinessAreaRequest> areas = businessAreas.stream().map(BusinessAreaRequest::new).toList();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/course-select")
    public ResponseEntity<List<CourseSelect>> findAllCourse(){
        List<Course> courses = courseRepository.findAll();
        List<CourseSelect> selects = courses.stream().map(CourseSelect::new).toList();
        return ResponseEntity.ok(selects);
    }
}
