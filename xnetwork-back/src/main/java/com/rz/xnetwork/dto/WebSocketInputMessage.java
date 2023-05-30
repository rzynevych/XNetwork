package com.rz.xnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebSocketInputMessage {
    private String username;
    private String text;
}
