package com.qa.practicespring.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// converting our POJO (Guitarist entity) to JSON (to view in the front-end)

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(lazy = true)
@Setter
@ToString
@EqualsAndHashCode
public class GuitaristDTO {

    // D - Data
    // T - Transfer
    // O - Object

    private Long id;
    private String name;
    private Integer noOfStrings;
    private String type;

}