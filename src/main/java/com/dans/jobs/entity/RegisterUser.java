package com.dans.jobs.entity;

import lombok.Data;

@Data
public class RegisterUser {
    private String nama;
    private String password;
    private String nip;
    private String username;
    private String id_jabatan;
    private String id_pangkat;
}
