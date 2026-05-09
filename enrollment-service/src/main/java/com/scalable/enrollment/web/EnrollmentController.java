package com.scalable.enrollment.web;

import com.scalable.enrollment.domain.Enrollment;
import com.scalable.enrollment.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public Enrollment create(@RequestBody CreateEnrollmentRequest req) {
        return enrollmentService.create(req.studentId(), req.sectionId());
    }

    public record CreateEnrollmentRequest(Long studentId, Long sectionId) {}
}
