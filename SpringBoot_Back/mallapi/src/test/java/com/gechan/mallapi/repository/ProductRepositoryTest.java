package com.gechan.mallapi.repository;

import com.gechan.mallapi.domain.Product;
import com.gechan.mallapi.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {

        for (int i = 1; i <= 10; i++) {

            Product product = Product.builder()
                    .pname("Test .." + i).pdesc("Test desc .." + i).price(1000).build();
            product.addImageString(UUID.randomUUID()+"_"+"image1.jpg");
            product.addImageString(UUID.randomUUID()+"_"+"image2.jpg");

            productRepository.save(product);
        }


    }

    @Transactional
    @Test
    public void testRead1() {

        Long pno = 1L;

        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        log.debug(product);

        log.debug(product.getImageList());
    }

    @Test
    public void testRead2() {

        Long pno = 1L;

        Optional<Product> result = productRepository.selectOne(pno);

        Product product = result.orElseThrow();

        log.debug(product);

        log.debug(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {

        Long pno = 2L;

        productRepository.updateToDelete(2L, true);
    }

    @Test
    public void testUpdate() {

        Product product = productRepository.selectOne(1L).get();

        product.changePrice(3000);

        product.clearList();

        product.addImageString(UUID.randomUUID()+"_"+"pimage1.jpg");
        product.addImageString(UUID.randomUUID()+"_"+"pimage2.jpg");
        product.addImageString(UUID.randomUUID()+"_"+"pimage3.jpg");

        productRepository.save(product);
    }

    @Test
    public void testList() {

        Pageable pageable =
                PageRequest.of(0,
                        10,
                        Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        result.getContent().forEach(arr -> log.debug(Arrays.toString(arr)));
    }

    @Test
    public void testSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        productRepository.searchList(pageRequestDTO);
    }
}
