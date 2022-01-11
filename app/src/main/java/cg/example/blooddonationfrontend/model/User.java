package cg.example.blooddonationfrontend.model;

public class User {
    private String name, email, password, cnp, age, sex, bloodGroup;

    public User(String name, String email, String password, String cnp, String age, String sex, String bloodGroup) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnp = cnp;
        this.age = age;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp() {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }









}
