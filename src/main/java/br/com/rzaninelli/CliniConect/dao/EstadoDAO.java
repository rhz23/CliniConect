package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoDAO extends JpaRepository<Estado, Integer> {
}
