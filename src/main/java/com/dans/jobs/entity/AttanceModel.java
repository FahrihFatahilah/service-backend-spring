package com.dans.jobs.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "tbl_absensi")
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id_absen;

    @Column(nullable = false)
    private String keterangan;

    @Column(nullable = true)
    private String tanggal;

    @Column(nullable = true)
    private String waktu;

    @Column(nullable = false)
    private String statusabsensi;

    @Column(nullable = false)
    private String nip;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


}
