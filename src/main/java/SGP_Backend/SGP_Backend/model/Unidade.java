package SGP_Backend.SGP_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "unidades")
@EqualsAndHashCode
@Builder
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direcao;
    private String nome;
    private String setor;
    private String responsabilidade;
    private String email;
    private String ramal;
    @Column(name = "nome_servidor")
    private String nomeServidor;
}
