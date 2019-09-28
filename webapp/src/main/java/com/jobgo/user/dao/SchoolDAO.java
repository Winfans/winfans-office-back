package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：学校DAO
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface SchoolDAO extends JpaRepository<JobgoJgDbSchool, String>, JpaSpecificationExecutor<JobgoJgDbSchool> {

    /**
     * 描述：通过用户id查询学校信息
     *
     * @param userId
     * @return
     */
    JobgoJgDbSchool findJobgoJgDbSchoolByUserId(Integer userId);

    /**
     * 描述：通过学校id查询返回结果的数量
     *
     * @param schId
     * @return
     */
    int countJobgoJgDbSchoolBySchId(String schId);


}
