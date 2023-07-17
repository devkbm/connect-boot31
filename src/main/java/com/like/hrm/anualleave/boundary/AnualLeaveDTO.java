package com.like.hrm.anualleave.boundary;

import java.time.LocalDate;
import java.util.Objects;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.hrm.staff.domain.model.Staff;

public class AnualLeaveDTO {

	public record SaveAnualLeave(
			String organizationCode,
			String staffNo,
			Integer yyyy,			
			LocalDate base,
			LocalDate from,
			LocalDate to,
			Double cnt,
			Double use_cnt,
			Long total_work_days,
			Long except_days,
			Boolean isIntraAnual,
			String comment
			) {
		
		public SaveAnualLeave {
			Objects.requireNonNull(organizationCode);
			Objects.requireNonNull(staffNo);
			Objects.requireNonNull(yyyy);		        						
		}
		
		public static SaveAnualLeave convertDTO(AnualLeave e) {
			if (e == null) return null;
			
			AnualLeaveId id = e.getId();			
			return new SaveAnualLeave(id.getOrganizationCode()
									 ,id.getStaffNo()
									 ,id.getYyyy()									  
									 ,e.getBase()
									 ,e.getFrom()
									 ,e.getTo()
									 ,e.getCnt()
									 ,e.getUse_cnt()
									 ,e.getTotal_work_days()
									 ,e.getExcept_days()
									 ,e.getIsIntraAnual()
									 ,e.getComment());
		}
		
		public AnualLeave newAnualLeave(Staff staff) {
			
			return new AnualLeave(new AnualLeaveId(staff, yyyy)
								 ,base
								 ,from
								 ,to);
		}
		
		public void modifyEntity(AnualLeave entity) {
			entity.modify(base, from, to);
		}
		
		
	}
		
}
