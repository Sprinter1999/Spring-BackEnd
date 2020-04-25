package com.demo.repository;

import com.demo.entity.TimelyNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;

public interface TimelyNewsRepository extends JpaRepository<TimelyNews, Integer> {
}
