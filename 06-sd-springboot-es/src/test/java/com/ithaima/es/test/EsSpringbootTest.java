package com.ithaima.es.test;

import com.itheima.es.EsApplication;
import com.itheima.es.dao.EmployeeDao;
import com.itheima.es.domain.Employee;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 10:15
 */
@SpringBootTest(classes = EsApplication.class)
@RunWith(SpringRunner.class)
public class EsSpringbootTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testCreateIndex() {

        //创建索引库
        elasticsearchTemplate.createIndex(Employee.class);
        List<Employee> list = new ArrayList<>();
        //向索引库中添加文档
        for (int i = 1; i <= 20; i++) {
            Employee employee = new Employee();
            employee.setId(20+i + "");
            employee.setAge(40 + i);
            employee.setName("现任中国共产党中央委员会总书记，中共中央军事委员会主席，中华人民共和国主席，中华人民共和国中央军事委员会主席" + i);
            employee.setAddress("习近平，男，汉族，1953年6月生，陕西富平人，1969年1月参加工作，1974年1月加入中国共产党，清华大学人文社会学院马克思主义理论与思想政治教育专业毕业，在职研究生学历，法学博士学位" + i);
            list.add(employee);
        }
        employeeDao.saveAll(list);
    }

    @Test
    public void deleteDocument() {
        employeeDao.deleteById("14");
    }

    @Test
    public void updateDocument() {
        //将索引库中ID为19的document中的数据更新为以下
        Employee employee = new Employee();
        employee.setId("19");
        employee.setAge(19);
        employee.setName("孙悟空");
        employee.setAddress("花果山");
        employeeDao.save(employee);
    }

    /**
     * 简单查询
     */
    @Test
    public void findSimple(){
        Iterable<Employee> employeeIterable = employeeDao.findAll(Sort.by(Sort.Order.desc("age")));
        employeeIterable.forEach(System.out::println);
    }

    /**
     * 自定义查询(默认每页10条数据)
     */
    @Test
    public void customFind1(){
        List<Employee> employees = employeeDao.findByAgeBetweenAndNameContains(30, 60, "中国共产党");
        System.out.println(employees.size());
        employees.forEach(System.out::println);
    }


    /**
     * 自定义查询(通过分页参数流控制显示的条数)
     */
    @Test
    public void customFind2(){
        List<Employee> employees = employeeDao.findByAgeBetweenAndNameContains(30, 60, "中国共产党", PageRequest.of(0,5));
        System.out.println(employees.size());
        employees.forEach(System.out::println);
    }

    /**
     * 使用es原生查询对象进行查询
     */
    @Test
    public void originalFind(){
        //1.构造查询条件对象
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("清华大学人文社会学院马克思主义理论与思想政治教育专业毕业")).withPageable(PageRequest.of(0,3)).build();

        //2.执行查询
        elasticsearchTemplate.queryForList(searchQuery,Employee.class).forEach(System.out::println);
    }
}
