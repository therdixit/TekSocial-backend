package com.social.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JwtRequest {
    String username;
    String password;

}
