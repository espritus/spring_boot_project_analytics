package com.example.spring_boot_project_analytics.Faq.repository;

import com.example.spring_boot_project_analytics.Faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
