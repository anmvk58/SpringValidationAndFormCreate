package com.anmv.createForm;

import com.anmv.validation.DepartmentNameNotExist;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class CreatingDepartmentForm {
    @NotBlank(message = "Tên phòng ban không được phép để trống")
    @Length(max = 15, message = "Tên phòng không quá 15 kí tự")
    @DepartmentNameNotExist
    private String name;

    @NotNull
    @PositiveOrZero(message = "Số lượng thành viên phải lớn hơn hoặc bằng 0")
    private int totalMember;

    @Pattern(regexp = "DEV|TEST|PM", message = "Loại phòng ban chỉ có thể là DEV, TEST hoặc PM")
    private String type;
}
