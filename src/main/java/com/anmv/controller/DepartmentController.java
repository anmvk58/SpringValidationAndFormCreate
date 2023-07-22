package com.anmv.controller;

import com.anmv.createForm.CreatingDepartmentForm;
import com.anmv.entity.AccountDTO;
import com.anmv.entity.Department;
import com.anmv.entity.DepartmentDTO;
import com.anmv.entity.SuccessObject;
import com.anmv.form.DepartmentFilterForm;
import com.anmv.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/departments")
@Validated
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments(DepartmentFilterForm form){

        List<Department> departments = service.getAllDepartments(form);

        List<DepartmentDTO> departmentDTOS = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>(){
        }.getType());

        //Gan link DepartmentDetails vao tung phan tu:
        for (DepartmentDTO dto : departmentDTOS){
            dto.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(dto.getId())).withSelfRel());
        }

        return departmentDTOS;
    }

    @GetMapping(value = "/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable(value = "id") int id){
        Department department = service.getDepartmentByID(id);

        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);

        departmentDTO.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(id)).withSelfRel());

        return departmentDTO;
    }

    @PostMapping
    public SuccessObject createNewDepartment(@RequestBody @Valid CreatingDepartmentForm form){
        service.createNewDepartment(form);
        SuccessObject object = new SuccessObject("200", "Department create succesfully!");
        return object;
    }
}
