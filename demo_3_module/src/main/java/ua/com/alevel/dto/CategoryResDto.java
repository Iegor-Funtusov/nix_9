package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.type.CategoryType;

@Getter
@Setter
public class CategoryResDto {

    private long id;
    private CategoryType category;
    private boolean income;
}
