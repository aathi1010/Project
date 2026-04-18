package com.examly.springapp.service;

import com.examly.springapp.model.Module;
import com.examly.springapp.repository.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    
    @Autowired
    private ModuleRepo moduleRepo;
    
    @Override
    public Module saveModule(Module module) {
        return moduleRepo.save(module);
    }
    
    @Override
    public List<Module> getAllModules() {
        return moduleRepo.findAll();
    }
    
    @Override
    public Module getModuleById(Long id) {
        return moduleRepo.findById(id).orElse(null);
    }
    
    @Override
    public Module updateModule(Long id, Module module) {
        module.setModuleId(id);
        return moduleRepo.save(module);
    }
    
    @Override
    public void deleteModule(Long id) {
        moduleRepo.deleteById(id);
    }
}
