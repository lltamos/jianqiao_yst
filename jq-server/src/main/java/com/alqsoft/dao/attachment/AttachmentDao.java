package com.alqsoft.dao.attachment;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.alqsoft.entity.attachment.Attachment;

public interface AttachmentDao extends BaseDao<Attachment>{


	@Query("from Attachment a where a.isbanner=true")
	public List<Attachment> getAttachmentsByIsBanner();
}
