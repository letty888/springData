package com.itheima.es.dao;

import com.itheima.es.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 10:06
 */
public interface EmployeeDao extends ElasticsearchRepository<Employee, String> {
}
