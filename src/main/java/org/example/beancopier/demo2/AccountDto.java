package org.example.beancopier.demo2;

public class AccountDto {
    private Integer id;
    private String createTime;
    private String balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}