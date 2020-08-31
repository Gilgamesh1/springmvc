package guru.springframework.controllers;

import guru.springframework.persistence.domain.Product;
import guru.springframework.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listProducts(Model model) {
        log.info("listProducts");
        model.addAttribute("products", productService.listAll());
        return "product/products";
    }

    @RequestMapping("/product/{id}")
    public String getAProduct(@PathVariable Integer id, Model model) {
        log.info("listProducts");
        model.addAttribute("product", productService.getById(id));
        return "product/product";
    }

    @RequestMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product) {
        log.info("saveOrUpdateProduct");
        Product product1 = productService.saveOrUpdate(product);
        return "redirect:/product/" + product1.getId();
    }

    @RequestMapping("/product/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        log.info("editProductForm");
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product/productform";
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProductForm(@PathVariable Integer id) {
        log.info("deleteProductForm");
        productService.delete(id);
        return "redirect:/products";
    }
}
