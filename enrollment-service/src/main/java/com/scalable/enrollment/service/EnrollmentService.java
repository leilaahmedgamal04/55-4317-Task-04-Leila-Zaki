package com.scalable.enrollment.service;

import com.scalable.enrollment.domain.Enrollment;
import com.scalable.enrollment.domain.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollments;

    public EnrollmentService(EnrollmentRepository enrollments) {
        this.enrollments = enrollments;
    }

    public Enrollment create(Long studentId, Long sectionId) {
        return null;
    }
}
