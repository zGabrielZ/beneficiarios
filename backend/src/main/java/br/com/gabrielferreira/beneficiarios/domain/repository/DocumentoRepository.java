package br.com.gabrielferreira.beneficiarios.domain.repository;

import br.com.gabrielferreira.beneficiarios.domain.model.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("SELECT d FROM Documento d " +
            "JOIN FETCH d.beneficiario b " +
            "WHERE b.id = :idBeneficiario " +
            "ORDER BY d.dataInclusao DESC")
    Page<Documento> buscarDocumentosPorBeneficiario(@Param("idBeneficiario") Long idBeneficiario, Pageable pageable);
}
