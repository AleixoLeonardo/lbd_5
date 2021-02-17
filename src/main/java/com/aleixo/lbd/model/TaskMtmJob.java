/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleixo.lbd.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aleixo
 */
@Entity
@Table(name = "task_mtm_job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskMtmJob.findAll", query = "SELECT t FROM TaskMtmJob t")
    , @NamedQuery(name = "TaskMtmJob.findById", query = "SELECT t FROM TaskMtmJob t WHERE t.id = :id")})
public class TaskMtmJob implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Job jobId;
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Task taskId;

    public TaskMtmJob() {
    }

    public TaskMtmJob(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
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
        if (!(object instanceof TaskMtmJob)) {
            return false;
        }
        TaskMtmJob other = (TaskMtmJob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fd.habiliteme.manager.model.TaskMtmJob[ id=" + id + " ]";
    }
    
}
