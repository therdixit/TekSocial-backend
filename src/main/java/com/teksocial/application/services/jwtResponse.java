package com.teksocial.application.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class jwtResponse {
	private Long id;
    private String status;
    private String jwtToken;
}
