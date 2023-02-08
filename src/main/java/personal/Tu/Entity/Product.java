package personal.Tu.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductId")
    private int productId;

    @Column(name="ProductName", columnDefinition = "nvarchar(255)")
    private String productName;

    @Column(name="Description", columnDefinition = "nvarchar(Max)")
    private String description;

    @Column(name="Sold")
    private int sold;

    @Column(name="isActive")
    private boolean isActive;

    @Column(name="isSelling")
    private boolean isSelling;

    @Column(name="Rating")
    private float rating;

    @Column(name="Price")
    private int price;

    @Column(name="isDeleted")
    private boolean isDeleted;

    @Column(name="createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
}
