package com.rz.xnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
    
    private Boolean status;
    private String message;
}
