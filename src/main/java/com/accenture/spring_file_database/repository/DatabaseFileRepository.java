package com.accenture.spring_file_database.repository;

import com.accenture.spring_file_database.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {}
