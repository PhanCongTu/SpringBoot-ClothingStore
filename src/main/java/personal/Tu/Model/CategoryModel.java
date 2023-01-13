package personal.Tu.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private long categoryId;
    private String categoryName;
    private String images;
    private MultipartFile imageFile; // Lưu hình
    private boolean status = true ;
    private Boolean isEdit = false;
}
