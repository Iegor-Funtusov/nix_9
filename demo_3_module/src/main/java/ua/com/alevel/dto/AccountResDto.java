package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountResDto {

    private long id;
    private BigDecimal balance;
    private long userId;
    private List<Long> operations;
}
