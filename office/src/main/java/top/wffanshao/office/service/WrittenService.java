package top.wffanshao.office.service;

import top.wffanshao.office.dto.WrittenDTO;
import top.wffanshao.office.vo.ResponsePage;

/**
 * 描述：签单记录Service
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
public interface WrittenService {


    /**
     * 
     * 
     * @param size
     * @param page
     * @return
     */
    ResponsePage<WrittenDTO> findAllWrittenByPage(int page, int size);

}
