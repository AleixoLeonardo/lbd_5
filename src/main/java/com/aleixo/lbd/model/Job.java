/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.aleixo.lbd.rest.view.JobView;
import com.aleixo.lbd.rest.view.TaskView;
import com.aleixo.lbd.rest.view.UserView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "job")
@XmlRootElement
@JsonIgnoreProperties("userList")
@NamedQueries({ @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
		@NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id"),
		@NamedQuery(name = "Job.findByName", query = "SELECT j FROM Job j WHERE j.name = :name") })
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ JobView.JobResume.class, UserView.UserFull.class, TaskView.TaskResume.class  })
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "name")
	@JsonView({ JobView.JobResume.class, UserView.UserFull.class, TaskView.TaskResume.class })
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId", fetch = FetchType.EAGER)
	@JsonView(JobView.JobFull.class)
	private List<User> userList;
	
	@JsonView({ TaskView.TaskFull.class })
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "jobs", cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Task> tasks = new HashSet<Task>();

	public Job() {
	}

	public Job(Integer id) {
		this.id = id;
	}

	public Job(Integer id, String name) {
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

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@XmlTransient
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "br.com.fd.habiliteme.manager.model.Job[ id=" + id + " ]";
	}

}
