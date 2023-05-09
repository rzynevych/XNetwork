package com.rz.xnetwork.websockets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class WebSocketOutputMessage {
    private String type;
    private Long converserID;
}
