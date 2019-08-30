package lt.liudas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
//public class ImageEntity implements Serializable {
public class ImageEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(name = "title")
    @NotBlank
    private String title;

//    @Column(name = "data")
//    @NotBlank
    private byte[] data;

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

    public ImageEntity() {
    }

    public ImageEntity(@NotBlank String title, byte[] data) {
        this.title = title;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public byte[] getData() {
        return data;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
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
