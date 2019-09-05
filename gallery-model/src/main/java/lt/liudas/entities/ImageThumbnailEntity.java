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
@Table(name = "images_thumbnails")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class ImageThumbnailEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String title;

        @Column(columnDefinition="BLOB")
        private byte[] data;

        private String mime;

        private Long size;

        @Column(nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @CreatedDate
        private Date createdAt;

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @LastModifiedDate
        private Date updatedAt;

    public ImageThumbnailEntity() {
    }

    public ImageThumbnailEntity(@NotBlank String title, byte[] data, String mime, Long size) {
        this.title = title;
        this.data = data;
        this.mime = mime;
        this.size = size;
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
}
