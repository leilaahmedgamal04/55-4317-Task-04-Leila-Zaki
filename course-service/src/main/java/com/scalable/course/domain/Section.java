package com.scalable.course.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal tuition;

    protected Section() {}

    public Section(String title, int availableSeats, BigDecimal tuition) {
        this.title          = title;
        this.availableSeats = availableSeats;
        this.tuition        = tuition;
    }

    public Long getId()             { return id; }
    public String getTitle()        { return title; }
    public int getAvailableSeats()  { return availableSeats; }
    public BigDecimal getTuition()  { return tuition; }

    public void decrementAvailableSeats() {
        if (availableSeats <= 0) {
            throw new IllegalStateException("No seats available in section " + id);
        }
        this.availableSeats -= 1;
    }
}
