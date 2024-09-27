package learn.caojw.eppsp.business.mapper;

import learn.caojw.eppsp.business.domain.Feedback;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 空气质量公众监督反馈信息表 Mapper 接口
 * </p>
 *
 * @author CaoJianwei
 * @since 2024-09-27
 */
public interface FeedbackMapper {
    @Insert("insert into bus_feedback (supervisor, city, address, information, estimated_grade, af_datetime, remarks) values (#{supervisor},#{city},#{address},#{information},#{estimatedGrade},now(),#{remark})")
    void insert(Feedback feedback);

    @Delete("delete from bus_feedback where id = #{feedback}")
    void delete(Integer feedback);

    void update(Feedback feedback);

    @Select("select * from bus_feedback")
    List<Feedback> selectAll();

    @Select("select * from bus_feedback where supervisor = #{supervisor}")
    List<Feedback> selectBySupervisor(Long supervisor);

    @Select("select * from bus_feedback where grid = #{grid}")
    List<Feedback> selectByGrid(Long grid);

    @Select("select * from bus_feedback where id = #{id}")
    Feedback selectById(Long id);
}
