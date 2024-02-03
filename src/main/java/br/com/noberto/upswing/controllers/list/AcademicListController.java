package br.com.noberto.upswing.controllers.list;

import br.com.noberto.upswing.dtos.area.BusinessAreaRequest;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.repositories.BusinessAreaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list/business-area")
public class AcademicListController {
    private final BusinessAreaRepository repository;

    AcademicListController(BusinessAreaRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<BusinessAreaRequest>> findAllBusinessArea(){
        List<BusinessArea> businessAreas = repository.findAll();
        List<BusinessAreaRequest> areas = businessAreas.stream().map(BusinessAreaRequest::new).toList();
        return ResponseEntity.ok(areas);
    }
}
