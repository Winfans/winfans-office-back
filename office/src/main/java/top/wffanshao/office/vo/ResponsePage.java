package top.wffanshao.office.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述：包装返回json格式的对象
 *
 * @param <T>
 * @author 杨炜帆
 * @date 2019/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ResponsePage<T> {
    private List<T> content;
    private int totalPages;
    private int totalElements;
}
