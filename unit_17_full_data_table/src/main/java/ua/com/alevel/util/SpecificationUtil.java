package ua.com.alevel.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class SpecificationUtil {

    private SpecificationUtil() { }

    public static List<Predicate> generateSpecificationPredicates(
            DataTableRequest request,
            Class<? extends BaseEntity> entityClass,
            Root<? extends BaseEntity> root,
            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (MapUtils.isEmpty(request.getRequestParamMap())) {
            return predicates;
        }

        String containsLikePattern;
        Field[] allFields = FieldUtils.getAllFields(entityClass);
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                String[] params = request.getRequestParamMap().get(field.getName());
                if (params != null && params.length != 0) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        List<Predicate> orPredicates = new ArrayList<>();
                        for (String param : params) {
                            containsLikePattern = StringPatternUtil.getContainsLikePattern(param);
                            orPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName())), containsLikePattern));
                        }
                        if (CollectionUtils.isNotEmpty(orPredicates)) {
                            predicates.add(criteriaBuilder.or(orPredicates.toArray(new Predicate[0])));
                        }
                    }
                    if (field.getType().isAssignableFrom(Integer.class)) {
                        if (params.length == 1) {
                            Integer intType = Integer.parseInt(params[0]);
                            predicates.add(criteriaBuilder.greaterThan(root.get(field.getName()), intType));
                        } else {
                            Integer intTypeStart = Integer.parseInt(params[0]);
                            Integer intTypeEnd = Integer.parseInt(params[1]);
                            predicates.add(criteriaBuilder.between(root.get(field.getName()), intTypeStart, intTypeEnd));
                        }
                    }
                    if (field.getType().isAssignableFrom(Date.class)) {
                        System.out.println("field = " + field.getName());
                        long startDate = Long.parseLong(params[0]);
                        System.out.println("startDate = " + new Date(startDate));
                        long endDate = Long.parseLong(params[1]);
                        predicates.add(criteriaBuilder.between(root.get(field.getName()), new Date(startDate), new Date(endDate)));
                    }
                }
            }
        }

        return predicates;
    }
}
