package SGP_Backend.SGP_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@Entity
@Table(name = "membros")
@SuperBuilder
public class Membro extends Usuario {
    private String funcao;
    private String lotacao;
    private String setor;
    private String ramal;
}
