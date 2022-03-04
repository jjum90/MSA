package com.microservice.unentered;

import com.microservice.unentered.entity.UnenteredCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnenteredRepository extends JpaRepository <UnenteredCheckHistory, Integer> {
}
