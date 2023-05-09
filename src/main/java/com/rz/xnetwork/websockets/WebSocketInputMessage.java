package com.rz.xnetwork.websockets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class WebSocketInputMessage {
    private String username;
    private String text;
}
