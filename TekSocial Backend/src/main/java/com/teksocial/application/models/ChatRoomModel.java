package com.teksocial.application.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "chat_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String chatId;

    @Column
    private Long senderId;

    @Column
    private Long recipientId;

}
