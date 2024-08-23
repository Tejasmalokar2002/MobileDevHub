package com.example.MobileDevHub.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileDTO{

    private Long id;
    private String fileName;
    private String fileType;
    private String data; // Base64 encoded file data
    private Long userId; // Or associate with other entities
}
