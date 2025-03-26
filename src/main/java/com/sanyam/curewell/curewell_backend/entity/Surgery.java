package com.sanyam.curewell.curewell_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        name = "surgeries",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"surgery_id"})
        }
)
public class Surgery {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long surgeryId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "surgery_date", nullable = false)
    private Date surgeryDate;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @NotEmpty(message = "Category (code) is required")
    @Column(name = "surgery_category", nullable = false)
    private String surgeryCategory;

    public Long getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(Long surgeryId) {
        this.surgeryId = surgeryId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Date getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public @NotEmpty(message = "Category (code) is required") String getSurgeryCategory() {
        return surgeryCategory;
    }

    public void setSurgeryCategory(@NotEmpty(message = "Category (code) is required") String surgeryCategory) {
        this.surgeryCategory = surgeryCategory;
    }
}
