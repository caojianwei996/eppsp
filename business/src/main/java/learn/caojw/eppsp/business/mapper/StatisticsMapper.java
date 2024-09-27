package learn.caojw.eppsp.business.mapper;

import learn.caojw.eppsp.business.domain.Statistics;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StatisticsMapper {
    @Insert("insert into bus_statistics (city, address, so2, co, spm, aqi, confirm, grid, feedback, remarks) values (#{city},#{address},#{so2},#{co},#{spm},#{aqi},now(),#{grid},#{feedback},#{remarks})")
    void insert(Statistics statistics);

    @Delete("delete from bus_statistics where id = #{id}")
    void delete(Long id);

    @Select("select * from bus_statistics where id = #{id}")
    Statistics selectById(Long id);

    @Select("select * from bus_statistics")
    List<Statistics> selectAll();
}
