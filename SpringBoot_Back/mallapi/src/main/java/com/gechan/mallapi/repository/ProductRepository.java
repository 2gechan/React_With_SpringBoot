package com.gechan.mallapi.repository;

import com.gechan.mallapi.domain.Product;
import com.gechan.mallapi.repository.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {

    // imageList에 담긴 Product Entity 테이블과 자동 조인
    @EntityGraph(attributePaths = "imageList")
    @Query("select p from Product p where p.pno = :pno")
    Optional<Product> selectOne(@Param("pno") Long pno);

    @Modifying
    @Query("update Product p set p.delflag = :delFlag where p.pno = :pno")
    void updateToDelete(@Param("pno") Long pno, @Param("delFlag") boolean delFlag);

    @Query("select p, pi from Product p left join p.imageList pi where pi.ord = 0 and p.delflag = false ")
    Page<Object[]> selectList(Pageable pageable);



}
