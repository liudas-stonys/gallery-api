package lt.liudas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")

//@EqualsAndHashCode(exclude = {"categories", "tags"})
//@ToString(exclude = {"categories", "tags"})
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private Long idImageFullSize;

    private String title;

    private String mime;

    @Column(columnDefinition = "BLOB")
    private byte[] data;

    private Long size;

    private String description;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "images_categories", joinColumns = {@JoinColumn(name = "image_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
//    @JsonBackReference
    private Set<CategoryEntity> categories = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "images_tags", joinColumns = {@JoinColumn(name = "image_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
//    @JsonBackReference
    private Set<TagEntity> tags = new HashSet<>();

    public ImageEntity() {
    }

    public ImageEntity(Long idImageFullSize, String title, String mime, byte[] data, Long size, String description, Set<CategoryEntity> categories, Set<TagEntity> tags) {
        this.idImageFullSize = idImageFullSize;
        this.title = title.toLowerCase();
        this.mime = mime;
        this.data = data;
        this.size = size;
        this.description = description.toLowerCase();
        this.categories = categories;
        this.tags = tags;
    }

    public void addCategory(CategoryEntity cat) {
        this.categories.add(cat);
        cat.getImages().add(this);
    }

    public void removeCategory(CategoryEntity cat) {
        this.categories.remove(cat);
        cat.getImages().remove(this);
    }

    public void addTag(TagEntity tag) {
        this.tags.add(tag);
        tag.getImages().add(this);
    }

    public void removeTag(TagEntity tag) {
        this.tags.remove(tag);
        tag.getImages().remove(this);
    }
}
