package com.teksocial.application.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="chat_notification")
public class ChatNotificationModel {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;
}

