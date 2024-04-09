package com.example.spring_boot_project_analytics.repository;

import com.example.spring_boot_project_analytics.entity.TableDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDetailsRepository extends JpaRepository<TableDetails, Long> {
}
