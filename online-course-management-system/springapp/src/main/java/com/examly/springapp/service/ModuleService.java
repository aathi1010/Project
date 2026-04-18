package com.examly.springapp.service;

import com.examly.springapp.model.Module;
import java.util.List;

public interface ModuleService {
    Module saveModule(Module module);
    List<Module> getAllModules();
    Module getModuleById(Long id);
    Module updateModule(Long id, Module module);
    void deleteModule(Long id);
}
