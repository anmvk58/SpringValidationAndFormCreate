package com.anmv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private int totalMember;
    @NonNull
    private String type;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private List<AccountDTO> accounts;
}
