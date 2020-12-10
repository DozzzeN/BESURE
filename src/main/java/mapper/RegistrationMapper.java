package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RegistrationMapper {
    @Insert("insert into t_ks " +
            "values(#{idP},#{pwP},#{au},#{eP})")
    int toKS(@Param("idP") String idP, @Param("pwP") String pwP,
             @Param("au") String au, @Param("eP") int eP);

    @Insert("insert into t_cs " +
            "values(#{idP},#{auCS},#{cskP})")
    int toCS(@Param("idP") String idP, @Param("auCS") String auCS, @Param("cskP") String cskP);

    @Insert("insert into t_h " +
            "values(#{idP},#{auH})")
    int toH(@Param("idP") String idP, @Param("auH") String auH);
}
