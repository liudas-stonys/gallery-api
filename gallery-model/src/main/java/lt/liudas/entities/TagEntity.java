package lt.liudas.entities;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @NaturalId // TODO: Gives org.springframework.dao.DataIntegrityViolationException: could not execute statement
    @Column(columnDefinition="text") // TODO: Solves the above?
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "tags")
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<ImageEntity> images = new HashSet<>();

    public TagEntity() {
    }

    public TagEntity(String name) {
        this.name = name;
    }

    // Getters and Setters (Omitted for brevity)
}