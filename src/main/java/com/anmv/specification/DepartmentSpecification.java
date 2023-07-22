package com.anmv.specification;

import com.anmv.entity.Department;
import com.anmv.form.DepartmentFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
//import java.sql.Date;
import java.util.Date;

public class DepartmentSpecification {
    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
        if(form != null && form.getCreateDate() != null ){
            CustomDepartmentSpecification createDateEqual = new CustomDepartmentSpecification("createdDate", form.getCreateDate());
            return Specification.where(createDateEqual);
        }

        return null;
    }
}

@RequiredArgsConstructor
class CustomDepartmentSpecification implements Specification<Department> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(
            Root<Department> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("createdDate")) {
            return criteriaBuilder.equal(
                    root.get("createdDate").as(java.sql.Date.class),
                    (Date) value);
        }

        if (field.equalsIgnoreCase("type")) {
            return criteriaBuilder.equal(
                    root.get("type"), value);
        }

        return null;
    }
}
