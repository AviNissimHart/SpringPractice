package com.qa.practicespring.dto;

import java.util.List;

import com.qa.practicespring.persistence.domain.Guitarist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BandDTO {

    private Long id;
    private String name;
    private List<Guitarist> guitarists;

}
