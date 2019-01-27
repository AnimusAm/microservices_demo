package hr.smilebacksmile.domain;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class TestData {

    @Id
    private BigInteger id;

    private String code;

    private String name;

    protected TestData() { }

    public TestData(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}



