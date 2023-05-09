package com.rz.xnetwork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageDTO {
    
    Long converserID;
    String text;
}
