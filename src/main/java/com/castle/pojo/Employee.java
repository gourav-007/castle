package com.castle.pojo;

import lombok.Builder;
import lombok.Data;

@Builder(setterPrefix = "set")
@Data
public class Employee {
    private int id;
    private String fname;
    private String lname;
}
