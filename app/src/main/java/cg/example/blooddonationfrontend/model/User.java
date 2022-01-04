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
}
