package br.com.macedo.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status", nullable = false)
    private Long idStatus;

    @Column(name = "nome_status")
    private String nomeStatus;

    @Column(name = "descricao_status")
    private String descricaoStatus;

    public static Optional<PanacheEntityBase> findByNomeStatus(String nomeStatus) {
        return find("nomeStatus", nomeStatus).firstResultOptional();
    }
}
