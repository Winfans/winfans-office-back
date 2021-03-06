package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.wffanshao.office.bo.UserInfo;
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
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.WrittenService;
import top.wffanshao.office.utils.JwtUtils;
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
@Slf4j
@Service
public class WrittenServiceImpl implements WrittenService {

    @Autowired
    private WrittenDAO writtenDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 描述：分页查询团队下所有的签单记录
     *
     * @param teamId
     * @param page   当前页数
     * @param size   总页数
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
            userOptional.ifPresent(user -> writtenDTO.setUserName(user.getUserName()));
            writtenDTO.setDetail(written.getDetail());
            writtenDTO.setMoney(written.getMoney());
            writtenDTO.setUserId(userId);
            writtenDTO.setCustomerId(customerId);
            writtenDTO.setWrittenId(written.getWrittenId());
            writtenDtoList.add(writtenDTO);
        });

        ResponsePage<WrittenDTO> customerResponsePage = new ResponsePage<>();
        customerResponsePage.setContent(writtenDtoList);
        customerResponsePage.setTotalPages(writtenPage.getTotalPages());
        customerResponsePage.setTotalElements((int) writtenPage.getTotalElements());

        return customerResponsePage;

    }

    /**
     * 描述：添加签单记录
     *
     * @param token
     * @param writtenDTO
     * @return
     */
    @Override
    public boolean addWritten(String token, WrittenDTO writtenDTO) {
        // 解析token获取用户id
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            throw new MyException(ExceptionEnum.NO_AUTHENTICATION);
        }
        OfficeDbCustomer customer = customerDAO.findByCustomerName(writtenDTO.getCustomerName());

        OfficeDbWritten written = new OfficeDbWritten();
        written.setUserId(userInfo.getUserId());
        written.setCustomerId(customer.getCustomerId());
        written.setCreateTime(new Timestamp(System.currentTimeMillis()));
        written.setMoney(writtenDTO.getMoney());
        written.setDetail(writtenDTO.getDetail());
        written.setTeamId(customer.getTeamId());

        OfficeDbWritten savedWritten = writtenDAO.saveAndFlush(written);

        if (savedWritten.getWrittenId() == 0)   {
            throw new MyException(ExceptionEnum.WRITTEN_ADD_FAIL);
        }
        return true;
    }

    /**
     * 描述：删除签单记录
     *
     * @param token
     * @param writtenId
     * @return
     */
    @Override
    public boolean deleteWrittenByWrittenId(String token, Integer writtenId) {
        boolean result = writtenDAO.existsById(writtenId);
        if (!result) {
            throw new MyException(ExceptionEnum.WRITTEN_NOT_FOUND);
        }

        // 解析token获取用户id
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            throw new MyException(ExceptionEnum.NO_AUTHENTICATION);
        }

        Optional<OfficeDbWritten> optional = writtenDAO.findById(writtenId);
        if (optional.isPresent()) {
            if (userInfo.getUserId() != optional.get().getUserId()) {
                throw new MyException(ExceptionEnum.PERMISSION_DENIED);
            }
        }

        writtenDAO.deleteById(writtenId);
        return true;
    }

    /**
     * 描述：根据id修改相对应的签单记录
     *
     * @param token
     * @param writtenId
     * @param writtenDTO
     * @return
     */
    @Override
    public Boolean updateWrittenByWrittenId(String token, Integer writtenId, WrittenDTO writtenDTO) {

        boolean result = writtenDAO.existsById(writtenId);
        if (!result) {
            throw new MyException(ExceptionEnum.WRITTEN_NOT_FOUND);
        }

        // 解析token获取用户id
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            throw new MyException(ExceptionEnum.NO_AUTHENTICATION);
        }

        Optional<OfficeDbWritten> optional = writtenDAO.findById(writtenId);

        if (optional.isPresent()) {
            OfficeDbWritten written = optional.get();

            if (userInfo.getUserId() != written.getUserId()) {
                throw new MyException(ExceptionEnum.PERMISSION_DENIED);
            }

            written.setDetail(writtenDTO.getDetail());
            written.setMoney(writtenDTO.getMoney());
            written.setCustomerId(customerDAO.findByCustomerName(writtenDTO.getCustomerName()).getCustomerId());
            writtenDAO.saveAndFlush(written);
            return true;

        }
        return false;
    }

    /**
     * 描述：根据writtenId查询签单记录
     *
     * @param writtenId
     * @return
     */
    @Override
    public WrittenDTO findWrittenByTeamId(int writtenId) {

        Optional<OfficeDbWritten> optional = writtenDAO.findById(writtenId);
        if (optional.isPresent()) {
            OfficeDbWritten written = optional.get();
            WrittenDTO writtenDTO = new WrittenDTO();
            writtenDTO.setCreateTime(written.getCreateTime());
            writtenDTO.setDetail(written.getDetail());
            writtenDTO.setMoney(written.getMoney());
            int customerId = written.getCustomerId();
            writtenDTO.setCustomerId(customerId);
            int userId = written.getUserId();
            writtenDTO.setUserId(userId);
            writtenDTO.setWrittenId(written.getWrittenId());
            Optional<OfficeDbCustomer> customerOptional = customerDAO.findById(customerId);
            customerOptional.ifPresent(customer -> writtenDTO.setCustomerName(customer.getCustomerName()));
            Optional<OfficeDbUser> userOptional = userDAO.findById(userId);
            userOptional.ifPresent(user -> writtenDTO.setUserName(user.getUserName()));
            return writtenDTO;
        }
        return null;
    }
}
