package br.com.gabrielferreira.beneficiarios.domain.repository;

import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

    @Query("SELECT b FROM Beneficiario b " +
            "ORDER BY b.dataInclusao DESC")
    Page<Beneficiario> buscarBeneficiarios(Pageable pageable);
}
