package com.mng.fileoperation.repository;

import com.mng.fileoperation.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData,Long> {
}
