package personal.Tu.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private int categoryId;
    private String categoryName;

    private String images;
    private MultipartFile imageFile; // Lưu hình
    long millis = System.currentTimeMillis();
    private Date createdAt = new java.sql.Date(millis);;
    private Boolean isEdit = false;
}
