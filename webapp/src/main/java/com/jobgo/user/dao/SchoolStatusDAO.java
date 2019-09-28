package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbSchoolStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：学校状态DAO
 * 创建时间: 2019-09-08 8:40
 * 修改时间: 2019-09-08 8:40
 */
public interface SchoolStatusDAO extends JpaRepository<JobgoJgDbSchoolStatus, String>, JpaSpecificationExecutor<JobgoJgDbSchoolStatus> {

    /**
     * 描述：通过学校id查询学校状态信息
     *
     * @param schId
     * @return
     */
    JobgoJgDbSchoolStatus findJobgoJgDbSchoolStatusBySchId(String schId);
}
