package ua.com.alevel.generics;

import java.util.List;

public interface SystemCrudDao<AE extends AbstractEntity> {

    <E extends AE, ID> void generate(List<CrudDao<E, ID>> list);
}
