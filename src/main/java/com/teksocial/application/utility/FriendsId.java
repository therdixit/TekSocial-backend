package com.teksocial.application.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendsId implements Serializable {
    private Long friendRequestSentById;
    private Long friendRequestToId;

}
