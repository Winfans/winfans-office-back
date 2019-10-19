package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbWritten;
import top.wffanshao.office.pojo.OfficeDbWrittenPK;


/**
 * 描述：签单记录DAO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
public interface WrittenDAO extends JpaRepository<OfficeDbWritten, OfficeDbWrittenPK>, JpaSpecificationExecutor<OfficeDbWritten>{

}
