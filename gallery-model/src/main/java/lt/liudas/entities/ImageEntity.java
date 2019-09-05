package lt.liudas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
//public class ImageEntity implements Serializable {
public class ImageEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "title")
    @NotBlank
    private String title;

//    @Column(name = "data")
//    @NotBlank
    @Column(columnDefinition="LONGBLOB")
    private byte[] data;

    private String mime;

    private Long size;

//    @Column(name = "createdAt", nullable = false, updatable = false)
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

//    @Column(name = "updatedAt", nullable = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "images_tags", joinColumns = { @JoinColumn(name = "image_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<TagEntity> tags = new HashSet<>();

    public ImageEntity() {
    }

    public ImageEntity(@NotBlank String title, byte[] data, String mime, Long size) {
        this.title = title;
        this.data = data;
        this.mime = mime;
        this.size = size;
    }

    public ImageEntity(@NotBlank String title, byte[] data, String mime, Long size, Set<TagEntity> tags) {
        this.title = title;
        this.data = data;
        this.mime = mime;
        this.size = size;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }
}

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Basic(optional = false)
//    @Column(insertable = false, nullable = false, updatable = false)
//    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Basic(optional = false)
//    @Column(insertable = false, nullable = true)
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;
