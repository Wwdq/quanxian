package model;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;
@Getter
@Setter
@ToString
public class DepTree  extends Department{
    private List<DepTree> list;
   public static  DepTree  adapt(Department dp){
        DepTree dt=new DepTree();
        BeanUtils.copyProperties(dp, dt);
        return dt;
    }
    DepTree(){

    }

    public void setList(List<DepTree> list) {
        this.list = list;
    }
}
