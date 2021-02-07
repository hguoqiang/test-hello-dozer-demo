    package org.example.beancopier.demo1;

public class UserWithDiffType {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}