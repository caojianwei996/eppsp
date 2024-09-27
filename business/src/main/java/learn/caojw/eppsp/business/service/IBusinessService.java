package learn.caojw.eppsp.business.service;

import learn.caojw.eppsp.business.domain.Feedback;
import learn.caojw.eppsp.business.domain.Statistics;
import learn.caojw.eppsp.common.core.domain.entity.SysDictData;

import java.util.List;

/**
 * <p>
 * 空气质量公众监督反馈信息表 服务类
 * </p>
 *
 * @author CaoJianwei
 * @since 2024-09-27
 */
public interface IBusinessService {
    List<SysDictData> selectAqi();

    void insertFeedback(Feedback feedback);

    List<Feedback> selectFeedbackBySupervisor();

    List<Feedback> selectFeedback(Integer pageSize, Integer pageNum);

    Feedback selectFeedbackById(Long id);

    void updateFeedback(Feedback feedback);

    List<Feedback> selectFeedbackByGrid();

    List<SysDictData> selectCity();

    void insertStatistics(Statistics statistics);

    void deleteStatistics(Long id);

    List<Statistics> selectStatistics();

    Statistics selectStatisticsById(Long id);
}
