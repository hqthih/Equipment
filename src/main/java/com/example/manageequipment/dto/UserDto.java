package com.example.manageequipment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private List<Long> equipmentIds;
    private List<Long> transferredEquipIds;
    private String role;
    private String deviceToken;
}
