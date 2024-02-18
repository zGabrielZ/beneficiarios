package br.com.gabrielferreira.beneficiarios.domain.repository;

import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

}
