package top.wffanshao.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.wffanshao.office.dao.CustomerDAO;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.dao.WrittenDAO;
import top.wffanshao.office.dto.WrittenDTO;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.pojo.OfficeDbWritten;
import top.wffanshao.office.service.WrittenService;
import top.wffanshao.office.vo.ResponsePage;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


/**
 * 描述：签单记录Service实现类
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Service
public class WrittenServiceImpl implements WrittenService {

    @Autowired
    private WrittenDAO writtenDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CustomerDAO customerDAO;

    /**
     * @param size
     * @param page
     * @return
     */
    @Override
    public ResponsePage<WrittenDTO> findAllWrittenByPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OfficeDbWritten> writtenPage = writtenDAO.findAll(pageable);

        List<OfficeDbWritten> writtenList = writtenPage.getContent();

        List<WrittenDTO> writtenDtoList = new LinkedList<>();

        writtenList.forEach(written -> {
            WrittenDTO writtenDTO = new WrittenDTO();
            Optional<OfficeDbCustomer> optional1 = customerDAO.findById(written.getCustomerId());
            optional1.ifPresent(customer -> writtenDTO.setCustomerName(customer.getCustomerName()));

            Optional<OfficeDbUser> optional2 = userDAO.findById(written.getUserId());
            optional2.ifPresent(user -> writtenDTO.setUserName(user.getUserName()));
            writtenDTO.setDetail(written.getDetail());
            written.setMoney(written.getMoney());
            writtenDTO.setCreateTime(written.getCreateTime());
            writtenDtoList.add(writtenDTO);
        });
        ResponsePage<WrittenDTO> responsePage = new ResponsePage<>();
        responsePage.setContent(writtenDtoList);
        responsePage.setTotalPages(writtenPage.getTotalPages());
        responsePage.setTotalElements(writtenPage.getSize());
        return responsePage;
    }
}
