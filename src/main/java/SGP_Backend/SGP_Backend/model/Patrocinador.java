package SGP_Backend.SGP_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@Entity
@Table(name= "patrocinadores")
@SuperBuilder
public class Patrocinador extends Usuario {
    private String posicao;
}
