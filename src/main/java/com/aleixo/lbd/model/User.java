/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.aleixo.lbd.rest.view.UserView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
		@NamedQuery(name = "User.findByCpf", query = "SELECT u FROM User u WHERE u.cpf = :cpf"),
		@NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonView(UserView.UserResume.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JsonView(UserView.UserResume.class)
	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@JsonView(UserView.UserResume.class)
	@Basic(optional = false)
	@Column(name = "cpf")
	private String cpf;

	@JsonView(UserView.UserResume.class)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Basic(optional = false)
	@Column(name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	@JsonView(UserView.UserResume.class)
	@Basic(optional = false)
	@Column(name = "role")
	private String role;

	@JsonView(UserView.UserFull.class)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private List<HistoryTask> historyTaskList;

	@JsonView(UserView.UserResume.class)
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Job jobId;

	@JsonIgnore
	@Basic(optional = false)
	@Column(name = "password")
	private String password;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String name, String cpf, Date birthDate) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@XmlTransient
	public List<HistoryTask> getHistoryTaskList() {
		return historyTaskList;
	}

	public void setHistoryTaskList(List<HistoryTask> historyTaskList) {
		this.historyTaskList = historyTaskList;
	}

	public Job getJobId() {
		return jobId;
	}

	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.fd.habiliteme.manager.model.User[ id=" + id + " ]";
	}

}
