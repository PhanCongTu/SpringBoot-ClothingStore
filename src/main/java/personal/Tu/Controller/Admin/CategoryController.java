package personal.Tu.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.Tu.Entity.Category;
import personal.Tu.Service.Impl.ICategoryService;

import java.util.List;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @RequestMapping("")
    public String List(ModelMap model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }
}
