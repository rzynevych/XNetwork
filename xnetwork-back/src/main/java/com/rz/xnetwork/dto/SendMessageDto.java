package com.rz.xnetwork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageDto {
    
    Long converserId;
    String text;
}
