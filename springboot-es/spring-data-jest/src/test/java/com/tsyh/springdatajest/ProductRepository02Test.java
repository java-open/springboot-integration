package com.tsyh.springdatajest;

import com.tsyh.springdatajest.dataobject.ESProductDO;
import com.tsyh.springdatajest.repository.ProductRepository02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepository02Test {

    @Autowired
    private ProductRepository02 productRepository;

    @Test // 根据名字获得一条记录
    public void testFindByName() {
        Optional<ESProductDO> product = Optional.ofNullable(productRepository.findByName("hello44"));
        System.out.println(product);
    }

    @Test // 使用 name 模糊查询，分页返回结果
    public void testFindByNameLike() {


        // 创建排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "id"); // ID 倒序
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        // 执行分页操作
        Page<ESProductDO> page = productRepository.findByNameLike("名字", pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    /**
     * 为了给分页制造一点数据
     */
    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            ESProductDO product = new ESProductDO();
            product.setId(i); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
            product.setName("hello" + i);
            product.setSellPoint("愿半生编码，如一生老友");
            product.setDescription("我只是一个描述");
            product.setCid(1);
            product.setCategoryName("技术-java");
            productRepository.save(product);
        }
    }

}