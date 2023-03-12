package com.teksocial.application.models;

import com.teksocial.application.utility.FriendsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "friends_mapper_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(FriendsId.class)
public class FriendsModel {

    @Id
    @Column(name = "friend_request_sent_id")
    private Long friendRequestSentById;

    @Id
    @Column(name="friend_request_to_id")
    private Long friendRequestToId;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="deleted_at")
    private String deletedAt;

    @Column(name="updated_at")
    private String updatedAt;
}
