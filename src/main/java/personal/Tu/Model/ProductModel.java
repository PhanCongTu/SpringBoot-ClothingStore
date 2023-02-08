package personal.Tu.Model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.Tu.Entity.Category;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private int productId;
    private String productName;
    private String description;
    private int sold = 0;
    private boolean isActive = true;
    private boolean isSelling = true;
    private float rating = 0;
    private int price;
    private boolean isDeleted = false;
    private Date createdAt = new java.sql.Date(System.currentTimeMillis());
    private int categoryId;
    private Boolean isEdit = false;
}
