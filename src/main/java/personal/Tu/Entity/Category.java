package personal.Tu.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CategoryId")
    private int categoryId;

    @Column(name="CategoryName", columnDefinition = "nvarchar(255)")
    private String categoryName;

    @Column(name="Images", columnDefinition = "nvarchar(MAX)")
    private String images;

    @Column(name="Status")
    private boolean status;

    @Column(name="createdAt")
    private Date createdAt;

}
