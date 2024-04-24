package com.account.editor;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_account")
public class Account {
	
	@Id
	private Integer pno;
	private String pname;
	private Integer zandaka;
	private Date batchupdate;
	

}
