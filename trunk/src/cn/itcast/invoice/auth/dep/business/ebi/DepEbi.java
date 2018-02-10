package cn.itcast.invoice.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.dep.vo.DepModel;
import cn.itcast.invoice.util.base.BaseEbi;

/**
 * this is an interface based on BaseEbi
 *
 */
@Transactional
public interface DepEbi extends BaseEbi<DepModel> {
}


/*
public void save(DepModel dm);

public void update(DepModel dm);

public void delete(DepModel dm);

public DepModel get(Long uuid);

public List<DepModel> getAll();

/**
 * åˆ†é¡µèŽ·å�–æ•°æ�®
 * @param dqm æŸ¥è¯¢æ�¡ä»¶
 * @param pageNum é¡µç �å€¼
 * @param pageCount æ¯�é¡µæ˜¾ç¤ºæ•°
 * @return
 */
/*	public List<DepModel> getAll(DepQueryModel dqm, Integer pageNum,Integer pageCount);

public Integer getCount(DepQueryModel dqm);
*/