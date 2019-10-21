package top.wffanshao.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.wffanshao.office.dao.CustomerDAO;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.dao.WrittenDAO;
import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.dto.WrittenDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.pojo.OfficeDbUserCustomer;
import top.wffanshao.office.pojo.OfficeDbWritten;
import top.wffanshao.office.service.WrittenService;
import top.wffanshao.office.vo.ResponsePage;

import javax.persistence.criteria.*;
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
     * 描述：分页查询团队下所有的签单记录
     *
     * @param teamId
     * @param page   当前页数
     * @param size   总叶数
     * @return
     */
    @Override
    public ResponsePage<WrittenDTO> findAllWrittenByPage(int teamId, int page, int size) {

        page = page <= 0 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, size);

        Specification<OfficeDbWritten> specification = (Specification<OfficeDbWritten>) (root, query, criteriaBuilder) -> {
            Path<Object> path = root.get("teamId");
            return criteriaBuilder.equal(path, teamId);
        };


        Page<OfficeDbWritten> writtenPage = writtenDAO.findAll(specification, pageable);

        if (writtenPage == null) {
            throw new MyException(ExceptionEnum.WRITTEN_NOT_FOUND);
        }

        List<OfficeDbWritten> writtenList = writtenPage.getContent();

        List<WrittenDTO> writtenDtoList = new LinkedList<>();

        writtenList.forEach(written -> {
            int customerId = written.getCustomerId();
            int userId = written.getUserId();
            WrittenDTO writtenDTO = new WrittenDTO();
            writtenDTO.setCreateTime(written.getCreateTime());
            Optional<OfficeDbCustomer> customerOptional = customerDAO.findById(customerId);
            customerOptional.ifPresent(customer -> writtenDTO.setCustomerName(customer.getCustomerName()));
            Optional<OfficeDbUser> userOptional = userDAO.findById(userId);
            userOptional.ifPresent(user -> writtenDTO.setCustomerName(user.getUserName()));
            writtenDTO.setDetail(written.getDetail());
            writtenDTO.setMoney(written.getMoney());
            writtenDTO.setUserId(userId);
            writtenDTO.setCustomerId(customerId);
            writtenDtoList.add(writtenDTO);
        });

        ResponsePage<WrittenDTO> customerResponsePage = new ResponsePage<>();
        customerResponsePage.setContent(writtenDtoList);
        customerResponsePage.setTotalPages(writtenPage.getTotalPages());
        customerResponsePage.setTotalElements((int) writtenPage.getTotalElements());

        return customerResponsePage;

    }



}
