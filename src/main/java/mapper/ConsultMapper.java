package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsultMapper {
    @Select("select auCS from t_cs where idP=#{idP}")
    String selAuCS(@Param("idP") String idP);

    @Select("select ck_rou_y_rou from t_ehr where idP=#{idP}")
    List<String> selCk_rou_y_rou(@Param("idP") String idP);

    @Select("select c_rou_y_rou from t_ehr where idP=#{idP}")
    List<String> selC_rou_y_rou(@Param("idP") String idP);

    @Update("insert into t_ehr (idP,stage,c_rou_y_rou,ck_rou_y_rou) " +
            "values (#{idP},#{stage},#{c_rou_y_rou},#{ck_rou_y_rou})")
    int insC_rou_y_rou(@Param("idP") String idP, @Param("stage") int stage,
                       @Param("c_rou_y_rou") String c_rou_y_rou, @Param("ck_rou_y_rou") String ck_rou_y_rou);

    @Select("select ifnull(max(stage),0) from t_ehr where idP=#{idP}")
    int selMaxStage(@Param("idP") String idP);
}
