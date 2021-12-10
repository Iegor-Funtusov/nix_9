package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OperationResDto {

    private long id;
    private Date created;
    private long categoryId;
    private long accountId;
    private BigDecimal amount;
}
