package personal.Tu.Controller.Admin;

import com.cloudinary.utils.ObjectUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import personal.Tu.Entity.Category;
import personal.Tu.Model.CategoryModel;
import personal.Tu.Service.Impl.ICategoryService;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    Cloudinary cloudinary;

    @RequestMapping("")
    public String List(ModelMap model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("add")
    public String Add(ModelMap model){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setIsEdit(false);
        model.addAttribute("category", categoryModel);
        return "admin/categories/add";
    }
    @PostMapping("save-or-update")
    public ModelAndView saveOrUpdate(ModelMap modelMap, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            return new ModelAndView("admin/categories/add");
        }
        Category category = new Category();
        // Coppy tu model sang entity
        BeanUtils.copyProperties(categoryModel, category);

        // Xử lý hình ảnh lưu file vào cloudinary
        if (!categoryModel.getImageFile().isEmpty()){
            try {
                Map r = this.cloudinary.uploader().upload(categoryModel.getImageFile().getBytes(),
                        ObjectUtils.asMap("resource_type","auto"));
                String img = (String) r.get("secure_url");
                category.setImages(img);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        categoryService.save(category);
        return new ModelAndView("forward:/admin/categories", modelMap);
    }
    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap modelMap, @PathVariable("categoryId") int categoryId){
        categoryService.deleteById(categoryId);
        return new ModelAndView("forward:/admin/categories", modelMap);
    }
}
