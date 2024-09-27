package learn.caojw.eppsp.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import learn.caojw.eppsp.business.domain.Feedback;
import learn.caojw.eppsp.business.domain.Statistics;
import learn.caojw.eppsp.business.mapper.FeedbackMapper;
import learn.caojw.eppsp.business.mapper.StatisticsMapper;
import learn.caojw.eppsp.business.service.IBusinessService;
import learn.caojw.eppsp.common.core.domain.entity.SysDictData;
import learn.caojw.eppsp.common.utils.SecurityUtils;
import learn.caojw.eppsp.system.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 空气质量公众监督反馈信息表 服务实现类
 * </p>
 *
 * @author CaoJianwei
 * @since 2024-09-27
 */
@RequiredArgsConstructor
@Service
public class BusinessServiceImpl implements IBusinessService {
    private final SysDictDataMapper sysDictDataMapper;
    private final FeedbackMapper feedbackMapper;
    private final StatisticsMapper statisticsMapper;

    @Override
    public List<SysDictData> selectAqi() {
        return sysDictDataMapper.selectDictDataByType("bus_air_quality");
    }

    @Override
    public void insertFeedback(Feedback feedback) {
        feedbackMapper.insert(feedback);
    }

    @Override
    public List<Feedback> selectFeedbackBySupervisor() {
        return feedbackMapper.selectBySupervisor(SecurityUtils.getUserId());
    }

    @Override
    public List<Feedback> selectFeedback(Integer pageSize, Integer pageNum) {
        try (Page<Object> page = PageHelper.startPage(pageNum, pageSize)) {
            return feedbackMapper.selectAll();
        }
    }

    @Override
    public Feedback selectFeedbackById(Long id) {
        return feedbackMapper.selectById(id);
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        feedbackMapper.update(feedback);
    }

    @Override
    public List<Feedback> selectFeedbackByGrid() {
        return feedbackMapper.selectByGrid(SecurityUtils.getUserId());
    }

    @Override
    public List<SysDictData> selectCity() {
        return sysDictDataMapper.selectDictDataByType("bus_city_info");
    }

    @Override
    public void insertStatistics(Statistics statistics) {
        statisticsMapper.insert(statistics);
    }

    @Override
    public void deleteStatistics(Long id) {
        statisticsMapper.delete(id);
    }

    @Override
    public List<Statistics> selectStatistics() {
        return statisticsMapper.selectAll();
    }

    @Override
    public Statistics selectStatisticsById(Long id) {
        return statisticsMapper.selectById(id);
    }
}
