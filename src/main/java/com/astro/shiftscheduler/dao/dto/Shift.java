package com.astro.shiftscheduler.dao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class Shift {

    private Long id;
    private String type;
    private int time;
}
