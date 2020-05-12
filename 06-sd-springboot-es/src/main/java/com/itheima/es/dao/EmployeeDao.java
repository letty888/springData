package com.itheima.es.dao;

import com.itheima.es.domain.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 10:06
 */
public interface EmployeeDao extends ElasticsearchRepository<Employee, String> {

    /**
     * 根据年龄区间和姓名查询(默认每页10条数据)
     * @param start 年龄开始条件
     * @param end 年龄结束条件
     * @param name 姓名中包含的字段
     * @return List<Employee>
     */
    List<Employee> findByAgeBetweenAndNameContains(int start,int end,String name);

    /**
     * 根据年龄区间和姓名查询+分页条件
     * @param start 年龄开始条件
     * @param end 年龄结束条件
     * @param name 姓名中包含的字段
     * @param pageable 分页参数
     * @return List<Employee>
     */
    List<Employee> findByAgeBetweenAndNameContains(int start, int end, String name, Pageable pageable);
}
