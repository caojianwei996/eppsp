package learn.caojw.eppsp.business.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 空气质量公众监督反馈信息表
 * </p>
 *
 * @author CaoJianwei
 * @since 2024-09-27
 */
@Data
public class Feedback {
    private Long id;
    private Long supervisor;
    private String city;
    private String address;
    private String information;
    private Integer estimatedGrade;
    private LocalDateTime afDatetime;
    private Long grid;
    private LocalDateTime assignDatetime;
    private Integer status;
    private String remarks;
}
