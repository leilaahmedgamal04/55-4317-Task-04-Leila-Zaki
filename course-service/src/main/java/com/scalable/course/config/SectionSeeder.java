package com.scalable.course.config;

import com.scalable.course.domain.Section;
import com.scalable.course.domain.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class SectionSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SectionSeeder.class);

    private final SectionRepository sections;

    public SectionSeeder(SectionRepository sections) {
        this.sections = sections;
    }

    @Override
    public void run(String... args) {
        if (sections.count() > 0) return;
        sections.save(new Section("Intro to Scalable Systems", 200, new BigDecimal("50.00")));
        sections.save(new Section("Advanced Distributed Systems", 200, new BigDecimal("200.00")));
        log.info("SectionSeeder inserted {} sections", sections.count());
    }
}
