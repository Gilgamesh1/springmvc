package guru.springframework.controllers;

import guru.springframework.persistence.domain.Product;
import guru.springframework.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTests {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product());
        productsList.add(new Product());
        when(productService.listAll()).thenReturn((List) productsList);
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void testGetAProduct() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/product"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNewProductForm() throws Exception {
        verifyNoInteractions(productService);
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdateProduct() throws Exception {
        Integer id = 1;
        String description = "Cajas";
        BigDecimal precio = new BigDecimal("12.00");
        String url = "http://example.com";

        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setPrice(precio);
        product.setImageUrl(url);
        Product product1 = mock(Product.class);
        when(productService.saveOrUpdate(org.mockito.ArgumentMatchers.any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/product")
                .param("id", "1")
                .param("description", description)
                .param("price", "12.00")
                .param("imageUrl", "http://example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
                .andExpect(model().attribute("product", hasProperty("id", is(id))))
                .andExpect(model().attribute("product", hasProperty("description", is(description))))
                .andExpect(model().attribute("product", hasProperty("price", is(precio))))
                .andExpect(model().attribute("product", hasProperty("imageUrl", is(url))));
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(productArgumentCaptor.capture());
        assertEquals(id, productArgumentCaptor.getValue().getId());
        assertEquals(description, productArgumentCaptor.getValue().getDescription());
        assertEquals(precio, productArgumentCaptor.getValue().getPrice());
        assertEquals(url, productArgumentCaptor.getValue().getImageUrl());
    }

    @Test
    public void testEditProductForm() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testDeleteProductForm() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"));
        verify(productService, times(1)).delete(id);
    }
}
