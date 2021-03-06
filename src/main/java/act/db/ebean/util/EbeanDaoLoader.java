package act.db.ebean.util;

import act.app.App;
import act.app.DbServiceManager;
import act.db.ebean.EbeanDao;
import org.osgl.inject.BeanSpec;
import org.osgl.inject.GenericTypedBeanLoader;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.util.List;

@ApplicationScoped
public class EbeanDaoLoader implements GenericTypedBeanLoader<EbeanDao> {

    private DbServiceManager dbServiceManager;
    public EbeanDaoLoader() {
        dbServiceManager = App.instance().dbServiceManager();
    }

    @Override
    public EbeanDao load(BeanSpec beanSpec) {
        List<Type> typeList = beanSpec.typeParams();
        int sz = typeList.size();
        if (sz > 1) {
            Class<?> modelType = BeanSpec.rawTypeOf(typeList.get(1));
            return (EbeanDao) dbServiceManager.dao(modelType);
        }
        return null;
    }
}
