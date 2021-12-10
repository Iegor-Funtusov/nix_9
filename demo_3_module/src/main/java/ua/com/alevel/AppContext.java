package ua.com.alevel;

import ua.com.alevel.dto.AccountResDto;
import ua.com.alevel.dto.CategoryResDto;
import ua.com.alevel.dto.OperationResDto;
import ua.com.alevel.dto.UserResDto;
import ua.com.alevel.type.CategoryType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppContext {

    private static AppContext instance;

    private final List<UserResDto> users;
    private final List<AccountResDto> accounts;
    private final List<OperationResDto> operations;
    private final List<CategoryResDto> categories;

    private AppContext() {
        this.users = users();
        this.categories = categories();
        this.accounts = accounts();
        this.operations = operations();
        AccountResDto dto = generateAccountResDto(6);
        dto.setUserId(1);
        addAccount(dto);
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public List<UserResDto> getUsers() {
        return users;
    }

    public void addUser(UserResDto res) {
        users.add(res);
    }

    public UserResDto findByUserResDtoId(long id) {
        return users.stream().filter(resDto -> resDto.getId() == id).findFirst().get();
    }

    public List<AccountResDto> getAccounts() {
        return accounts;
    }

    public List<AccountResDto> getAccountsByUserId(long userId) {
        return accounts.stream().filter(dto -> dto.getUserId() == userId).collect(Collectors.toList());
    }

    public AccountResDto findAccountResDtoById(long id) {
        return accounts.stream().filter(accountResDto -> accountResDto.getId() == id).findFirst().get();
    }

    public void addAccount(AccountResDto dto) {
        accounts.add(dto);
    }

    public List<OperationResDto> getOperations() {
        return operations;
    }

    public OperationResDto findOperationById(long id) {
        return operations.stream().filter(operationResDto -> operationResDto.getId() == id).findFirst().get();
    }

    public List<CategoryResDto> findAllCategories() {
        return categories;
    }

    public List<OperationResDto> getOperationsByAccountId(long accId) {
        AccountResDto acc = findAccountResDtoById(accId);
        List<Long> operIds = acc.getOperations();
        return operIds.stream().map(this::findOperationById).collect(Collectors.toList());
    }

    public CategoryResDto findCategoryById(long id) {
        return categories.stream().filter(categoryResDto -> categoryResDto.getId() == id).findFirst().get();
    }

    private List<UserResDto> users() {
        List<UserResDto> userResDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            long id = i + 1;
            UserResDto dto = new UserResDto();
            dto.setId(id);
            dto.setEmail("email" + id + "@mail.com");
            dto.setPhone("+38096000000" + id);
            userResDtos.add(dto);
        }
        return userResDtos;
    }

    private List<AccountResDto> accounts() {
        List<AccountResDto> accountResDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            long id = i + 1;
            accountResDtos.add(generateAccountResDto(id));
        }

        return accountResDtos;
    }

    private AccountResDto generateAccountResDto(long id) {
        AccountResDto dto = new AccountResDto();
        dto.setUserId(id);
        dto.setId(id);
        dto.setBalance(new BigDecimal("100.00"));
        dto.setOperations(new ArrayList<>());
        return dto;
    }

    private List<OperationResDto> operations() {
        List<OperationResDto> list = new ArrayList<>();
        OperationResDto res1 = new OperationResDto();
        res1.setCreated(new Date());
        res1.setId(1);
        res1.setAccountId(1);
        res1.setCategoryId(2);
        res1.setAmount(new BigDecimal("10.00"));
        list.add(res1);

        OperationResDto res2 = new OperationResDto();
        res2.setCreated(new Date());
        res2.setId(2);
        res2.setAccountId(2);
        res2.setCategoryId(4);
        res2.setAmount(new BigDecimal("10.00"));
        list.add(res2);

        return list;
    }

    private List<CategoryResDto> categories() {
        List<CategoryResDto> list = new ArrayList<>();
        CategoryResDto res1 = new CategoryResDto();
        res1.setCategory(CategoryType.INCOME_SALARY);
        res1.setId(1);
        res1.setIncome(true);
        list.add(res1);

        CategoryResDto res2 = new CategoryResDto();
        res2.setCategory(CategoryType.INCOME_TRANSFER);
        res2.setId(2);
        res2.setIncome(true);
        list.add(res2);

        CategoryResDto res3 = new CategoryResDto();
        res3.setCategory(CategoryType.OUTCOME_PAYMENT);
        res3.setId(3);
        res3.setIncome(false);
        list.add(res3);

        CategoryResDto res4 = new CategoryResDto();
        res4.setCategory(CategoryType.OUTCOME_TRANSFER);
        res4.setId(4);
        res4.setIncome(false);
        list.add(res4);

        return list;
    }
}
