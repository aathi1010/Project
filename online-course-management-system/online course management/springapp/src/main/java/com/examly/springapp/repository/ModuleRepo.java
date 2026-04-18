package com.examly.springapp.repository;
import com.examly.springapp.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {}

