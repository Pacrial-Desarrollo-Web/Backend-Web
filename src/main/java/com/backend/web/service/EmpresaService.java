package com.backend.web.service;

import com.backend.web.model.Empresa;
import com.backend.web.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public List<Empresa> obtenerTodas() {
        return empresaRepository.findAll();
    }
    
    public Optional<Empresa> obtenerPorId(Long id) {
        return empresaRepository.findById(id);
    }
    
    public Empresa crear(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
    
    public Empresa actualizar(Long id, Empresa empresa) {
        if (empresaRepository.existsById(id)) {
            empresa.setId(id);
            return empresaRepository.save(empresa);
        }
        return null;
    }
    
    public boolean eliminar(Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
