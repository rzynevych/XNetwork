package com.rz.xnetwork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageDto {
    
    private Long converserId;
    private String text;
}
