package com.scalable.course.service;

import com.scalable.course.domain.Section;
import com.scalable.course.domain.SectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseService {

    private final SectionRepository sections;

    public CourseService(SectionRepository sections) {
        this.sections = sections;
    }

    public Section findSection(Long id) {
        return sections.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Section " + id + " not found"));
    }

    @Transactional
    public Section reserveSeat(Long sectionId) {
        Section s = findSection(sectionId);
        try {
            s.decrementAvailableSeats();
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No seats available in section " + sectionId);
        }
        return sections.save(s);
    }
}
