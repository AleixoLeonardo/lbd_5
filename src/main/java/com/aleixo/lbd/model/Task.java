/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.aleixo.lbd.rest.view.HistoryTaskView;
import com.aleixo.lbd.rest.view.TaskView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "task")
@XmlRootElement
@JsonIgnoreProperties({ "historyTaskList" })
@NamedQueries({ @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
		@NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
		@NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name") })
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ TaskView.TaskResume.class, HistoryTaskView.HistoryTaskResume.class })
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	@JsonView({ TaskView.TaskResume.class, HistoryTaskView.HistoryTaskResume.class })
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
	private List<HistoryTask> historyTaskList;

	@JsonView({ TaskView.TaskResume.class })
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(name = "task_mtm_job", 
		joinColumns = { @JoinColumn(name = "task_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "job_id") })
	private Set<Job> jobs;

	public Task() {
	}

	public Task(Integer id) {
		this.id = id;
	}

	public Task(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@XmlTransient
	public List<HistoryTask> getHistoryTaskList() {
		return historyTaskList;
	}

	public void setHistoryTaskList(List<HistoryTask> historyTaskList) {
		this.historyTaskList = historyTaskList;
	}

	@Override
	public String toString() {
		return "br.com.fd.habiliteme.manager.model.Task[ id=" + id + " ]";
	}

}
