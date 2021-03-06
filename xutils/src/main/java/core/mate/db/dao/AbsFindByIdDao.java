package core.mate.db.dao;

import android.support.annotation.NonNull;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.lang.reflect.Type;

import core.mate.db.AbsDao;
import core.mate.util.ClassUtil;

/**
 * 用于根据id查询数据库的访问基类
 *
 * @author DrkCore 178456643@qq.com
 * @since 2016年1月3日21:21:03
 */
public abstract class AbsFindByIdDao<Table> extends AbsDao<Table> {

	/* 继承 */

	@Override
	public final Table access (@NonNull DbManager db) throws DbException {
		Type type = ClassUtil.getGenericParametersType(getClass())[0];
		if (type instanceof Class) {
			@SuppressWarnings("unchecked")
			Class<Table> clazz = (Class<Table>) type;
			return db.findById(clazz, idValue);
		}
		throw new IllegalStateException("指定泛型" + type.toString() + "不可用");
	}

	/* 拓展 */

	private Object idValue;

	public final AbsFindByIdDao<Table> byId (Object idValue) {
		this.idValue = idValue;
		return this;
	}

}
