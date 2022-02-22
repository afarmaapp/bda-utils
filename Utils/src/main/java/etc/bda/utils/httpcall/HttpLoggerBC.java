package etc.bda.utils.httpcall;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.demoiselle.jee.crud.AbstractBusiness;

public class HttpLoggerBC extends AbstractBusiness<HttpLoggerEntity, Long> {
	
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public HttpLoggerEntity persist(HttpLoggerEntity entity) {
		return super.persist(entity);
	}

}
