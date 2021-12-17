package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
//        AgeByBirthDayGenerationListener.class
})
public class Personal extends User {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
