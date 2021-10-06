/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.aleixo.lbd.rest.view.HistoryTaskView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "history_task")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "HistoryTask.findAll", query = "SELECT h FROM HistoryTask h"),
		@NamedQuery(name = "HistoryTask.findById", query = "SELECT h FROM HistoryTask h WHERE h.id = :id"),
		@NamedQuery(name = "HistoryTask.findByHistoryDate", query = "SELECT h FROM HistoryTask h WHERE h.historyDate = :historyDate") })
public class HistoryTask implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "history_date")
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date historyDate;
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	private Task taskId;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	private User userId;
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	@Column(name = "message", length = 100)
	private String message;

	public HistoryTask() {
	}

	public HistoryTask(Integer id) {
		this.id = id;
	}

	public HistoryTask(Integer id, Date historyDate) {
		this.id = id;
		this.historyDate = historyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}

	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof HistoryTask)) {
			return false;
		}
		HistoryTask other = (HistoryTask) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.fd.habiliteme.manager.model.HistoryTask[ id=" + id + " ]";
	}

}
