package etc.bda.utils.rest;






import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.io.IOUtils;
import org.demoiselle.jee.crud.DemoiselleRequestContext;
import org.demoiselle.jee.crud.TreeNodeField;
import org.demoiselle.jee.rest.exception.DemoiselleRestException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class UtilREST {
	
	@Inject
	private DemoiselleRequestContext drc;

	private HttpHeaders headers;

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public String getIdentity() {
		return this.getIdentityFromHeader();
	}

	private String getIdentityFromHeader() {
		String identity = null;
		if (headers != null) {
			List<String> identityHeaders = headers.getRequestHeader("identity");
			if (identityHeaders != null && !identityHeaders.isEmpty()) {
				identity = identityHeaders.get(0);
			}
		}
		return identity;
	}

	public void addFilterFromIdentity(String className) {
		String identity = this.getIdentity();
		if (identity != null) {
			Set<String> values = new HashSet<String>();
			values.add(identity);
			TreeNodeField<String, Set<String>> filters = drc.getFilters();
			if (filters == null) {
				filters = new TreeNodeField<String, Set<String>>(className, Collections.EMPTY_SET);
				filters.addChild("provider", Collections.EMPTY_SET).addChild("identity", values);
				drc.setFilters(filters);
			} else {
				TreeNodeField<String, Set<String>> filter = filters.getChildByKey("provider");
				if (filter == null) {
					filters.addChild("provider", Collections.EMPTY_SET).addChild("identity", values);
				} else {
					filter.addChild("identity", values);
				}
			}
		}
	}

	public byte[] getImageFromForm(MultipartFormDataInput dataInput) {
		byte[] image = null;
		Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
		List<InputPart> imageParts = uploadForm.get("image");
		try {
			for (InputPart imagePart : imageParts) {
				InputStream inputStream = imagePart.getBody(InputStream.class, null);
				image = IOUtils.toByteArray(inputStream);
			}
		} catch (Exception e) {
			throw new DemoiselleRestException("Erro ao tentar ler imagem da requisicao.", e);
		}
		return image;
	}
}
