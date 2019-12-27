package com.tsyh.springdatajest;

import com.tsyh.springdatajest.dataobject.ESProductDO;
import com.tsyh.springdatajest.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description  测试spring-data-jest
 * @Author
 * @Date
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    // 插入一条记录
    @Test
    public void testInsert() {
        ESProductDO product = new ESProductDO();
        product.setId(4); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
        product.setName("你好");
        product.setSellPoint("第四个数据");
        product.setDescription("我只是一个描述");
        product.setCid(1);
        product.setCategoryName("技术-python");
        productRepository.save(product);
    }


    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO product = new ESProductDO();
        product.setId(1);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        productRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        productRepository.deleteAll();
//        productRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<ESProductDO> userDO = productRepository.findById(1);
        System.out.println(userDO.toString());
        System.out.println(userDO.isPresent());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = productRepository.findAllById(Arrays.asList(1,4));
        users.forEach(System.out::println);
    }

}
