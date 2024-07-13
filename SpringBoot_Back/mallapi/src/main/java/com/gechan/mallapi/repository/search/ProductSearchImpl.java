package com.gechan.mallapi.repository.search;

import com.gechan.mallapi.domain.Product;
import com.gechan.mallapi.domain.QProduct;
import com.gechan.mallapi.domain.QProductImage;
import com.gechan.mallapi.dto.PageRequestDTO;
import com.gechan.mallapi.dto.PageResponseDTO;
import com.gechan.mallapi.dto.ProductDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {

        log.debug("============SearchList============");

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("pno").descending());

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<Product> query = from(product);

        // product Entity의 imageList를 ProductImage Entity로 간주한다.
        query.leftJoin(product.imageList, productImage);

        query.where(productImage.ord.eq(0)); // eq = equal

        Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query); // 페이징 쿼리 추가

        // List<Product> productList = query.fetch(); // 쿼리 실행
        List<Tuple> productList = query.select(product, productImage).fetch(); // 상품과 상품 이미지 조회쿼리 실행

        long count = query.fetchCount();

        log.debug("===========ElementCollectioin을 이용한 queryDSL 페이징===========");
        log.debug(productList);

        return null;
    }
}
