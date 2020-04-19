package com.ithaima.es.test;

import com.itheima.es.EsApplication;
import com.itheima.es.dao.EmployeeDao;
import com.itheima.es.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
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
            employee.setId(i + "");
            employee.setAge(20 + i);
            employee.setName("孙悟空" + i);
            employee.setAddress("花果山" + i);
            list.add(employee);
        }
        employeeDao.saveAll(list);
    }
}
