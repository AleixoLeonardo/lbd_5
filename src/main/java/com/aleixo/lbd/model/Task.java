/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")
    , @NamedQuery(name = "Task.findByNome", query = "SELECT t FROM Task t WHERE t.nome = :nome")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
    private List<HistoryTask> historyTaskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
    private List<TaskMtmJob> taskMtmJobList;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<HistoryTask> getHistoryTaskList() {
        return historyTaskList;
    }

    public void setHistoryTaskList(List<HistoryTask> historyTaskList) {
        this.historyTaskList = historyTaskList;
    }

    @XmlTransient
    public List<TaskMtmJob> getTaskMtmJobList() {
        return taskMtmJobList;
    }

    public void setTaskMtmJobList(List<TaskMtmJob> taskMtmJobList) {
        this.taskMtmJobList = taskMtmJobList;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fd.habiliteme.manager.model.Task[ id=" + id + " ]";
    }
    
}
