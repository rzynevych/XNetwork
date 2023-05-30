package com.rz.xnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebSocketOutputMessage {
    private String type;
    private Long converserID;
}
