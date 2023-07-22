package com.anmv.specification;

import com.anmv.entity.Account;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification {
    public static Specification<Account> buildWhere(String search, String id) {
        // Khởi tạo mệnh đề Where null
        Specification<Account> where = null;

        // neu search null hoac rong:
        if (StringUtils.isEmpty(search) && StringUtils.isEmpty(id)) {
            return null;
        }

        // Nếu có Search đầu vào
        if (!StringUtils.isEmpty(search)){
            // loai bo space thừa ở 2 đầu của chuỗi.
            search = search.trim();
            CustomSpecification username_like = new CustomSpecification("username_like", search);
            where = Specification.where(username_like);
        }

        // Nếu có Id đầu vào
        if (!StringUtils.isEmpty(id)){
            CustomSpecification department_name = new CustomSpecification("department_name", id);
            // Kiểm tra trạng thái của where
            if (where == null){
                where = Specification.where(department_name);
            } else {
                where = where.and(department_name);
            }
        }


        return where;
    }
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Account> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(
            Root<Account> root, //đại diện cho đối tượng Account
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("username_like")) {
            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("department_name")) {
            return criteriaBuilder.like(root.get("department").get("name"), "%" + value.toString() + "%");
        }

        return null;
    }
}