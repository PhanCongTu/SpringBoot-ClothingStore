package personal.Tu.Controller.Admin;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import personal.Tu.Entity.Product;
import personal.Tu.Model.ProductModel;
import personal.Tu.Service.IProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    Cloudinary cloudinary;

    @RequestMapping("")
    public ModelAndView List(ModelMap modelMap){
        return new ModelAndView("forward:/admin/products/search-pagenated", modelMap);
    }

    @GetMapping("add")
    public String Add(ModelMap modelMap){
        ProductModel productModel = new ProductModel();
        productModel.setIsEdit(false);
        modelMap.addAttribute("product", productModel);
        return "admin/products/addOrEdit";
    }

    @RequestMapping("search-pagenated")
    public String search(ModelMap modelMap, @RequestParam(name="name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page){
        int countAll = (int) productService.count();
        int curentPage = page.orElse(1);
        int pageSize = 6;

        Pageable pageable = PageRequest.of(curentPage-1, pageSize, Sort.by("ProductId"));
        Page<Product> resultPage = null;
        if(StringUtils.hasText(name)){
            resultPage = productService.findByProductNameContaining(name, pageable);
            countAll = productService.countByProductNameContaining(name);
            modelMap.addAttribute("name", name);
        }else{
            resultPage = productService.findAll(pageable);
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
        modelMap.addAttribute("products", resultPage);
        return "admin/products/listPagenated";
    }
}
