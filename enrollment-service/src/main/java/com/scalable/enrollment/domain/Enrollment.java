package com.scalable.enrollment.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    public enum Status { PENDING, CONFIRMED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "section_id", nullable = false)
    private Long sectionId;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.PENDING;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    protected Enrollment() {}

    public Enrollment(Long studentId, Long sectionId) {
        this.studentId = studentId;
        this.sectionId = sectionId;
    }

    public Long getId()           { return id; }
    public Long getStudentId()    { return studentId; }
    public Long getSectionId()    { return sectionId; }
    public BigDecimal getAmount() { return amount; }
    public Status getStatus()     { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void confirm(BigDecimal amount) {
        this.amount = amount;
        this.status = Status.CONFIRMED;
    }
}
