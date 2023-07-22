package com.anmv.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class DepartmentFilterForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
}
