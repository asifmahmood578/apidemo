package com.apidemo.repository;

import com.apidemo.entity.Regstration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegstrationRepository extends JpaRepository<Regstration, Long> {
}