package com.socialserver.socialserver.modal;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private Long senderId;
    private String senderName;
    private Long recipientId;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
