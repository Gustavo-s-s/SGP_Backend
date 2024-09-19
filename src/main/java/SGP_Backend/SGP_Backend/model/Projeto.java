package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "projetos")
@EqualsAndHashCode
public class Projeto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private String versao;
        private String objetivo;
        private LocalDateTime dataInicio;
        private LocalDateTime dataFim;
        private LocalDateTime prazo;
    @Enumerated(EnumType.STRING)
    private TipoProjeto tipo;
    @Enumerated(EnumType.STRING)
    private StatusProjeto status;
    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
    @ManyToMany
    @JoinTable(
            name = "projeto_unidades",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_id"))
    private Set<Unidade> outrasUnidades;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(id, projeto.id) && Objects.equals(nome, projeto.nome) && Objects.equals(versao, projeto.versao) && Objects.equals(objetivo, projeto.objetivo) && Objects.equals(dataInicio, projeto.dataInicio) && Objects.equals(dataFim, projeto.dataFim) && Objects.equals(prazo, projeto.prazo) && tipo == projeto.tipo && status == projeto.status && Objects.equals(equipe, projeto.equipe) && Objects.equals(outrasUnidades, projeto.outrasUnidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, versao, objetivo, dataInicio, dataFim, prazo, tipo, status, equipe, outrasUnidades);
    }
}
