package com.compass.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Regist {
    @CsvBindByPosition(position = 0)
    private String contactID;
    @CsvBindByPosition(position = 1)
    private String firstName;
    @CsvBindByPosition(position = 2)
    private String lastName;
    @CsvBindByPosition(position = 3)
    private String email;
    @CsvBindByPosition(position = 4)
    private String zipCode;
    @CsvBindByPosition(position = 5)
    private String address;

}
