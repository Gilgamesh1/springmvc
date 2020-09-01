package guru.springframework.persistence.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable  {
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updateDate;
//    AuditingEntityListener
}
