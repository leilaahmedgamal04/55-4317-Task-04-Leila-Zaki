package com.scalable.enrollment.service;

import com.scalable.enrollment.feign.CourseClient;
import com.scalable.enrollment.feign.SectionDto;
import com.scalable.enrollment.messaging.EnrollmentPublisher;
import com.scalable.enrollment.domain.Enrollment;
import com.scalable.enrollment.domain.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseClient          courseClient;
    private final EnrollmentPublisher   enrollmentPublisher;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             CourseClient courseClient,
                             EnrollmentPublisher enrollmentPublisher) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseClient         = courseClient;
        this.enrollmentPublisher  = enrollmentPublisher;
    }

    public Enrollment create(Long studentId, Long sectionId) {

        // 1. Persist new enrollment using the real constructor
        Enrollment enrollment = new Enrollment(studentId, sectionId);
        enrollment = enrollmentRepository.save(enrollment);

        // 2. Reserve seat via Feign — throws 409 if no seats, let it propagate
        SectionDto section = courseClient.reserve(sectionId);

        // 3. Confirm using the built-in confirm() method
        enrollment.confirm(section.tuition());
        enrollment = enrollmentRepository.save(enrollment);

        // 4. Publish event last, after confirmed save
        enrollmentPublisher.publishConfirmed(enrollment);

        return enrollment;
    }
}