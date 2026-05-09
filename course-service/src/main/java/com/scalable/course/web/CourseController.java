package com.scalable.course.web;

import com.scalable.course.domain.Section;
import com.scalable.course.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/sections/{sectionId}")
    public SectionDto get(@PathVariable("sectionId") Long sectionId) {
        Section s = courseService.findSection(sectionId);
        return new SectionDto(s.getId(), s.getTitle(),
                s.getAvailableSeats(), s.getTuition());
    }

    @PostMapping("/sections/{sectionId}/reserve")
    public SectionDto reserve(@PathVariable("sectionId") Long sectionId) {
        Section s = courseService.reserveSeat(sectionId);
        return new SectionDto(s.getId(), s.getTitle(),
                s.getAvailableSeats(), s.getTuition());
    }

    public record SectionDto(Long id, String title,
                             int availableSeats, BigDecimal tuition) {}
}
