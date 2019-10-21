package top.wffanshao.office.service;

import top.wffanshao.office.dto.WrittenDTO;
import top.wffanshao.office.pojo.OfficeDbWritten;
import top.wffanshao.office.vo.ResponsePage;

/**
 * 描述：签单记录Service
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
public interface WrittenService {

    /**
     * 描述：分页查询团队下所有的签单记录
     *
     * @param teamId
     * @param page   当前页数
     * @param size   总叶数
     * @return
     */
    ResponsePage<WrittenDTO> findAllWrittenByPage(int teamId, int page, int size);

    /**
     * 描述：添加成员
     *
     * @param token
     * @param written
     * @return
     */
    boolean addWritten(String token, OfficeDbWritten written);


    /**
     * 描述：删除签单记录
     *
     * @param token
     * @param writtenId
     * @param userId
     * @return
     */
    boolean deleteWrittenWrittenId(String token, Integer writtenId, Integer userId);


    /**
     * 描述：根据id修改相对应的签单记录
     *
     * @param token
     * @param writtenId
     * @param written
     * @return
     */
    Boolean updateWrittenByWrittenId(String token, Integer writtenId, OfficeDbWritten written);
}
