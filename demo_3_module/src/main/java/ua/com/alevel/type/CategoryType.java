package ua.com.alevel.type;

import lombok.Getter;

@Getter
public enum CategoryType {

    INCOME_TRANSFER("transfer to card"),
    INCOME_SALARY("salary"),

    OUTCOME_PAYMENT("payment"),
    OUTCOME_TRANSFER("transfer from card");

    private final String type;

    CategoryType(String type) {
        this.type = type;
    }
}
