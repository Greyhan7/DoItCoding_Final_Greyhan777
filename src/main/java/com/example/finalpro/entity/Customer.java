package com.example.finalpro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    //JPA는 테이블 속성명과 엔티티의 변수명을 똑같이해야 인식함.
    @Id
    private String custid;
    private String pwd;
    private String name;
    private String birth;
    private String email;
    private String phone;
    private String gender;
    private String role;
    private String favor;
    private int cateid;
}
