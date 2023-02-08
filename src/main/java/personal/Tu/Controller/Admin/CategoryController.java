package personal.Tu.Controller.Admin;

import com.cloudinary.utils.ObjectUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import personal.Tu.Entity.Category;
import personal.Tu.Model.CategoryModel;
import personal.Tu.Service.ICategoryService;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    Cloudinary cloudinary;

    @RequestMapping("")
    public ModelAndView List(ModelMap modelMap) {
//        List<Category> list = categoryService.findAll();
//        model.addAttribute("categories", list);
        return new ModelAndView("forward:/admin/categories/search-pagenated", modelMap);
    }

    @GetMapping("add")
    public String Add(ModelMap model){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setIsEdit(false);
        model.addAttribute("category", categoryModel);
        return "admin/categories/addOrEdit";
    }
    @PostMapping("save-or-update")
    public ModelAndView saveOrUpdate(ModelMap modelMap, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("admin/categories/addOrEdit");
        }
        Category category = new Category();
        // Coppy tu model sang entity
        BeanUtils.copyProperties(categoryModel, category);


        if (!categoryModel.getImageFile().isEmpty()){
            try {
                Map r = this.cloudinary.uploader().upload(categoryModel.getImageFile().getBytes(),
                        ObjectUtils.asMap("resource_type","auto"));
                String img = (String) r.get("secure_url");
                category.setImages(img);
            } catch (IOException e){
                e.printStackTrace();
            }
        }else {
            Optional<Category> optional = categoryService.findById(categoryModel.getCategoryId());
            if (optional.isPresent()){
                Category category1 = optional.get();
                category.setImages(category1.getImages());
            }
        }

        categoryService.save(category);
        return new ModelAndView("forward:/admin/categories/search-pagenated", modelMap);
    }
    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap modelMap, @PathVariable("categoryId") int categoryId){
        categoryService.deleteById(categoryId);
        return new ModelAndView("forward:/admin/categories/search-pagenated", modelMap);
    }

    @GetMapping("edit/{categoryId}")
    public ModelAndView edit(ModelMap modelMap, @PathVariable("categoryId") int categoryId){
        Optional<Category> optional = categoryService.findById(categoryId);
        CategoryModel categoryModel = new CategoryModel();
        if (optional.isPresent()){
            Category category = optional.get();
            BeanUtils.copyProperties(category, categoryModel);
            categoryModel.setIsEdit(true);
            modelMap.addAttribute("category", categoryModel);
            return new ModelAndView("admin/categories/addOrEdit", modelMap);
        }
        return new ModelAndView("forward:/admin/categories/search-pagenated", modelMap);
    }

    @RequestMapping("search-pagenated")
    public String search(ModelMap modelMap, @RequestParam(name="name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page){
        int countAll = (int) categoryService.count();
        int curentPage = page.orElse(1);
        int pageSize = 6;

        Pageable pageable = PageRequest.of(curentPage-1, pageSize, Sort.by("categoryId"));
        Page<Category> resultPage = null;
        if(StringUtils.hasText(name)){
            resultPage = categoryService.findByCategoryNameContaining(name, pageable);
            countAll = categoryService.countByCategoryNameContaining(name);
            modelMap.addAttribute("name", name);
        }else{
            resultPage = categoryService.findAll(pageable);
        }
        int totalPages = resultPage.getTotalPages();

        if(totalPages>0){
            int start = Math.max(1, curentPage - 2);
            int end = Math.max(curentPage + 2, totalPages);
            if (totalPages>countAll){
                if (end == totalPages){
                    start = end - countAll;
                }else if (start == 1){
                    end = start + countAll;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("countAll", countAll);
        modelMap.addAttribute("categories", resultPage);
        return "admin/categories/listPagenated";
    }
}





















