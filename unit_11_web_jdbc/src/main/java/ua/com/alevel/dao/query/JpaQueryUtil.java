package ua.com.alevel.dao.query;

public final class JpaQueryUtil {

    private JpaQueryUtil() { }

    public static final String CREATE_DEPARTMENT_QUERY = "INSERT INTO departments VALUES(default,?,?,?,?)";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE departments SET name = ?, department_type = ?, updated = ? WHERE id = ";
    public static final String DELETE_DEPARTMENT_BY_ID_QUERY = "DELETE FROM departments WHERE id = ";
    public static final String EXIST_DEPARTMENT_BY_ID_QUERY = "SELECT COUNT(*) FROM departments WHERE id = ";
    public static final String FIND_ALL_DEPARTMENTS_QUERY = "SELECT * FROM departments";
    public static final String FIND_DEPARTMENT_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ";

    public static final String CREATE_EMPLOYEE_QUERY = "INSERT INTO employees VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_EMPLOYEE_BY_ID_QUERY = "UPDATE employees SET age = ?, first_name = ?, last_name = ?, updated = ? WHERE id = ";
    public static final String DELETE_EMPLOYEES_BY_ID_QUERY = "DELETE FROM employees WHERE id = ";
    public static final String DELETE_EMPLOYEES_BY_DEPARTMENT_ID_QUERY = "DELETE FROM employees WHERE department_id = ";
    public static final String EXIST_EMPLOYEE_BY_ID_QUERY = "SELECT COUNT(*) FROM employees WHERE id = ";
    public static final String FIND_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employees AS emp JOIN departments AS dep ON emp.department_id = dep.id WHERE emp.id = ";
    public static final String FIND_ALL_EMPLOYEES_QUERY = "SELECT * FROM employees AS emp JOIN departments AS dep ON emp.department_id = dep.id";
    public static final String FIND_ALL_EMPLOYEES_BY_DEPARTMENT_ID_QUERY = "SELECT * FROM employees AS emp JOIN departments AS dep ON emp.department_id = dep.id WHERE emp.department_id = ";
}
