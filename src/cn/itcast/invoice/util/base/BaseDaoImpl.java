package cn.itcast.invoice.util.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public abstract class BaseDaoImpl<T> extends HibernateDaoSupport{
	//å½“å‰�ç±»ä¸­æ³›åž‹çš„ç±»åž‹
	private Class<T> entityClass;
	
	//ä½•æ—¶åˆ�å§‹åŒ–:å½“å‰�ç±»çš„å¯¹è±¡åˆ›å»ºå®Œæ¯•ä¹‹å‰�
	public BaseDaoImpl(){
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType =(ParameterizedType)type;
		Type[] types = pType.getActualTypeArguments(); 
		entityClass = (Class) types[0];
	}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
		/*try {
		} catch (Exception e) {
			throw new AppException("å¯¹ä¸�èµ·ï¼Œæ•°æ�®åº“æœ�åŠ¡å™¨æ²¡æœ‰å�¯åŠ¨ï¼Œè¯·è�”ç³»ç®¡ç�†å‘˜ï¼�",e);
		}*/
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public T get(Serializable uuid) {
		return this.getHibernateTemplate().get(entityClass,uuid);
	}
	
	public List<T> getAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	public List<T> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		doQbc(dc,qm);
		return this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}
	
	public Integer getCount(BaseQueryModel qm) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.setProjection(Projections.rowCount());
		doQbc(dc,qm);
		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}
	
	//å¼ºåˆ¶å­�ç±»è¦†ç›–doQbcæ–¹æ³•
	protected abstract void doQbc(DetachedCriteria dc,BaseQueryModel qm);
}
/*
public static void main(String[] args) {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-dep.xml");
	DepDao dao = (DepDao) ctx.getBean("depDao");
	System.out.println(dao.getCount(new DepQueryModel()));
}
*/
/*
public Integer getCount(DepQueryModel dqm) {
	String hql = "select count(uuid) from DepModel where 1 = 1 ";
	if(dqm.getName()!=null && dqm.getName().trim().length()>0){
		hql += " and name like ? ";
	}
	if(dqm.getTele()!=null && dqm.getTele().trim().length()>0){
		hql += " and tele like ? ";
	}
	List<Long> count = this.getHibernateTemplate().find(hql,"%"+dqm.getName()+"%","%"+dqm.getTele()+"%");
	return count.get(0).intValue();
}
*/