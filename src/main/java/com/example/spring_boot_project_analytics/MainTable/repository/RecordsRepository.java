package com.example.spring_boot_project_analytics.MainTable.repository;

import com.example.spring_boot_project_analytics.MainTable.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Record, Long> {
}
