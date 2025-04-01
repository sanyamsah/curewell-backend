package com.sanyam.curewell.curewell_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long doctorId;

    @Column(name = "doctorName", nullable = false)
    @NotEmpty(message = "Name is required")
    private String doctorName;

    @Column(name = "image")
    private String image;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(
                    name = "doctor_id",
                    referencedColumnName = "doctorId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "specialization_code",
                    referencedColumnName = "specializationCode"
            )
    )
    private Set<Specialization> specializations;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public @NotEmpty(message = "Name is required") String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(@NotEmpty(message = "Name is required") String doctorName) {
        this.doctorName = doctorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }
}
