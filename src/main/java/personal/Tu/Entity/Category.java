package personal.Tu.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @Column(name="createdAt")
    private Date createdAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
